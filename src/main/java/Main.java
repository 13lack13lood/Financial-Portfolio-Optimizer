import java.util.Arrays;

import portfolioOptimizer.PortfolioOptimizer;

public class Main {

	public static void main(String[] args) {
		String[] tickers = {"aapl", "amzn", "googl", "nvda", "msft", "v", "tsla", "meta", "cost", "jnj", "unh"};

		PortfolioOptimizer po = new PortfolioOptimizer(tickers);
		
		System.out.println(Arrays.toString(po.getOptimizedPortfolio()));
		System.out.println(po.getExpectedReturns());
		System.out.println(po.getExpectedRisk());
	}

}
