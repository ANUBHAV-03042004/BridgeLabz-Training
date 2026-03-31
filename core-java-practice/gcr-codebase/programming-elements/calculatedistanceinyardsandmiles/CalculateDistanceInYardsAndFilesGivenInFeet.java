package calculatedistanceinyardsandmiles;
import  java.util.Scanner;
public class CalculateDistanceInYardsAndFilesGivenInFeet {
public static void main(String args[])
{
	Scanner sc=new Scanner(System.in);
	System.out.println("Enter the distance in feets :");
	int distance_in_feets= sc.nextInt();
	double distanceinyards= distance_in_feets*0.333333;
	double distanceinmiles= distanceinyards*0.000568182;
	System.out.printf("Distance in Miles %f\n:",distanceinmiles);
	System.out.printf("Distance in Yards %f",distanceinyards);
}
}
