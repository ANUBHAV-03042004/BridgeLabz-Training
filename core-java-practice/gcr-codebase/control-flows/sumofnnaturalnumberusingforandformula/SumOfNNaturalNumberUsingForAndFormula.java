package sumofnnaturalnumberusingforandformula;

import java.util.Scanner;
public class SumOfNNaturalNumberUsingForAndFormula {
		public static void main(String args[]) {
	Scanner sc=new Scanner(System.in);
	int number=sc.nextInt();
	boolean checknatural=true;
	int total_using_for=0;
	if(number<=0) {
		System.out.println("Number is not a natural number");
		checknatural=false;
	}
	int copynumber=number;
	for(;number>0;number--) total_using_for+=number;
	int sum_using_formula= (copynumber*(copynumber+1)/2);

	boolean check=sum_using_formula==total_using_for;
	if(checknatural)
	System.out.println("Are both equal : "+ check );
		}
	}

