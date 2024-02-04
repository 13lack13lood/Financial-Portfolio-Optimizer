package financialData;
import java.util.LinkedList;
import java.util.List;

public class HistoricalPriceData {
	private List<HistoricalPriceDataPoint> data;
	
	public HistoricalPriceData() {
		data = new LinkedList<HistoricalPriceDataPoint>();
	}
	
	public void add(HistoricalPriceDataPoint dataPoint) {
		data.add(dataPoint);
	}
	
	public List<HistoricalPriceDataPoint> getData() {
		return data;
	}
}
