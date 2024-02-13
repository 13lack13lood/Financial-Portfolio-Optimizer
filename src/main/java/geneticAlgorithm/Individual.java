package geneticAlgorithm;

import java.util.Arrays;

import util.MathUtil;

public class Individual implements Comparable<Individual> {
	
	private double[] genes;
	private double fitness;
	
	public Individual(int geneLength, GeneticAlgorithm algorithm) {
		double[] randomArray = MathUtil.randomArray(geneLength);
		genes = MathUtil.normalize(randomArray);
		
		fitness = algorithm.getFitness(this);
	}
	
	protected double[] getGenes() {
		return genes;
	}
	
	protected double getGene(int index) {
		return genes[index];
	}
	
	protected void setGene(int index, double gene) {
		genes[index] = gene;
	}
	
	protected double getFitness() {
		return fitness;
	}
	
	@Override
	public String toString() {
		return Arrays.toString(genes);
	}

	public int compareTo(Individual o) {
		return Double.compare(o.getFitness(), fitness);
	}
	

}
