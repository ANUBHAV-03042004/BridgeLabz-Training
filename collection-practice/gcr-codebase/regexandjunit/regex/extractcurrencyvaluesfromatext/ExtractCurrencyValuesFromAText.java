package extractcurrencyvaluesfromatext;
import java.util.regex.*;
public class ExtractCurrencyValuesFromAText {
	static void extractCurrency(String text) {
		  Matcher m = Pattern.compile("\\$?\\d+\\.\\d{2}").matcher(text);
	        while (m.find()) {
	       System.out.println(m.group());
	  }
	}
	  public static void main(String args[])
	  {
		  String text="The price is $45.99, and the discount is $10.50.";
	      extractCurrency(text);
	  }
}
