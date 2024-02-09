package financialData;

import util.MathUtil;

public class FinancialDataCalculator {
	
	private String[] tickers;
	private double[][] historicalReturns;
	private double[][] covarience;
	private double[] mean;
	private double[] standardDeviation;
	
	public FinancialDataCalculator(String[] tickers) {
		this.tickers = tickers;

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
	
}
