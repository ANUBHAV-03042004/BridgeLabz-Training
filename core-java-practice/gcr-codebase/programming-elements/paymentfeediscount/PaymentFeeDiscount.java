package paymentfeediscount;

public class PaymentFeeDiscount {
public static void main(String args[])
{
	int fees=125000;
	int discount_percent=10;
	double discountedprice=((fees*discount_percent)/100);
	double price_after_discount= fees- discountedprice;
	System.out.printf("Discount Percentage applied = %d\nDiscounted price = %f\nPrice after discount = %f.",discount_percent,discountedprice,price_after_discount);
}
}
