package smartcitytransportandservicemanagementsystem;

import java.util.List;

public class Passenger {
    String name;
    String route;
    double location;
    Passenger(String name, String route) {
        this.name = name;
        this.route = route;
    }
    Passenger(String name,double location){
    	this.name=name;
    	this.location=location;
    }
    public void bookService(String choice, List<Object> service) {
        switch (choice.toLowerCase()) {
            case "ambulance" :
            	service.stream().filter(s-> s instanceof Ambulance).findFirst().ifPresent(s->{
            		Ambulance ambulance = (Ambulance)s;
            		System.out.println("Ambulance : "+ambulance);
            			 double distance = GeoUtils.calculateDistance(location, ambulance.location);
                         System.out.println("Distance: " + distance+" km");

            	});
            	break;
            case "fire station":
            	service.stream().filter(s-> s instanceof FireService).findFirst().ifPresent(
            			s->{
                    		FireService fireservice = (FireService)s;
                    		System.out.println("Fire Service: "+fireservice);
                    			 double distance = GeoUtils.calculateDistance(location, fireservice.location);
                                 System.out.println("Distance: " + distance+" km");

                    	}
            			);
            	break;
            case "bus":
            	service.stream()
                .filter(s -> s instanceof BusService)
                .findFirst()
                .ifPresent(s -> {
                    BusService bus = (BusService) s;
                    double fare = bus.calculateFare(10.0);
                    System.out.println("BusService: " + bus);
                    System.out.println("Total Fare: " + fare);
                });

                
                break;

            case "metro":
                service.stream()
                        .filter(s -> s instanceof MetroService)
                        .findFirst()
                        .ifPresent(s->{
                        	MetroService metro = (MetroService) s;
                        	double fare = metro.calculateFare(10.0);
                            System.out.println("BusService: " + metro);
                            System.out.println("Total Fare: " + fare);
                        });
                break;

            case "taxi":
                service.stream()
                        .filter(s -> s instanceof TaxiService)
                        .findFirst()
                        .ifPresent(s->{
                        	TaxiService taxi = (TaxiService) s;
                        	double fare = taxi.calculateFare(10.0);
                            System.out.println("BusService: " + taxi);
                            System.out.println("Total Fare: " + fare);
                        });
                break;

            default:
                System.out.println("Invalid choice. Please select Bus, Metro, or Taxi.");
        }
    }
}
