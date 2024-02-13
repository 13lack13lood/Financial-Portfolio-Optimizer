package com.geneticAlgorithm;

import java.util.List;

import com.financialData.FinancialDataCalculator;

public class GeneticAlgorithm {
	private static final double regularMutationRate = 0.4;
	private static final double finalMutationRate = 0.1;
	private static final double eliteRate = 0.3;
	
	private static final int generationPatience = 15;
	private static final int maxGenerations = 100;
	private static int populationSize = 100;
	private int geneLength;
	
	private FinancialDataCalculator financialData;
	
	public GeneticAlgorithm(FinancialDataCalculator financialData) {
		geneLength = financialData.getNumberOfStocks();
		this.financialData = financialData;
	}
	
	public double[] runAlgorithm() {
		Population population = new Population(populationSize, geneLength, true, this);
		
		int generationCount = 0;
		int patience = 0;
		double maxFitness = 0;
		
		Individual fittest = population.getFittest();
		
		while(generationCount <= maxGenerations) {
			double currentFitness = population.getFittest().getFitness();
			
			if(maxFitness > currentFitness) {
				patience++;
			} else {
				maxFitness = currentFitness;
				fittest = population.getFittest();
				patience = 0;
			}
			
			if(patience >= generationPatience) {
				break;
			}
			
			System.out.println("Generation: " + generationCount + " | Fittest: " + population.getFittest() + " | Score: " + population.getFittest().getFitness());
			
			population = evolvePopulation(population);
			
			generationCount++;
		}
		
		System.out.println("Fittest: " + fittest + " | Score: " + fittest.getFitness());
		
		return fittest.getGenes();
	}
	
	private Population evolvePopulation(Population population) {
		Population nextGeneration = new Population(populationSize, geneLength, false, this);
		
		List<Individual> elite = population.getElitePopulation(this, eliteRate);
		
		while(nextGeneration.getSize() < populationSize) {
			boolean performMutation = false;
			
			if(nextGeneration.getSize() > 2.0 * populationSize / 3.0) {
				performMutation = Math.random() <= finalMutationRate;
			} else {
				performMutation = Math.random() <= regularMutationRate;
			}
			
			if(performMutation) {
				int choice = (int) Math.floor(Math.random() * elite.size());
				
				mutateIndividual(elite.get(choice));
				
				nextGeneration.getIndividuals().add(elite.get(choice));
			} else {
				int eliteA = (int) Math.floor(Math.random() * elite.size());
				int eliteB = (int) Math.floor(Math.random() * elite.size());
				
				Individual[] children = heuristicCrossover(elite.get(eliteA), elite.get(eliteB));
				
				nextGeneration.getIndividuals().add(children[0]);
				nextGeneration.getIndividuals().add(children[1]);
			}
		}
		
		return nextGeneration;
	}
	
	private void mutateIndividual(Individual individual) {
		int geneA = (int) Math.floor(Math.random() * geneLength);
		int geneB = (int) Math.floor(Math.random() * geneLength);
		
		while(geneA == geneB) {
			geneB = (int) Math.floor(Math.random() * geneLength);
		}
		
		double temp = individual.getGene(geneA);
		
		individual.setGene(geneA, individual.getGene(geneB));
		individual.setGene(geneA, temp);
	}
	
	private Individual[] heuristicCrossover(Individual parentA, Individual parentB) {
		Individual childA = new Individual(geneLength, this);
		Individual childB = new Individual(geneLength, this);
		
		double beta = Math.random();
		
		double[] difference = new double[geneLength];
		
		for(int i = 0; i < geneLength; i++) {
			difference[i] = beta * (parentB.getGene(i) - parentA.getGene(i));
		}
		
		if(parentA.getFitness() > parentB.getFitness()) {
			for(int i = 0; i < geneLength; i++) {
				childA.setGene(i, parentA.getGene(i) + difference[i]);
				childB.setGene(i, parentB.getGene(i) - difference[i]);
			}
		} else {
			for(int i = 0; i < geneLength; i++) {
				childB.setGene(i, parentA.getGene(i) + difference[i]);
				childA.setGene(i, parentB.getGene(i) - difference[i]);
			}
		}
		
		return new Individual[] { childA, childB };
	}
	
	protected double getFitness(Individual individual) {
		return financialData.calculateSharpeRatio(individual.getGenes());
	}
}
