package printmultiplicationtable;
import java.util.Scanner;
public class MultiplicationTable {
	public static void main(String args[])
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter a number :");
		int multiplied_number=sc.nextInt();
		int table[]=new int[10];
		for(int i=0;i<10;i++)
		{
			table[i]=multiplied_number*(i+1);
		}
		for(int i=0;i<10;i++) {
			System.out.printf("%d * %d = %d\n", multiplied_number , (i+1),table[i]);
		}
	}
}
