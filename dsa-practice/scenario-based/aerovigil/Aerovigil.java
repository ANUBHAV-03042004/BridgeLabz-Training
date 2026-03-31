package aerovigil;

import java.util.Scanner;

public class Aerovigil {
public static void main(String[] args) {
	Scanner sc= new Scanner(System.in);
	System.out.println("Enter Flight Details in <FlightNumber>:<FlightName>:<PassengerCount>:<CurrentFuel Level>. format : ");
	String input= sc.nextLine();
	String flightDetails[]=input.split(":");
	String FlightNumber = flightDetails[0];
	String FlightName = flightDetails[1];
	String PassengerCount = flightDetails[2];
	String currentFuelLevel = flightDetails[3];
	FlightUtil fu= new FlightUtil();
	 try {
	 if(!fu.validateFlightNumber(FlightNumber)) return ;
	 if(!fu. validateFlightName(FlightName)) return ;
	  if(!fu.validatePassengerCount(Integer.parseInt(PassengerCount), FlightName)) return ;

		Double ans  = fu.calculateFuelToFillTank(FlightName,Double.parseDouble(currentFuelLevel));
		System.out.println(ans);
		
	} catch (NumberFormatException e) {
		e.printStackTrace();
		return;
	} catch (InvalidFlightException e) {
		e.printStackTrace();
		return ;
	}
}
}
