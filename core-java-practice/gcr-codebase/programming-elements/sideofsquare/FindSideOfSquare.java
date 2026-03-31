package sideofsquare;
import java.util.Scanner;
public class FindSideOfSquare {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.print("Enter the perimeter of square :");
		
		int perimeter= sc.nextInt();
		double  side =(double) perimeter / 4;
		System.out.printf("The length of the side is %.2f whose perimeter is %d",side,perimeter);

	}

}
