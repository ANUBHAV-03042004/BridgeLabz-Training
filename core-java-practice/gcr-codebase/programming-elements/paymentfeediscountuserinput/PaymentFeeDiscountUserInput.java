package paymentfeediscountuserinput;
import java.util.Scanner;
public class PaymentFeeDiscountUserInput {
public static void main(String args[])
{
	Scanner sc=new Scanner(System.in);
	System.out.print("Enter the fees:");
	int fees=sc.nextInt();
	System.out.print("Enter the Discount Percentage:");
	int discountPercent=sc.nextInt();
	double discount= (fees * discountPercent)/100;
	double discountedamount = fees - discount;
	System.out.printf("The discount amount is INR %f and final discounted fee is INR %f \r\n",discount,discountedamount);
}
}
