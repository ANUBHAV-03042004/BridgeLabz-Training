package doubleoperation;
import java.util.Scanner;
public class DoubleOperation {
public static void main(String args[]) {
	Scanner sc=new Scanner(System.in);
	System.out.println("Enter value of a :");
	double a= sc.nextDouble();
	System.out.println("Enter value of b :");
	double b=sc.nextDouble();
	System.out.println("Enter value of c :");
	double c=sc.nextDouble();
	double result= a + b * c;
	System.out.println("result of a + b * c :"+ result);
	result = a * b + c;
	System.out.println("result of a * b + c :"+ result);
	result= c + a / b;
	System.out.println("result of c + a / b :"+ result);
	result= a % b + c;
	System.out.println("result of a % b + c :"+ result);
	
}
}
