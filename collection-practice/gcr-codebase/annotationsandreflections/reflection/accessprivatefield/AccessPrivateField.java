package accessprivatefield;

import java.lang.reflect.Field;

class Person{
	private static int age = 20;
}
public class AccessPrivateField {
public static void main(String[] args) {
	Class<Person> cls= Person.class;
	try {
		Field field= cls.getDeclaredField("age");
		field.setAccessible(true);
		System.out.println(field.get(null));
			field.set(null,21);
			System.out.println(field.get(null));
	} catch (NoSuchFieldException e) {
		e.printStackTrace();
	} catch (SecurityException e) {
		e.printStackTrace();
	} catch (IllegalArgumentException e1) {
		e1.printStackTrace();
	} catch (IllegalAccessException e1) {
		e1.printStackTrace();
	}
}
}
