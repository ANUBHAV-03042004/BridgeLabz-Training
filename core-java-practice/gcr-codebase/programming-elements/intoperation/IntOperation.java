package intoperation;
import java.util.Scanner;
public class IntOperation {
public static void main(String args[]) {
	Scanner sc=new Scanner(System.in);
	System.out.println("Enter value of a :");
	int a= sc.nextInt();
	System.out.println("Enter value of b :");
	int b=sc.nextInt();
	System.out.println("Enter value of c :");
	int c=sc.nextInt();
	int result= a + b * c;
	System.out.println("result of a + b * c :"+ result);
	result = a * b + c;
	System.out.println("result of a * b + c :"+ result);
	result= c + a / b;
	System.out.println("result of c + a / b :"+ result);
	result= a % b + c;
	System.out.println("result of a % b + c :"+ result);
	
}
}
