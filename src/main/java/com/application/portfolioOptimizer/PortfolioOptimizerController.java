package com.application.portfolioOptimizer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "portfolio")
public class PortfolioOptimizerController {

	@GetMapping()
	public PortfolioOptimizer getOptimizedPortfolio() {
		String[] tickers = { "aapl", "amzn", "googl", "nvda", "msft", "v", "tsla", "meta", "cost", "jnj", "unh" };

		PortfolioOptimizer po = new PortfolioOptimizer(tickers);

		return po;
	}

}
