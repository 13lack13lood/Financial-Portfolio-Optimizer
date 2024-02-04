import financialData.FinancialData;
import financialData.HistoricalPriceData;

public class Main {

	public static void main(String[] args) {
		HistoricalPriceData aaplData = FinancialData.getHistoricalData("aapl");

	}

}
