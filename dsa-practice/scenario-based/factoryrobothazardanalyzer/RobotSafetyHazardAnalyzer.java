package factoryrobothazardanalyzer;

import java.util.HashMap;
import java.util.Scanner;

public class RobotSafetyHazardAnalyzer {
	public double CalculateHazardRisk(double armPrecision, int workerDensity, String machineryState) throws RobotSafetyException {
		if(armPrecision < 0.0d || armPrecision >1.0d) throw new RobotSafetyException("Error: Arm precision must be 0.0-1.0");
		if(workerDensity < 1 || workerDensity > 20) throw new RobotSafetyException("Error: Worker density must be 1-20");
		if(!machineryState.equalsIgnoreCase("worn")&& !machineryState.equalsIgnoreCase("faulty") && !machineryState.equalsIgnoreCase("critical")) {
throw new RobotSafetyException("Error: Unsupported machinery state");	
		}
		HashMap<String,Float> machineRiskFactor = new HashMap<>();
		machineRiskFactor.put("worn",1.3f);
		machineRiskFactor.put("faulty", 2.0f);
		machineRiskFactor.put("critical",3.0f);
		
		
		double hazardRisk = ((1.0 - armPrecision) * 15.0) + (workerDensity * machineRiskFactor.get(machineryState.toLowerCase())); 
		return hazardRisk;
	}
	public static void main(String[] args) throws RobotSafetyException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Arm Precision (0.0 - 1.0): ");
		double armPrecision = sc.nextDouble();
		System.out.println("Enter Worker Density (1 - 20):");
		int workerDensity = sc.nextInt();
		System.out.println("Enter Machinery State (Worn/Faulty/Critical):");
		String machineryState = sc.next();
		
		RobotSafetyHazardAnalyzer rsha = new RobotSafetyHazardAnalyzer();
		double result = rsha.CalculateHazardRisk(armPrecision, workerDensity, machineryState);
		System.out.println("Robot Hazard Risk Score: "+result);
		
	}
}
