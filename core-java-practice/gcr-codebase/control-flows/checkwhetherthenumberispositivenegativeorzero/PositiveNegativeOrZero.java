package checkwhetherthenumberispositivenegativeorzero;
import java.util.Scanner;
public class PositiveNegativeOrZero {

	public static void main(String args[])
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the number :");
		int number=sc.nextInt();
		if(number>0)System.out.printf("Positive");
		else if(number<0) System.out.printf("Negative");
		else System.out.printf("Zero");
	}
	}

