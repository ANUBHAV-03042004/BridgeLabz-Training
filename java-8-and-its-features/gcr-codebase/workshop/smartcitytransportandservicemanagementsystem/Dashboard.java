package smartcitytransportandservicemanagementsystem;

import java.util.*;
import java.util.stream.Collectors;

public class Dashboard {
    public static void main(String[] args) {
    	 Random random = new Random();

        // Transport services
        BusService bus = new BusService("Bus", List.of("Alambagh","Charbagh","Kaiserbagh","Burlington"), "08:15",random.nextInt(50,100));
        MetroService metro = new MetroService("Metro", List.of("Alambagh","Charbagh","Kaiserbagh","Burlington"), "05:45", random.nextInt(50,100));
        TaxiService taxi = new TaxiService("Taxi", List.of("Alambagh","Charbagh","Kaiserbagh","Burlington"), random.nextInt(50,100));
        
        BusService bus2 = new BusService("Bus", List.of("Alambagh","Kaiserbagh","Burlington"), "08:15", random.nextInt(50,100));
        MetroService metro2 = new MetroService("Metro", List.of("Alambagh","Kaiserbagh","Burlington"), "05:45", random.nextInt(50,100));
        TaxiService taxi2 = new TaxiService("Taxi", List.of("Alambagh","Kaiserbagh","Burlington"), random.nextInt(50,100));
        
        
        Ambulance ambulance = new Ambulance("Ambulance","IndraGandhi Hospital",192.25874);
        FireService fireService = new FireService("Fire Service","Fire Station",425.0285);

        List<TransportService> services = List.of(bus, metro, taxi,metro2,bus2,taxi2);
        List<Object> allServices = List.of(bus, metro, taxi, ambulance, fireService);

        // Passengers
        Passenger anmol = new Passenger("Anmol", "Charbagh");
        Passenger prakash = new Passenger("Prakash", "Alambagh");
        Passenger rahul = new Passenger("Rahul", "Charbagh");
        List<Passenger> passengers = List.of(anmol, prakash, rahul);

        
        // Sort services by fare
        List<TransportService> sortedServices = services.stream()
                .sorted(Comparator.comparingInt(TransportService::getFare))
                .collect(Collectors.toList());

        System.out.println("Available Services (Sorted by Fare):");
        sortedServices.forEach(System.out::println);

 
        List<Object> availableForAnmol = sortedServices.stream()
                .filter(s -> (s instanceof Vehicle) && ((Vehicle) s).route.stream()
                        .anyMatch(r -> r.equalsIgnoreCase(anmol.route)))
                .collect(Collectors.toList());

        
        
        
        
        System.out.println("\nServices available for passenger " + anmol.name + ":");
        availableForAnmol.forEach(System.out::println);

System.out.println();
System.out.println("Booked : ");
    	anmol.bookService("metro", availableForAnmol);
    	
    	
    	
        Map<String, List<Passenger>> passengersByRoute = passengers.stream()
                .collect(Collectors.groupingBy(p -> p.route));

        System.out.println("\nPassengers grouped by route:");
        passengersByRoute.forEach((route, list) ->
                System.out.println(route + " -> " + list.stream()
                        .map(p -> p.name)
                        .collect(Collectors.joining(", "))));

   
        Map<Boolean, List<TransportService>> peakPartition = services.stream()
                .collect(Collectors.partitioningBy(s -> {
                    if (s instanceof BusService) {
                        BusService b = (BusService) s;
                        return b.departureTime.compareTo("07:00") >= 0 && b.departureTime.compareTo("10:00") <= 0
                                || b.departureTime.compareTo("17:00") >= 0 && b.departureTime.compareTo("20:00") <= 0;
                    }
                    if (s instanceof MetroService) {
                        MetroService m = (MetroService) s;
                        return m.departureTime.compareTo("07:00") >= 0 && m.departureTime.compareTo("10:00") <= 0
                                || m.departureTime.compareTo("17:00") >= 0 && m.departureTime.compareTo("20:00") <= 0;
                    }
                    return false;
                }));

        System.out.println("\nPeak Time Services:");
        peakPartition.get(true).forEach(System.out::println);

        System.out.println("Non-Peak Time Services:");
        peakPartition.get(false).forEach(System.out::println);

        DoubleSummaryStatistics fareStats = services.stream()
                .collect(Collectors.summarizingDouble(TransportService::getFare));

        System.out.println("\nFare Statistics:");
        System.out.println("Total Revenue: " + fareStats.getSum());
        System.out.println("Average Fare: " + fareStats.getAverage());
        System.out.println("Min Fare: " + fareStats.getMin());
        System.out.println("Max Fare: " + fareStats.getMax());

  
      
        bus.printServiceDetails();

   

     List<Object> prioritizedServices = allServices.stream()
             .sorted((s1, s2) -> {
                 boolean e1 = s1 instanceof EmergencyService;
                 boolean e2 = s2 instanceof EmergencyService;
                 if (e1 && !e2) return -1;   // Emergency first
                 if (!e1 && e2) return 1;
                 return 0; 
             })
             .collect(Collectors.toList());

     System.out.println("Prioritized Services (Emergency First):");
     prioritizedServices.forEach(System.out::println);

     List<Object> emergencyOnly = allServices.stream()
             .filter(s -> s instanceof EmergencyService)
             .collect(Collectors.toList());

     System.out.println("\nEmergency Services Detected:");
     emergencyOnly.forEach(System.out::println);
     
     System.out.println();
     Passenger Sahil = new Passenger("Sahil",184.265);
     Sahil.bookService("ambulance", emergencyOnly);

    }
}