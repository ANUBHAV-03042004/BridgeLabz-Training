package factorialofanumber;

import java.util.Scanner;
public class FactorialOfANumber {
	public static void main(String args[]) {
Scanner sc=new Scanner(System.in);
int number=sc.nextInt(),factorial=1,copynumber=number;
while(number>0) factorial*=number--;
System.out.printf("Factorial of %d is : %d",copynumber,factorial);
	}
}
