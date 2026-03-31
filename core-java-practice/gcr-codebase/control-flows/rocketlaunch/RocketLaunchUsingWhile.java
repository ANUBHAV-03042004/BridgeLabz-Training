package rocketlaunch;
import java.util.Scanner;
public class RocketLaunchUsingWhile {
	public static void main (String args[]) {
Scanner sc=new Scanner(System.in);
int countdown=sc.nextInt();
while(countdown>0)
{
	System.out.println("Value of counter :"+countdown);
	countdown--;
}
}
}