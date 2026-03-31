package extractallcapitalizedwordsfromasentence;
import java.util.regex.*;
public class ExtractAllCapitalizedWordsFromASentence {
	static void extractAllCapitalWords(String regex,String text) {
		  Pattern p=Pattern.compile(regex);
		  Matcher m = p.matcher(text);
		  while(m.find()) {
			  System.out.println(m.group());
		  }
	  }
	  public static void main(String args[])
	  {
		  String regex="\\b[A-Z][a-z]+\\b",text="The Eiffel Tower is in Paris and the Statue of Liberty is in New York.";
	      extractAllCapitalWords(regex,text);
	  }
}
