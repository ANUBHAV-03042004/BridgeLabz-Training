package validateausername;
public class ValidateAUsername {
public static void validateUsername(String regex,String name) {
	System.out.println("Is Username valid ? :"+name.matches(regex));
}
public static void main(String args[])
{
	String regex="^[A-Za-z][A-Za-z0-9_]{4,14}$",Username="aks123";
	validateUsername(regex,Username);
}
}
