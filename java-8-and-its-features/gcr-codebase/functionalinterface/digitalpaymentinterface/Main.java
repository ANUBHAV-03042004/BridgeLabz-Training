package digitalpaymentinterface;

public class Main {
public static void main(String[] args) {
	UPI upi= new UPI();
	Wallet wallet = new Wallet();
	CreditCard creditcard = new CreditCard();
	
	upi.pay();
	wallet.pay();
	creditcard.pay();
}
}
