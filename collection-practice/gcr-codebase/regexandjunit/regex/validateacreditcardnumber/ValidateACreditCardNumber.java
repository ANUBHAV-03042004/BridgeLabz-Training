package validateacreditcardnumber;

public class ValidateACreditCardNumber {
	static boolean validateCreditCard(String regex,String cardnumber) {
		 return cardnumber.matches(regex);
	  }
	  public static void main(String args[])
	  {
		  String regex="^(4|5)\\d{15}$",cardnumber="4123456789123456";
		System.out.println(validateCreditCard(regex,cardnumber));
	  }
}
