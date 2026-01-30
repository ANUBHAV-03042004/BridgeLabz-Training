package paymentgatewayintegration;

class Person implements PaymentProcessor {
	@Override
	public void pay(double amount) {
		System.out.println("Pay :"+ amount);
		
	}
	@Override
	public void refund(double amount) {
		System.out.println("refund :"+amount);
	}

}
