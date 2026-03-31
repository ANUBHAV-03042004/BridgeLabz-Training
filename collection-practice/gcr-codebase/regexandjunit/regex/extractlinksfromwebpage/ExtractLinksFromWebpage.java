package extractlinksfromwebpage;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
public class ExtractLinksFromWebpage {
	static void extractLinks(String regex,String text) {
		  Pattern p=Pattern.compile(regex);
		  Matcher m = p.matcher(text);
		  while(m.find()) {
			  System.out.println(m.group());
		  }
	  }
	  public static void main(String args[])
	  {
		  String regex="\\bhttps?://([w]{3}\\.)?[a-z]+\\.[a-z]{2,}\\b",text="Visit https://www.google.com and http://example.org for more info.";
	      extractLinks(regex,text);
	  }
}
