package passwordstrengthvalidator;

import java.util.function.IntSupplier;

public interface StringUtils {
 static boolean checkpassword(String password) {
	 if(password == null) return false;
	 IntSupplier len =  password::length;
	 boolean length =len.getAsInt()>=8;
	 boolean upperCase = password.chars().anyMatch(Character::isUpperCase);
	 boolean LowerCase = password.chars().anyMatch(Character::isLowerCase);
	 boolean number = password.chars().anyMatch(Character::isDigit);
	 boolean hasSpecial = password.chars().anyMatch(ch -> !Character.isLetterOrDigit(ch));
	 return length && upperCase && LowerCase && number && hasSpecial;
 }
}
