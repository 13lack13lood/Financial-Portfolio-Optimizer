package geneticAlgorithm;

import java.util.LinkedList;
import java.util.List;

public class Population {
	private List<Individual> individuals;
	
	public Population(int size, boolean createNew) {
		individuals = new LinkedList<Individual>();
		
		if(createNew) {
			
		}
	}
	
	protected List<Individual> getIndividuals() {
		return individuals;
	}
	
	protected Individual getFittest() {
		Individual fittest = individuals.get(0);
		
		for(Individual individual : individuals) {
			if(fittest.getFitness() < individual.getFitness()) {
				fittest = individual;
			}
		}
		
		return fittest;
	}
	
	private void createNewPopulation(int size) {
		for(int i = 0; i < size; i++) {
			individuals.add(new Individual());
		}
	}
}
