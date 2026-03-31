package adduntiluserinputszero;
import java.util.Scanner;
public class AddUntilUserInputsZero {
	public static void main(String args[]) {
Scanner sc=new Scanner(System.in);
double number=-1,total=0.0d;
while(number!=0)
{
	System.out.println("Enter the number :");
	number=sc.nextDouble();
	total+=number;
}
System.out.println("Total is :"+total);
	}
}
