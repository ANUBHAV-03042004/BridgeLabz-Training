package personcanvote;
import java.util.Scanner;
public class PersonCanVote {
public static void main(String args[])
{
	Scanner sc=new Scanner(System.in);
	int students[]=new int[10];
	for(int i=0;i<10;i++)
	{
		System.out.printf("Enter age of student %d:",(i+1));
		students[i]=sc.nextInt();
		if(students[i]<0) System.out.println("Invalid age");
		if(students[i]>=18) System.out.printf("The student with the age %d can vote.\n",students[i]);
		else if(students[i]<18) System.out.printf("The student with the age %d cannot vote.\n",students[i]);
	}
}
}
