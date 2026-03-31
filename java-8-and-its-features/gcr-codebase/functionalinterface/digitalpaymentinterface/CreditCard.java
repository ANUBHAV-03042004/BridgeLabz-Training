package digitalpaymentinterface;

public class CreditCard implements Payment {

	@Override
	public void pay() {
		System.out.println("Pay with Credit card");
		
	}

}
