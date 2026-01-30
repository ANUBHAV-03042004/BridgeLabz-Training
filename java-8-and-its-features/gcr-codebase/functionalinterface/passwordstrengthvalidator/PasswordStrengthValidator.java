package passwordstrengthvalidator;

import java.util.Scanner;

public class PasswordStrengthValidator {
public static void main(String[] args) {
	Scanner sc=new Scanner(System.in);
	System.out.println("Enter a password : ");
	String input = sc.next();
	System.out.println("Is password Strong ? "+ StringUtils.checkpassword(input));
}
}
