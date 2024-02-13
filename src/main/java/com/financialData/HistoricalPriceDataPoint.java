package com.financialData;

public class HistoricalPriceDataPoint {
	private double price;
	private String date;
	
	public HistoricalPriceDataPoint(double price, String date) {
		this.price = price;
		this.date = date;
	}
	
	public double getPrice() {
		return price;
	}

	public String getDate() {
		return date;
	}
	
	@Override
	public String toString() {
		return "Price: " + price + " Date: " + date;
	}
}
