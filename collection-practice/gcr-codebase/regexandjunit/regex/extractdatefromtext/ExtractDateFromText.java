package extractdatefromtext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class ExtractDateFromText {
	static void extractDate(String regex,String text) {
		  Pattern p=Pattern.compile(regex);
		  Matcher m = p.matcher(text);
		  while(m.find()) {
			  System.out.println(m.group());
		  }
	  }
	  public static void main(String args[])
	  {
		  String regex="\\b[0-9]{2}/[0-9]{2}/[0-9]{4}\\b",text="The events are scheduled for 12/05/2023, 15/08/2024, and 29/02/2020.";
	      extractDate(regex,text);
	  }
}
