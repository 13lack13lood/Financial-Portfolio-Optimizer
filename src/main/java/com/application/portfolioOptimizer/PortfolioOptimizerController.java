package com.application.portfolioOptimizer;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping(path = "portfolio")
public class PortfolioOptimizerController {

	@PostMapping(consumes = "application/json", produces = "application/json")
	public PortfolioOptimizer getOptimizedPortfolio(@RequestBody String[] tickers) throws Exception{
		PortfolioOptimizer po = new PortfolioOptimizer(tickers);

		return po;

	}

}
