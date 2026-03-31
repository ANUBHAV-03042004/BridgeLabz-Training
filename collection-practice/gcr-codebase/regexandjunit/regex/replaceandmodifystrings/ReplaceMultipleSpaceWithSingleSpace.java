package replaceandmodifystrings;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
public class ReplaceMultipleSpaceWithSingleSpace {
	static String ReplaceAndModify(String text) {
		 return text.replaceAll("\\s+", " ");
	  }
	  public static void main(String args[])
	  {
		  String text="This        is an example  with multiple  spaces.";
	    System.out.println(ReplaceAndModify(text));
	  }
}
