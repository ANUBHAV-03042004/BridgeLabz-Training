package smartcitytransportandservicemanagementsystem;

import java.util.List;

public class TaxiService extends Vehicle implements TransportService , FareCalculator{
    int fare;

    TaxiService(String type, List<String> route, int fare) {
        super(type, route);
        this.fare = fare;
    }

    @Override
    public int getFare() {
        return fare;
    }

    @Override
    public String toString() {
        return type + " -> " + String.join(",", route) + " -> Fare: " + fare;
    }

    @Override
	public double calculateFare(double distance) {
		
		return distance * getFare();
	}
}