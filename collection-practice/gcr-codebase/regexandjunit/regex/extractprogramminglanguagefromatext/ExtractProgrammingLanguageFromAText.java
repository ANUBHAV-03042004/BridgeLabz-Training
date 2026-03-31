package extractprogramminglanguagefromatext;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
public class ExtractProgrammingLanguageFromAText {
	    static void extractProgrammingLanguage(String text) {
	    	   Matcher m = Pattern.compile("\\b(Java|Python|JavaScript|Go)\\b").matcher(text);
	    	   while(m.find())
	    	   {
	    		   System.out.println(m.group());
	    	   }
	    }

	    public static void main(String args[]) {
	        String text ="I love Java, Python, and JavaScript, but I haven't tried Go yet.";
	        extractProgrammingLanguage(text);
	    }
}
