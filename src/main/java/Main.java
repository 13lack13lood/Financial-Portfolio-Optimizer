import java.time.LocalDateTime;
import java.util.Arrays;

import financialData.FinancialData;
import financialData.HistoricalPriceData;
import geneticAlgorithm.GeneticAlgorithm;
import util.MathUtil;

public class Main {

	public static void main(String[] args) {
		String[] tickers = {"aapl", "amzn", "googl", "nvda", "msft", "v", "tsla", "meta", "cost", "jnj", "unh"};
//		
//		HistoricalPriceData aaplData = FinancialData.getHistoricalData("aapl");
//		System.out.println(aaplData);
		
//		GeneticAlgorithm ga = new GeneticAlgorithm(10000, "");
//		
//		ga.runAlgorithm();
		
//		double[] randomizedArray = MathUtil.randomArray(tickers.length);
//		double[] normalized = MathUtil.normalize(randomizedArray);
//		
//		System.out.println(Arrays.toString(normalized));
		
		System.out.println(Arrays.deepToString(FinancialData.getVariousHistoricalReturns(tickers)));
		
		
	}

}
