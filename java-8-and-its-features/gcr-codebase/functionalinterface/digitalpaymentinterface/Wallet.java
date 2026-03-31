package digitalpaymentinterface;

public class Wallet implements Payment{

	@Override
	public void pay() {
		System.out.println("Pay with Wallet");
		
	}

}
