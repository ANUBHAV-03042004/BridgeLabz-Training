package dynamicmethodinvocation;

import java.lang.reflect.Method;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

class MathOperations
{
	int value1,value2;
	public MathOperations(int value1,int value2) {
		this.value1=value1;
		this.value2=value2;
	}
	int add(int a,int b) {
		return a+b;
	}
	int sub(int a,int b) {
		return a-b;
	}
	int mul(int a,int b) {
		return a*b;
	}
	
}
public class DynamicMethodInvocation {
public static void main(String[] args) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
	Class<?> cls=MathOperations.class;
    Scanner sc= new Scanner(System.in);
    System.out.println("Enter add for addition / sub for substraction / mul for multiplication");
    String operation=sc.next();
    System.out.println("Enter value 1: ");
    int a= sc.nextInt();
    System.out.println("Enter Value 2: ");
    int b=sc.nextInt();
    Constructor cons = cls.getDeclaredConstructor(int.class,int.class);
    MathOperations mathop= (MathOperations) cons.newInstance(a,b);
    try {
		Method method=cls.getDeclaredMethod(operation,int.class,int.class);
		int result = (int) method.invoke(mathop,a,b);
		System.out.println(result);
	} catch (NoSuchMethodException e) {
		e.printStackTrace();
	} catch (SecurityException e) {
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
