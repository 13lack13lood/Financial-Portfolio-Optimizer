package financialData;

import util.MathUtil;

public class FinancialDataCalculator {
	
	private static double RISK_FREE_FACTOR = 5.44;
	
	private double[][] historicalReturns;
	private double[][] covarience;
	private double[] mean;
	private double[] standardDeviation;
	
	public FinancialDataCalculator(String[] tickers) {
		historicalReturns = FinancialData.getVariousHistoricalReturns(tickers);
		
		covarience = MathUtil.calculateCovarience(historicalReturns);
		mean = MathUtil.calculateMean(historicalReturns);
		standardDeviation = MathUtil.calculateStandardDeviation(historicalReturns);
	}
	
	
	private double calculateExpectedReturns(double[] portfolio) {
		double sum = 0;
		
		for(int i = 0; i < portfolio.length; i++) {
			sum += portfolio[i] * mean[i];
		}
		
		return sum;
	}
	
	private double calculatePortfolioReturnStandardDevition(double[] portfolio) {
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
}
