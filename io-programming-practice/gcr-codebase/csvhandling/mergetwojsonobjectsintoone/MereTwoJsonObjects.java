package mergetwojsonobjectsintoone;

import org.json.JSONArray;
import org.json.JSONObject;

class Student{
	private String name;
	private int standard;
	public Student(String name,int standard){
		this.name=name;
		this.standard=standard;
	}
	public String getName(){
		return name;
	}
	public int getStandard() {
		return standard;
	}
}
public class MereTwoJsonObjects {
public static void main(String[] args) {
	Student ashish = new Student("ashish",3);
	Student manish = new Student("manish", 5);
	JSONObject jsonObj1= new JSONObject();
	jsonObj1.put("name",ashish.getName());
	jsonObj1.put("standard",ashish.getStandard());
	JSONObject jsonObj2= new JSONObject(manish);
	jsonObj2.put("name",manish.getName());
	jsonObj2.put("standard",manish.getStandard());
	
	JSONArray jsonArr = new JSONArray();
	jsonArr.put(jsonObj1);
	jsonArr.put(jsonObj2);
	System.out.println(jsonArr.toString(2));
}
	
}
