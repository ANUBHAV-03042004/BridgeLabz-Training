package generateajsonrepresentation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class GenerateAJsonRepresentation {
    String name;
    int age;
    
    public GenerateAJsonRepresentation(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String toJson(Object obj) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{\n");

        Class<?> cls = obj.getClass();
        Field[] fieldArray = cls.getDeclaredFields();
int count=0;
          for(Field field: fieldArray) {
            field.setAccessible(true); 
            try {
                Object value = field.get(obj);
                stringBuilder.append("  \"")
                             .append(field.getName())
                             .append("\": ");

                if (value instanceof String) {
                    stringBuilder.append("\"").append(value).append("\"");
                } else {
                    stringBuilder.append(value);
                }
  count++;
                if (count<fieldArray.length) {
                    stringBuilder.append(",");
                }
                stringBuilder.append("\n");

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    public static void main(String[] args) throws NoSuchMethodException,
            SecurityException, InstantiationException,
            IllegalAccessException, IllegalArgumentException,
            InvocationTargetException {

        Class<?> cls = GenerateAJsonRepresentation.class;
        Constructor<?> cons = cls.getDeclaredConstructor(String.class, int.class);

        GenerateAJsonRepresentation gajr =
                (GenerateAJsonRepresentation) cons.newInstance("Anubhav", 21);

        System.out.println(gajr.toJson(gajr));
    }
}