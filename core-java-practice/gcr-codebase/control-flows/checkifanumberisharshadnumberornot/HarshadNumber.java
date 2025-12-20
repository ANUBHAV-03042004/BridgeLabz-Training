package checkifanumberisharshadnumberornot;
import java.util.Scanner;
public class HarshadNumber {
public static void main(String args[])
{
	Scanner sc=new Scanner(System.in);
	System.out.println("Enter a number :");
//	copied the number for comparison
	int number= sc.nextInt(),sum=0,copynumber=number;
//	extracting sum of digits
	while(number>0) {
		sum+=number%10;
		number/=10;
	}
	if(copynumber%sum==0)System.out.println("It's a Harshad Number");
	else System.out.println("It's not a Harshad Number");
}
}
