package basiccalculator;
import java.util.Scanner;
public class BasicCalculator {
public static void main(String args[])
{
	Scanner sc=new Scanner(System.in);
	float number1= sc.nextFloat();
	float number2= sc.nextFloat();
	
	float addition=number1 + number2;
	float substraction= number1 - number2;
	float multiply= number1 * number2 ;
	float division= number1 / number2 ;
	System.out.printf("The addition, subtraction, multiplication and division value of 2 numbers %f and %f is %f, %f, %f, and %f",number1,number2,addition,substraction,multiply,division);
}
}
