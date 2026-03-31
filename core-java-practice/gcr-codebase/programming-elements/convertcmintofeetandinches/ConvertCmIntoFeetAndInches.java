package convertcmintofeetandinches;
import java.util.Scanner;
public class ConvertCmIntoFeetAndInches {
	public static void main(String args[]) {
Scanner sc=new Scanner(System.in);
System.out.println("Enter your height in cm:");
int cm=sc.nextInt();
double inches= cm/2.54;
double feet= inches/12;
 System.out.printf("Your Height in cm is %d while in feet is %f and inches is %.2f",cm,feet,inches);
	}
}
