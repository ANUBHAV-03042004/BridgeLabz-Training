package paymentgatewayintegration;

public class Provider implements PaymentProcessor {

	@Override
	public void pay(double amount) {
		System.out.println("Pay: "+amount);
	}
	public static void main(String[] args) {
		Person p1 = new Person();
		Provider p2 = new Provider();
		
		p1.pay(50d);
		p1.refund(50);
		
		p2.pay(100);
		p2.refund(100);

		
	}
}
