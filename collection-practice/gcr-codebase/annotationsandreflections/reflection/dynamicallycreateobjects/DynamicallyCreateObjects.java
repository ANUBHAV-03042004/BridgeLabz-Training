package dynamicallycreateobjects;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class Student{
	public Student(){}
  void run() {
	  System.out.println("Inside Student Class");
  }
}
public class DynamicallyCreateObjects {
public static void main(String[] args) {
	Class<?> cls= Student.class;
	try {
		Constructor cons= cls.getConstructor();
		Student stu= (Student) cons.newInstance();
		stu.run();
	} catch (NoSuchMethodException e) {
		e.printStackTrace();
	} catch (SecurityException e) {
		e.printStackTrace();
	} catch (InstantiationException e) {
		e.printStackTrace();
	} catch (IllegalAccessException e) {
		e.printStackTrace();
	} catch (IllegalArgumentException e) {
		e.printStackTrace();
	} catch (InvocationTargetException e) {
		e.printStackTrace();
	} 
}
}
