package displaywhetherthenumberisevenorodd;
import java.util.Scanner;
public class DisplayWhetherTheNumberIsEvenOrOdd {
	public static void main(String args[]) {
Scanner sc=new Scanner(System.in);
System.out.println("Enter a number");
int number=sc.nextInt();
if(number<=0) System.out.printf("%d is not a natural number",number);
for(int i=1;i<=number;i++)
{
	if(i%2==0) System.out.printf("%d is even\n",i);
	else System.out.printf("%d is odd\n",i);
}
	}
}
