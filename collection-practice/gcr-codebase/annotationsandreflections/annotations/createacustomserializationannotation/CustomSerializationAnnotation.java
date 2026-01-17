package createacustomserializationannotation;

import java.lang.reflect.Field;
import java.util.ArrayList;

class User{
	@JsonField(name="user_name")
	private String username;
	
	@JsonField(name="user_age")
	private int age;
	
	@JsonField(name="user_email")
	private String email;
	
	public User(String username, int age , String email) {
		this.username=username;
		this.age=age;
		this.email=email;
	}

}

class JsonSerialization{
	public String toJson(Object obj) {
	 StringBuilder strBuilder = new StringBuilder();
	 strBuilder.append("{");
	 Class<?> cls= User.class;
	 Field []fieldArray = cls.getDeclaredFields();
	StringBuilder jsonList = new StringBuilder();
	 jsonList.append("\n");
	 int count =0;
	 for(Field field : fieldArray) {
		 if(field.isAnnotationPresent(JsonField.class)) {
		  JsonField json= field.getAnnotation(JsonField.class);
		  String key = json.name();
		  field.setAccessible(true); // used to let us use the private variable.
		  try {
			Object value = field.get(obj);
			 jsonList.append("  \"").append(key).append("\": ");

             if (value instanceof String) {
                 jsonList.append("\"").append(value).append("\"");
             } else {
                 jsonList.append(value);
             }
             count++;
             if (count < fieldArray.length) {
                 jsonList.append(",");
             }

            
             jsonList.append("\n");

			
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		 }
	 }
	 strBuilder.append(jsonList);
	 strBuilder.append("}");
	 return strBuilder.toString();
	}
}
public class CustomSerializationAnnotation {
	public static void main(String[] args) {
		User user = new User("Anubhav",21,"aks@gmail.com");
		JsonSerialization jsonSerialization = new JsonSerialization();
		String result = jsonSerialization.toJson(user);
		System.out.println(result);
		
	}

}
