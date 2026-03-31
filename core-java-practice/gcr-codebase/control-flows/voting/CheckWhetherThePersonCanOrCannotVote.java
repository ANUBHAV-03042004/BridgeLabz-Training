package voting;
import java.util.Scanner;
public class CheckWhetherThePersonCanOrCannotVote {
public static void main(String args[])
{
	Scanner sc=new Scanner(System.in);
	System.out.println("Enter the age :");
	int age=sc.nextInt();
	if(age>=18)System.out.printf("The person's age is %d and can vote.\r\n",age);
	else System.out.printf("The person's age is %d and cannot vote.\r\n",age);
}
}
