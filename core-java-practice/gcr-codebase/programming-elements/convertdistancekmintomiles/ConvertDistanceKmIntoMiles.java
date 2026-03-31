package convertdistancekmintomiles;
import java.util.Scanner;
public class ConvertDistanceKmIntoMiles {
	public static void main(String args[]) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the distance in km : ");
		double km= sc.nextDouble();
		System.out.printf("The total miles is %f mile for the given %f km\r\n",(km*0.621371),km);
	}
}
