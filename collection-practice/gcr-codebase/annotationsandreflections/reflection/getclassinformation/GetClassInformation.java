package getclassinformation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class GetClassInformation {
	int value;
	String drum;
	GetClassInformation(){
		 value=10;
		drum="empty";
	}
	GetClassInformation(int value,String drum){
		this.value=value;
		this.drum=drum;
	}
	static void run() {
		System.out.println("Run");
	}
	static void fun() {
		System.out.println("Fun");
	}
public static void main(String[] args) {
	Class<GetClassInformation>cls= GetClassInformation.class;
	System.out.println("Class Name :"+ cls.getName());
	 Method methodArray[]= cls.getDeclaredMethods();
	 Constructor constructor[] = cls.getDeclaredConstructors();
	 Field field[]= cls.getDeclaredFields();
      for(Constructor cons: constructor) {
   Parameter[] paramtersfromconstructors= cons.getParameters();
          System.out.println("Constructor name: " +cons.getName());
      for(Parameter params:paramtersfromconstructors) {
     	  System.out.println("Paramter Type: "+ params.getType());
    	  System.out.println("Parameter : "+params.getName());
      }
      }
      for(Method method: methodArray) {
    	   Parameter[] paramtersfromMethods= method.getParameters();
    	          System.out.println("Method name : "+method.getName());
    	      for(Parameter params:paramtersfromMethods) {
    	     	  System.out.println("Paramter Type : "+ params.getType());
    	    	  System.out.println("Paramter : "+ params.getName());
    	      }
    	      }
	 
	}
}
