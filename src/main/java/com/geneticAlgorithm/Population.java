package com.geneticAlgorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Population {
	private List<Individual> individuals;
	
	public Population(int size, int geneLength, boolean createNew, GeneticAlgorithm algorithm) {
		individuals = new ArrayList<Individual>();
		
		if(createNew) {
			createNewPopulation(size, geneLength, algorithm);
		}
	}
	
	protected List<Individual> getIndividuals() {
		return individuals;
	}
	
	protected int getSize() {
		return individuals.size();
	}
	
	protected List<Individual> getElitePopulation(GeneticAlgorithm algorithm, double fraction) {
		sort();
		
		double eliteNumber = individuals.size() * fraction;
		
		List<Individual> elite = new LinkedList<Individual>();
		
		for(int i = 0; i < eliteNumber; i++) {
			elite.add(individuals.get(i));
		}
		
		return elite;
	}
	
	protected void sort() {
		Collections.sort(individuals);
	}
	
	protected Individual getFittest() {
		sort();
		
		return individuals.get(0);
	}
	
	private void createNewPopulation(int size, int geneLength, GeneticAlgorithm algorithm) {
		for(int i = 0; i < size; i++) {
			individuals.add(new Individual(geneLength, algorithm));
		}
	}
}
