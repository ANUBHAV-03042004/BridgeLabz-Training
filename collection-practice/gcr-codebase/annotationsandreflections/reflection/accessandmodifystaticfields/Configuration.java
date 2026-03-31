package accessandmodifystaticfields;

import java.lang.reflect.Field;

public class Configuration {
private static String API_KEY="e2ff5c96";
public static void main(String[] args) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
	Class<?>cls=Configuration.class;
	Field field= cls.getDeclaredField("API_KEY");
	field.setAccessible(true);
	field.set(null,"6fb6d22e");
	System.out.println("New API_KEY = "+ field.get(null));
}
}
