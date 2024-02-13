package portfolioOptimizer;

import financialData.FinancialDataCalculator;
import geneticAlgorithm.GeneticAlgorithm;

public class PortfolioOptimizer {

	private double[] optimizedPortfolio;
	private double expectedReturns;
	private double expectedRisk;

	public PortfolioOptimizer(String[] tickers) {
		FinancialDataCalculator dataCalculator = new FinancialDataCalculator(tickers);

		GeneticAlgorithm ga = new GeneticAlgorithm(dataCalculator);

		optimizedPortfolio = ga.runAlgorithm();

		expectedReturns = dataCalculator.calculateExpectedReturns(optimizedPortfolio);
		expectedRisk = Math.pow(dataCalculator.calculatePortfolioReturnStandardDevition(optimizedPortfolio), 2);
	}

	public double[] getOptimizedPortfolio() {
		return optimizedPortfolio;
	}

	public double getExpectedReturns() {
		return expectedReturns;
	}

	public double getExpectedRisk() {
		return expectedRisk;
	}
}
