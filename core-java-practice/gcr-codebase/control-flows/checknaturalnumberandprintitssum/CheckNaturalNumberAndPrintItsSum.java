package checknaturalnumberandprintitssum;
import java.util.Scanner;
public class CheckNaturalNumberAndPrintItsSum {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int natural_number;
		System.out.println("Enter a natural number :");
		 natural_number=sc.nextInt();
		if(natural_number>0)System.out.printf("The sum of %d natural numbers is %d",natural_number,(natural_number *(natural_number-1))/2);
		else  System.out.printf("The number %d is not a natural number.\n",natural_number);
		
	}

}
