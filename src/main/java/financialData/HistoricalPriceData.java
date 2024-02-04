package financialData;
import java.util.LinkedList;

public class HistoricalPriceData {
	private LinkedList<HistoricalPriceDataPoint> data;
	
	public HistoricalPriceData() {
		data = new LinkedList<HistoricalPriceDataPoint>();
	}
	
	public void add(HistoricalPriceDataPoint dataPoint) {
		data.add(dataPoint);
	}
	
	public LinkedList<HistoricalPriceDataPoint> getData() {
		return data;
	}
	
	@Override
	public String toString() {
		return data.toString();
	}
}
