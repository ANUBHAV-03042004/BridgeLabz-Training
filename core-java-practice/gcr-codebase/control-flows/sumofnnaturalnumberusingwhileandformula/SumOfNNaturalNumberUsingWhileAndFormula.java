package sumofnnaturalnumberusingwhileandformula;
import java.util.Scanner;
public class SumOfNNaturalNumberUsingWhileAndFormula {
	public static void main(String args[]) {
Scanner sc=new Scanner(System.in);
int number=sc.nextInt();
boolean checknatural=true;
int total_using_while=0;
if(number<=0) {
	System.out.println("Number is not a natural number");
	checknatural=false;
}
int copynumber=number;
while(number>0)total_using_while+=number--;
int sum_using_formula= (copynumber*(copynumber+1)/2);

boolean check=sum_using_formula==total_using_while;
if(checknatural)
System.out.println("Are both equal : "+ check );
	}
}
