package mayabmifitnesstracker;
import java.util.Scanner;
public class BmiFitnessTracker {
public static void main(String args[])
{
	Scanner sc=new Scanner(System.in);
	System.out.println("Enter your height in m :");
//	enter your height
    double height=sc.nextDouble();
    System.out.println("Enter your weight in kgs :");
    double weight=sc.nextDouble();
//    compute bmi and tells whether you are overweight, normal , underweight or obese.
    double bmi=weight/(height*height);
    if(bmi>=18.5 && bmi<=24.9)System.out.println("You are normal");
    else if(bmi<18.5) System.out.println("You are underweight");
    else if(bmi > 24.9 && bmi <29.9) System.out.println("You are overweight");
    else if(bmi>30) System.out.println("You are obese");
    else System.out.println("Wrong Input");
    
}
}
