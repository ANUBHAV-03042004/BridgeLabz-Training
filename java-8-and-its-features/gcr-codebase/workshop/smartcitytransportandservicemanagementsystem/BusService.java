package smartcitytransportandservicemanagementsystem;

import java.util.List;
import java.util.Random;

public class BusService extends Vehicle implements TransportService,FareCalculator{
    String departureTime;
    int fare;

    BusService(String type, List<String> route, String departureTime, int fare) {
        super(type, route);
        this.departureTime = departureTime;
        this.fare = fare;
    }

    @Override
    public int getFare() {
        return fare;
    }

    @Override
    public String toString() {
        return type + " -> " + String.join(",", route) + " -> " + departureTime + " -> Fare: " + fare;
    }

	@Override
	public double calculateFare(double distance) {
		
		return distance * getFare();
	}
}