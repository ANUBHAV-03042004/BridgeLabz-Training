package stockpricelogger;

import java.util.Arrays;
import java.util.List;

class Stock {
    private String symbol;
    private double price;

    public Stock(String symbol, double price) {
        this.symbol = symbol;
        this.price = price;
    }

    public String getSymbol() { return symbol; }
    public double getPrice() { return price; }

    @Override
    public String toString() {
        return symbol + " → " + price;
    }
}
public class StockPrice {
public static void main(String[] args) {
	        List<Stock> stocks = Arrays.asList(
	            new Stock("RELIANCE", 2450.00),
	            new Stock("INFY", 1650.00),
	            new Stock("ADANIENT", 2020.00),
	            new Stock("APOLLOHOSP", 6950.00),
	            new Stock("ASIANPAINT", 3180.00)
	        );
	        stocks.stream()
	              .forEach(System.out::println);
}
}
