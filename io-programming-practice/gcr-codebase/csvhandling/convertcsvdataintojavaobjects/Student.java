package convertcsvdataintojavaobjects;

public class Student {
String id;
String name;
String email;
String phone;
Student(String id,String name,String email,String phone){
	this.id=id;
	this.email=email;
	this.name=name;
	this.phone=phone;
}
@Override
public String toString() {
	return id+"  "+ name+"   "+email+"  "+phone;
}
}
