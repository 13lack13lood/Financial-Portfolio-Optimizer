package geneticAlgorithm;

import java.util.ArrayList;
import java.util.List;

public class Population {
	private List<Individual> individuals;
	private int size;
	
	public Population(int size, int geneLength, boolean createNew) {
		individuals = new ArrayList<Individual>();
		
		this.size = size;
		
		if(createNew) {
			createNewPopulation(size, geneLength);
		}
	}
	
	protected List<Individual> getIndividuals() {
		return individuals;
	}
	
	protected int getSize() {
		return size;
	}
	
	protected Individual getFittest(GeneticAlgorithm algorithm) {
		Individual fittest = individuals.get(0);
		
		for(Individual individual : individuals) {
			if(fittest.getFitness(algorithm) < individual.getFitness(algorithm)) {
				fittest = individual;
			}
		}
		
		return fittest;
	}
	
	private void createNewPopulation(int size, int geneLength) {
		for(int i = 0; i < size; i++) {
			individuals.add(new Individual(geneLength));
		}
	}
}
