package createajsonobjectforastudent;
import java.util.Arrays;

import org.json.JSONObject;
public class CreateJsonObject {
	 public static void main(String[] args) {
	        JSONObject Student = new JSONObject();
	        Student.put("name","Sahil");
	        Student.put("age", 21);
	        Student.put("subjects",Arrays.asList("Physics","Chemistry","Maths"));
	        System.out.println(Student.toString());
	    }

}
