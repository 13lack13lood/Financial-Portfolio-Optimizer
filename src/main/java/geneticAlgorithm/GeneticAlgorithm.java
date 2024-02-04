package geneticAlgorithm;

public class GeneticAlgorithm {
	private static final double uniformRate = 0.5;
	private static final double mutationRate = 0.025;
	private static final int tournamentSize = 1000;
	private static final int elitismOffset = 1;
	
	private double[] solution;
	private int populationSize;
	private int maxFitness;
	private int geneLength;
	
	public GeneticAlgorithm(int populationSize, int geneLength) {
		this.populationSize = populationSize;
		this.geneLength = geneLength;
		
		maxFitness = this.solution.length;
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
	
	protected int getFitness(Individual individual) {
		int fitness = 0;
		
		for(int i = 0; i < geneLength; i++) {
			if(individual.getGene(i) == solution[i]) {
				fitness++;
			}
		}
		
		return fitness;
	}
	
//	private void setSolution(String solutionString) {
//		solution = new double[solutionString.length()];
//		
//		for(int i = 0; i < solutionString.length(); i++) {
//			solution[i] = Byte.parseByte(Character.toString(solutionString.charAt(i)));
//		}
//	}
}
