package rocketlaunch;
import java.util.Scanner;
public class RocketLaunchUsingFor {
	public static void main (String args[]) {
Scanner sc=new Scanner(System.in);
int countdown=sc.nextInt();
for(int i=countdown;i>0;i--)
{
	System.out.println("Value of counter :"+i);
}
}
}