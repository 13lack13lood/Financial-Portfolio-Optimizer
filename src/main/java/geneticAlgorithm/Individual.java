package geneticAlgorithm;

import java.util.Arrays;

public class Individual {
	public static final int geneLength = 64;
	
	private byte[] genes;
	private int fitness;
	
	public Individual() {
		genes = new byte[geneLength];
		fitness = 0;
		
		for(int i = 0; i < geneLength; i++) {
			byte gene = (byte) Math.round(Math.random());
			genes[i] = gene;
		}
	}
	
	protected byte getGene(int index) {
		return genes[index];
	}
	
	
	protected void setGene(int index, byte gene) {
		genes[index] = gene;
	}
	
	public int getFitness() {
		if(fitness == 0) {
			fitness = GeneticAlgorithm.getFitness(this);
		}
		
		return fitness;
	}
	
	@Override
	public String toString() {
		return Arrays.toString(genes);
	}
}
