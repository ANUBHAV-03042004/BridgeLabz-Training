package createacustomobjectmapper;
import java.lang.reflect.Field;
import java.util.*;
public class CustomObjectMapper {
	static String name="Guest";
	static int age=0;
	public static void toObject(Class<?> clazz, Map<String,Object> properties) throws IllegalArgumentException, IllegalAccessException {
		Field fieldArray[]= clazz.getDeclaredFields();
		for(Field field:fieldArray) {
			field.setAccessible(true);
			field.set(null,properties.get(field.getName()));
		}
		
	}
public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
	HashMap<String,Object>hmap = new HashMap<>();
	hmap.put("name","Anubhav");
	hmap.put("age",21);
	Class<?> cls = CustomObjectMapper.class;
    toObject(cls,hmap);
	System.out.println(name +" "+ age);
	
}
}
