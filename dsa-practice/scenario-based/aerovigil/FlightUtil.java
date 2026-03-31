package aerovigil;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FlightUtil {
public boolean validateFlightNumber(String flightNumber) {
	try {
	String regex= "^FL\\-[1-9][0-9]{3}$";
	if(flightNumber.matches(regex)) return true;
	else  throw new  InvalidFlightException("The flight number "+flightNumber+" is invalid");
	}catch(InvalidFlightException e)
	{
		System.out.println(e.getMessage());
		return false;
	}
}
public boolean validateFlightName(String flightName){
	try {
	if(flightName.equalsIgnoreCase("SpiceJet") || flightName.equalsIgnoreCase("Vistara")
			|| flightName.equalsIgnoreCase("IndiGo")|| flightName.equalsIgnoreCase("Air Arabia")) return true;
	
	else  throw new  InvalidFlightException("The flight name "+flightName+" is invalid");
	}catch(InvalidFlightException e)
	{
		System.out.println(e.getMessage());
		return false;
	}
}
public boolean validatePassengerCount(int passengerCount, String flightName) {
	HashMap<String,Integer> flightcount= new HashMap<>();
	flightcount.put("SpiceJet",396);
	flightcount.put("Vistara",615);
	flightcount.put("IndiGo",230);
	flightcount.put("Air Arabia",130);
	try {
	if (validateFlightName(flightName)) {
		int count = flightcount.get(flightName);
		if(passengerCount<=0 || passengerCount>count) throw new InvalidFlightException("The passenger count "+passengerCount+" is invalid for "+flightName);
		
	}
	return true;
	}catch(InvalidFlightException e)
	{
		System.out.println(e.getMessage());
		return false;
	}
}
public double calculateFuelToFillTank(String flightName, double currentFuelLevel) throws InvalidFlightException {
	HashMap<String,Integer> flightfuel= new HashMap<>();
	flightfuel.put("SpiceJet",200000);
	flightfuel.put("Vistara",300000);
	flightfuel.put("IndiGo",250000);
	flightfuel.put("Air Arabia",150000);
	try {
	int fuelCapacity = 0;
	if (validateFlightName(flightName)) {
		 fuelCapacity = flightfuel.get(flightName);
		if(currentFuelLevel<=0.0d || currentFuelLevel>fuelCapacity) throw new InvalidFlightException("Invalid fuel level for "+ flightName);
		
	}
	double required_fuel_amount_to_filltank = fuelCapacity - currentFuelLevel;
	return required_fuel_amount_to_filltank ;
	}catch(InvalidFlightException e)
	{
		System.out.println(e.getMessage());
		throw e;
	}
}
}
