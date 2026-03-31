package replaceandmodifystrings;

public class CensorBadWords {
	static String censorBadWords(String text) {
		String badwords[]= {"damn","stupid"};
		for(String word :badwords) {
			 text=text.replaceAll("(?i)" + word,"****");
		}
		return text;
		  }
	  public static void main(String args[])
	  {
		  String text="This is a damn bad example with some stupid words.";
		 text=censorBadWords(text);
		  System.out.println(text);
	  }
}
