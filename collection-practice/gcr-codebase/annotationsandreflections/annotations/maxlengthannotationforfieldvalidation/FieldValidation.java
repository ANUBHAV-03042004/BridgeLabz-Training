package maxlengthannotationforfieldvalidation;

import java.lang.reflect.Field;

class User{
	@MaxLength(10)
	private String username;
	
	User(String name){
		this.username=name;
	Class<?> cls= User.class;
	Field field;
	try {
		field = cls.getDeclaredField("username");
		if(field.isAnnotationPresent(MaxLength.class)) {
			MaxLength maxlength = field.getAnnotation(MaxLength.class);
			int length = maxlength.value();
			if(username.length()>length) {
				throw new IllegalArgumentException("Length exceeded the limit.");
			}
		}
	} catch (NoSuchFieldException e) {
		e.printStackTrace();
	} catch (SecurityException e) {
		e.printStackTrace();
	}
	}
	String getUsername() {
		return username;
	}
}
public class FieldValidation {
	public static void main(String[] args) {
		 User user= new User("Anubhav");
		 System.out.println(user.getUsername());
		 User user2= new User("Prakash Dixt");
		 System.out.println(user2.getUsername());
	}

}
