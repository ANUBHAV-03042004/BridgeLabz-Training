package studenttestscore;
import java.util.Scanner;
public class StudentTestScore {
public static void main(String args[])
{
	Scanner sc=new Scanner(System.in);
	System.out.println("Enter the number of students :");
	int noOfstudents=sc.nextInt();
	int studentmarks[]=new int[noOfstudents];
	int marks,index=0,sum=0,lowest=100,highest=0;
	while(index< noOfstudents)
	{
		System.out.println("Enter marks between 0 - 100: ");
		if(sc.hasNextInt()) {
		marks=sc.nextInt();
		if(marks>=0 && marks<=100) {
			studentmarks[index++]=marks;
			sum+=marks;
			lowest=Math.min(marks,lowest);
			highest=Math.max(marks,highest);
		}
		else {
			System.out.println("Invalid Input");
		}
		}
		else {
			System.out.println("Invalid Input");
			sc.next();
		}
	}
	int avg=sum/noOfstudents;
	System.out.println("Average marks : "+avg);
	System.out.println("Lowest marks : "+ lowest);
	System.out.println("Highest marks : "+ highest);
	for(int mark:studentmarks) {
		if(mark>avg) System.out.println("Mark's Greater than Average : "+mark);
	}
	sc.close();
}
}
