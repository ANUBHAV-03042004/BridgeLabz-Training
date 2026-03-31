package positivenegativezeroorevenoddorfirstandlastelementequal;
import java.util.Scanner;
public class PositiveNegativeOrZeroOrEvenOrOddOrFirstAndLastElementAreEqual {
	public static void main(String args[])
	{
		Scanner sc=new Scanner(System.in);
		int number[]=new int[5];
		for(int i=0;i<5;i++)
		{
			System.out.printf("Enter number %d:",(i+1));
			number[i]=sc.nextInt();
			if(number[i]<0) System.out.println("Number is Negative");
			if(number[i]>0) {
				System.out.println("Number is positive");
				if(number[i]%2==0) System.out.println("Number is Even");
				else System.out.println("Number is Odd");
			}
			else if(number[i]==0) System.out.println("Number is Zero");
		}
		if(number[0]==number[4]) System.out.println("First and last element are equal.");
		else System.out.println("First and Last Element are not equal.");
		
	}
}
