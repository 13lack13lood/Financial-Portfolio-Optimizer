package financialData;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.json.JSONArray;
import org.json.JSONObject;

public class FinancialData {
	
	private final static int[] TIME_FRAMES = {3, 6, 12, 24, 36};
	
	private final static DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	
	private static HistoricalPriceData getHistoricalData(String ticker) {
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://financialmodelingprep.com/api/v3/historical-price-full/" + ticker + "?apikey=sc4W3hS908VlJfuQ3y5ybtpJDpcugPKz"))
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.build();
		
		
		HttpResponse<String> res = null;
		
		try {
			res = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		
		JSONObject jsonObject = new JSONObject(res.body());
		
		JSONArray historicalData = jsonObject.getJSONArray("historical");
		
		HistoricalPriceData historicalPriceData = new HistoricalPriceData();
		
		for(int i = 0; i < historicalData.length(); i++) {
			JSONObject data = historicalData.getJSONObject(i);
			
			double price = data.getDouble("close");
			String date = data.getString("date").replaceAll("'", "");
			
			historicalPriceData.add(new HistoricalPriceDataPoint(price, date));
		}
		
		return historicalPriceData;
	}
	
	private static HistoricalPriceData getHistoricalDataInTimeFrame(HistoricalPriceData priceData, int months) {
		LocalDate endDate = LocalDate.now();
		LocalDate startDate = endDate.minusMonths(months);
				
		HistoricalPriceData output = new HistoricalPriceData();
		
		for(HistoricalPriceDataPoint dataPoint : priceData.getData()) {
			LocalDate currentDate = LocalDate.parse(dataPoint.getDate(), DATE_FORMATTER);
			
			if(startDate.compareTo(currentDate) > 0) {
				output.add(dataPoint);
				break;
			}
			
			output.add(dataPoint);
		}
		
		return output;
	}
	
	private static double getHistoricalReturn(HistoricalPriceData priceData) {
		double end = priceData.getData().getFirst().getPrice();
		double start = priceData.getData().getLast().getPrice();

		return (end - start) / start;
	}
	
	protected static double[][] getVariousHistoricalReturns(String[] tickers) {
		double[][] dataframe = new double[tickers.length][TIME_FRAMES.length];
		
		for(int i = 0; i < tickers.length; i++) {			
			HistoricalPriceData priceData = getHistoricalData(tickers[i]);
			
			for(int j = 0; j < TIME_FRAMES.length; j++) {				
				HistoricalPriceData timePriceData = getHistoricalDataInTimeFrame(priceData, TIME_FRAMES[j]);
				
				double returns = getHistoricalReturn(timePriceData);
				
				dataframe[i][j] = returns;
			}
		}
		
		return dataframe;
	}
	
}
