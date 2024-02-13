package financialData;

import util.MathUtil;

public class FinancialDataCalculator {
	
	private static double RISK_FREE_FACTOR = 0.0544;
	
	private int numberOfStocks;
	
	private double[][] historicalReturns;
	private double[][] covarience;
	private double[] mean;
	private double[] standardDeviation;
	
	public FinancialDataCalculator(String[] tickers) {
		numberOfStocks = tickers.length;
		
		historicalReturns = FinancialData.getVariousHistoricalReturns(tickers);
		
		covarience = MathUtil.calculateCovarience(historicalReturns);
		mean = MathUtil.calculateMean(historicalReturns);
		standardDeviation = MathUtil.calculateStandardDeviation(historicalReturns);
	}
	
	
	public double calculateExpectedReturns(double[] portfolio) {
		double sum = 0;
		
		for(int i = 0; i < portfolio.length; i++) {
			sum += portfolio[i] * mean[i];
		}
		
		return sum;
	}
	
	public double calculatePortfolioReturnStandardDevition(double[] portfolio) {
		double output = 0;
		
		for(int i = 0; i < portfolio.length; i++) {
			output += Math.pow(portfolio[i] * standardDeviation[i], 2);
		}
		
		for(int i = 0; i < portfolio.length; i++) {
			for(int j = 0; j < portfolio.length; j++) {
				output += covarience[i][j] * portfolio[i] * portfolio[j];
			}
		}
		
		return Math.sqrt(output);
	}
	
	public double calculateSharpeRatio(double[] portfolio) {
		return (calculateExpectedReturns(portfolio) - RISK_FREE_FACTOR) / calculatePortfolioReturnStandardDevition(portfolio);
	}
	
	public int getNumberOfStocks() {
		return numberOfStocks;
	}
}
