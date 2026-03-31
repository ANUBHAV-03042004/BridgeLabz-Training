package adduntiluserinputszerooranegativenumber;
import java.util.Scanner;
public class AddUntilZeroOrANegativeNumber {
	public static void main(String args[]) {
Scanner sc=new Scanner(System.in);
double total=0.0d;
while(true)
{
	System.out.println("Enter the number :");
	double number=sc.nextDouble();
	if(number<=0) break;
	total+=number;
}
System.out.println("Total is :"+total);
	}
}
