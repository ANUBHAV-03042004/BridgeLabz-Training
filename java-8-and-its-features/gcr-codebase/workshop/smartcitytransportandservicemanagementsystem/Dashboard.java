package smartcitytransportandservicemanagementsystem;

import java.util.List;
import java.util.Random;
import java.util.random.*;
public class Dashboard {
	void showVehicles() {
		
	}
public static void main(String[] args) {
	Random random = new Random();
	double busprice = random.nextInt(50,100);
	BusService bus = new BusService("bus",List.of("alambagh","charbagh","kaiserbagh","burlington"),"02:05",busprice);
	double metroprice = random.nextInt(50,100);
	MetroService metro = new MetroService("metro",List.of("alambagh","charbagh","kaiserbagh","burlington"),"05:45",metroprice);
	TaxiService taxi = new TaxiService("taxi",List.of("alambagh","charbagh","kaiserbagh","burlington"));
	Ambulance ambulance = new Ambulance("ambulance","IndraGandhi Hospital",192.25874);
	FireService fireService = new FireService("Fire Service","Fire Station",425.0285);
	
	  Passenger anmol = new Passenger("anmol", "charbagh");
	  Passenger prakash = new Passenger("prakash",192.756208);
	  
	  
	  
	  
	
}
}
