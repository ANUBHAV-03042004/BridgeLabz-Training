package m1practiceproblem.set1.globalshipmentmanifestvalidator;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Scanner;

public class GlobalShipmentManifestValidator {
	public static boolean validate(String shipWithCode) {
		String regex = "^SHIP-(?!.*(\\d)\\1{3})[1-9]\\d{5}$";
		
		if(shipWithCode.matches(regex)) return true;
		else return false;
	}
	public static boolean validateDate(String date) {
		try {
		LocalDate localdate = LocalDate.parse(date);
		int year = localdate.getYear();
		if(year >= 2000 && year <= 2099) return true;
		 } catch (DateTimeParseException e) {
			return false;
		}
		return false;
	}
	public static boolean validateMode(String mode) {
		HashMap<String,Boolean> hasMode = new HashMap<>();
		hasMode.put("air",true);
		hasMode.put("sea", true);
		hasMode.put("road", true);
		hasMode.put("rail",true);
		hasMode.put("express",true);
		hasMode.put("freight",true);
		mode = mode.toLowerCase();
		if(hasMode.containsKey(mode)) return true;
		else return false;
	}
	public static boolean validateWeight(String weight) {
	    if (!weight.matches("^(0|[1-9]\\d*)(\\.\\d{1,2})?$")) return false;
	    try {
	        double value = Double.parseDouble(weight);
	        return value >= 0 && value <= 999999.99;
	    } catch (NumberFormatException e) {
	        return false;
	    }
	}
	public static boolean validateDeliveryStatus(String deliveryStatus) {
		HashMap<String , Boolean> status = new HashMap<>();
		status.put("DELIVERED",true);
		status.put("CANCELLED",true);
		status.put("IN_TRANSIT",true);
		
		deliveryStatus= deliveryStatus.trim().toUpperCase();
		if(status.containsKey(deliveryStatus)) return true;
		return false;
	}
public static void main(String[] args) {
	Scanner sc = new Scanner(System.in);
	int noOfTime = sc.nextInt();
	sc.nextLine();
	while(noOfTime-->0) {
		String input = sc.next();
		String inputArray[]=input.split("\\|");
		String shipWithCode = inputArray[0];
		String shipDate = inputArray[1];
		String mode = inputArray[2];
		String weight = inputArray[3];
		String deliveryStatus = inputArray[4];
		
		if(!validate(shipWithCode)) System.out.println("NON-COMPLIANT RECORD");
		else if(!validateDate(shipDate))  System.out.println("NON-COMPLIANT RECORD");
		else if(!validateMode(mode))  System.out.println("NON-COMPLIANT RECORD");
		else if(!validateWeight(weight))  System.out.println("NON-COMPLIANT RECORD");
		else if(!validateDeliveryStatus(deliveryStatus))  System.out.println("NON-COMPLIANT RECORD");
		else System.out.println("COMPLIANT RECORD");
	}
}
}
