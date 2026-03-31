package validateahexcolorcode;

public class ValidateAHexColorCode {
	static boolean validHexColorCode(String regex,String code) {
		  return code.matches(regex);
	  }
	  public static void main(String args[])
	  {
		  String regex="^#[A-Fa-f0-9]{6}$",code="#fFf500";
		 System.out.println("Is Hex Color Code Valid : "+validHexColorCode(regex,code));
	  }
}
