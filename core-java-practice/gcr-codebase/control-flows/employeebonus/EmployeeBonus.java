package employeebonus;
import java.util.Scanner;
public class EmployeeBonus {
	public static void main(String args[]) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter your salary");
		int salary=sc.nextInt();
		System.out.println("Enter your years of Service :");
		int yrsofservice=sc.nextInt();
		
		if(yrsofservice>5) {
			int bonusamount=(salary*5)/100;
			System.out.println("Bonus amount :"+bonusamount+"\nAmount after bonus :"+(bonusamount+salary) );
		}
		else System.out.println("No Bonus");
		
	}
}
