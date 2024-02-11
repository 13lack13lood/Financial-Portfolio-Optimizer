package geneticAlgorithm;

import financialData.FinancialDataCalculator;

public class GeneticAlgorithm {
	private static final double uniformRate = 0.5;
	private static final double mutationRate = 0.025;
	private static final int tournamentSize = 1000;
	private static final int elitismOffset = 1;
	
	private int populationSize;
	private int geneLength;
	
	private FinancialDataCalculator financialData;
	
	public GeneticAlgorithm(int populationSize, int geneLength, FinancialDataCalculator financialData) {
		this.populationSize = populationSize;
		this.geneLength = geneLength;
		this.financialData = financialData;
	}
	
	public void runAlgorithm() {
		Population population = new Population(populationSize, geneLength, true);
		
		int generationCount = 1;
		
		int fittestScore = population.getFittest(this).getFitness(this);
		
		while(fittestScore < maxFitness) {
			System.out.println("Generation: " + generationCount + " | Correct Genes Found: " + fittestScore);
			
			population = evolvePopulation(population);
			
			fittestScore = population.getFittest(this).getFitness(this);
			
			generationCount++;
		}
		
		System.out.println("Solution Found");
		System.out.println("Generation: " + generationCount);
		System.out.println("Genes: ");
		System.out.println(population.getFittest(this));
	}
	
	private Population evolvePopulation(Population population) {
		Population nextGeneration = new Population(population.getIndividuals().size(), geneLength, false);
		
		nextGeneration.getIndividuals().add(population.getFittest(this));
		
		for(int i = elitismOffset; i < population.getSize(); i++) {
			Individual parentA = tournamentSelection(population);
			Individual parentB = tournamentSelection(population);
			
			nextGeneration.getIndividuals().add(crossover(parentA, parentB));
		}
		
		for(int i = elitismOffset; i < population.getSize(); i++) {
			mutateIndividual(nextGeneration.getIndividuals().get(i));
		}
		
		return nextGeneration;
	}
	
	private void mutateIndividual(Individual individual) {
		for(int i = 0; i < geneLength; i++) {
			if(Math.random() <= mutationRate) {
				individual.setGene(i, (byte) Math.round(Math.random()));
			}
		}
	}
	
	private Individual crossover(Individual parentA, Individual parentB) {
		Individual child = new Individual(geneLength);
		
		for(int i = 0; i < geneLength; i++) {
			if(Math.random() < uniformRate) {
				child.setGene(i, parentA.getGene(i));
			} else {
				child.setGene(i, parentB.getGene(i));
			}
		}
		
		return child;
	}
	
	private Individual tournamentSelection(Population population) {
		Population tournament = new Population(tournamentSize, geneLength, false);
		
		for(int i = 0; i < tournamentSize; i++) {
			int randomIndex = (int) (Math.random() * population.getSize());
			tournament.getIndividuals().add(i, population.getIndividuals().get(randomIndex));
		}
		
		Individual fittest = tournament.getFittest(this);
		
		return fittest;
	}
	
	protected double getFitness(Individual individual) {
		return financialData.calculateSharpeRatio(individual.getGenes());
	}
}
