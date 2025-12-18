package calculatethetotalprice;
import java.util.Scanner;
public class CalculateTheTotalPrice {
public static void main(String args[])
{
	Scanner sc=new Scanner(System.in);
	System.out.println("Enter the unitPrice of item :");
	int unitPrice= sc.nextInt();
	System.out.println("Enter the quantity of item :");
	int quantity = sc.nextInt();
	System.out.printf("The total purchase price is INR %d if the quantity %d and unit price is INR %d \r\n"
		, unitPrice * quantity , quantity, unitPrice);
	
}
}
