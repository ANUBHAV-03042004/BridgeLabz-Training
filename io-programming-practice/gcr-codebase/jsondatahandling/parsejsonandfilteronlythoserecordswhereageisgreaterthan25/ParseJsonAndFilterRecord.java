package parsejsonandfilteronlythoserecordswhereageisgreaterthan25;

import org.json.JSONArray;
import org.json.JSONObject;

class Student{
	String name;
	int age;
	Student(String name,int age)
	{
		this.name=name;
		this.age=age;
	}
	  public JSONObject toJson() {
	        JSONObject obj = new JSONObject();
	        obj.put("name", name);
	        obj.put("age", age);
	        return obj;
	    }

	@Override
	public String toString() {
		return name+" : "+ age;
	}
}
public class ParseJsonAndFilterRecord {
public static void main(String[] args) {
	Student s1= new Student("aks",21);
	Student s2= new Student("prakash",26);
	Student s3= new Student("sahil",28);
	
	
	 JSONArray studentsArray = new JSONArray();
     studentsArray.put(s1.toJson());
     studentsArray.put(s2.toJson());
     studentsArray.put(s3.toJson());

     for(int i=0;i<studentsArray.length();i++) {
    	 JSONObject jobj= studentsArray.getJSONObject(i);
    	 int age = jobj.getInt("age");
    	 if(age>25)
    	 {
    		 System.out.println(jobj.getString("name")+" : " + age);
    	 }
     }
	
	
	
}
}
