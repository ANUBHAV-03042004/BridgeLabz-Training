package temperaturealertsystem;

import java.util.Scanner;
import java.util.function.Predicate;

public class TemperatureAlertSystem {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Predicate<Double> temperaturecheck = x-> x > 50;
		System.out.println("Enter the input : ");
		double input = sc.nextDouble();
		if(temperaturecheck.test(input)) {
			System.out.println("Crossed Threshold");
		}
		else {
			System.out.println("Not crossed");
		}
	}

}
