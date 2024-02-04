package geneticAlgorithm;

import java.util.Arrays;

import util.MathUtil;

public class Individual {
	
	private double[] genes;
	private int fitness;
	
	public Individual(int geneLength) {
		double[] randomArray = MathUtil.randomArray(geneLength);
		genes = MathUtil.normalize(randomArray);
		
		fitness = 0;
	}
	
	protected double getGene(int index) {
		return genes[index];
	}
	
	
	protected void setGene(int index, double gene) {
		genes[index] = gene;
	}
	
	protected int getFitness(GeneticAlgorithm algorithm) {
		if(fitness == 0) {
			fitness = algorithm.getFitness(this);
		}
		
		return fitness;
	}
	
	@Override
	public String toString() {
		return Arrays.toString(genes);
	}
}
