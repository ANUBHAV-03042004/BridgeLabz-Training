package convertlistofjavaobjectintojsonarray;

import java.util.ArrayList;

import org.json.JSONArray;

class Student{
	String name;
    int marks;
    Student(String name,int marks){
    	this.name=name;
    	this.marks=marks;
    }
    @Override
    public String toString() {
        return "{ \"name\": \"" + name + "\", \"marks\": " + marks + " }";
    }

}
public class ConvertListOfJavaObjectIntoJsonArray {
public static void main(String[] args) {
	 ArrayList<Student> student= new ArrayList<>();
	 Student akash  =new Student("akash",81);
	 Student prakash  =new Student("prakash",92);
	     student.add(akash);
	     student.add(prakash);
	     JSONArray studentJsonArray = new JSONArray();
	     for(Student stu: student) {
	    	 studentJsonArray.put(stu);
	     }
	     System.out.println(studentJsonArray.toString(2));
}
}
