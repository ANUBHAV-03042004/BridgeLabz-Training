package extractallemailfromatext;
import java.util.regex.*;
public class ExtractAllEmailFromAText {
	static void extractEmails(String regex,String text) {
		Pattern p= Pattern.compile(regex);
		Matcher m= p.matcher(text);
		while(m.find())
		{
			 System.out.println(m.group());
		}
		 
	  }
	  public static void main(String args[])
	  {
		  String regex="\\b[A-Za-z0-9_\\.]+@[A-Za-z]+\\.[a-z]{2,}\\b",text="Contact us at support@example.com and info@company.org";
		  
		extractEmails(regex,text);
	  }
}
