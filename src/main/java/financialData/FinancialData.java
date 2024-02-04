package financialData;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONArray;
import org.json.JSONObject;

public class FinancialData {
	public static HistoricalPriceData getHistoricalData(String ticker) {
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
			String date = data.getString("date");
			
			historicalPriceData.add(new HistoricalPriceDataPoint(price, date));
		}
		
		return historicalPriceData;
	}
}
