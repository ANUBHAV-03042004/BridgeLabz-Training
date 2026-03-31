package oceanfleet;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public class VesselUtil {
	private List<Vessel> vesselList = new ArrayList<>();

	public List<Vessel> getVesselList() {
		return vesselList;
	}
	public void addVesselPerformance(Vessel vessel) {
		vesselList.add(vessel);
	}
	public Vessel getVesselById(String vesselId) {
	     for(Vessel v: vesselList) {
	    	 if(v.vesselId.equals(vesselId)) return v;
	     }
	    	 return null;
	}
	public List<Vessel> getHighPerformanceVessels(){
	    double maxSpeed = vesselList.stream()
                .mapToDouble(Vessel::getAverageSpeed)
                .max()
                .orElse(Double.NaN); 
	    
		return vesselList.stream()
                .filter(v -> v.getAverageSpeed() == maxSpeed)
                .collect(Collectors.toList());

	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the number of vessel to be added : ");
		int numberOfvessel = sc.nextInt();
		sc.nextLine();
		
		VesselUtil vu = new VesselUtil();
		String vesselId ="";
		String vesselName="";
		String vesselType="";
		double vesselaverageSpeed= 0.0d;
		System.out.println("Enter input : ");
		while(numberOfvessel-->0) {
		String input = sc.nextLine();
		String vessel[]=input.split(":");
		vesselId = vessel[0];
	    vesselName = vessel[1];
		vesselaverageSpeed = Double.parseDouble(vessel[2]);
		vesselType = vessel[3];
		
		Vessel v = new Vessel(vesselId, vesselName, vesselaverageSpeed, vesselType);
		vu.addVesselPerformance(v);
		}
		System.out.println();
		System.out.println("Enter VesselId to Check :");
		String currentvesselId = sc.nextLine();
		Vessel vcheck = vu.getVesselById(currentvesselId);
		if( vcheck == null)System.out.printf("VesselId %s not found",currentvesselId);
		else System.out.printf("%s | %s | %s | %f knots ",vcheck.getVesselId(),vcheck.getVesselName(),vcheck.getVesselType(),vcheck.getAverageSpeed());
		System.out.println();
		List<Vessel> HighPerformanceVessel = vu.getHighPerformanceVessels();
		HighPerformanceVessel.forEach(System.out::println);
		
	}
	
}
