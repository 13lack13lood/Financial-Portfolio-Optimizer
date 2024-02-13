package com.application.portfolioOptimizer;

import java.util.HashMap;
import java.util.Map;

import com.financialData.FinancialDataCalculator;
import com.geneticAlgorithm.GeneticAlgorithm;

public class PortfolioOptimizer {

	private String[] tickers;
	
	private double[] optimizedPortfolio;
	private double expectedReturns;
	private double expectedRisk;

	public PortfolioOptimizer(String[] tickers) {
		this.tickers = tickers;
		
		FinancialDataCalculator dataCalculator = new FinancialDataCalculator(tickers);

		GeneticAlgorithm ga = new GeneticAlgorithm(dataCalculator);

		optimizedPortfolio = ga.runAlgorithm();

		expectedReturns = dataCalculator.calculateExpectedReturns(optimizedPortfolio);
		expectedRisk = Math.pow(dataCalculator.calculatePortfolioReturnStandardDevition(optimizedPortfolio), 2);
	}

	public Map<String, Double> getOptimizedPortfolio() {
		Map<String, Double> output = new HashMap<String, Double>();
		
		for(int i = 0; i < optimizedPortfolio.length; i++) {
			output.put(tickers[i], optimizedPortfolio[i]);
		}
		
		return output;
	}

	public double getExpectedReturns() {
		return expectedReturns;
	}

	public double getExpectedRisk() {
		return expectedRisk;
	}
}
