package invokeprivatemethod;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class Calculator{
	private static int multiply(int a,int b) {
		return a*b;
	}
}
public class InvokePrivateMethod {
	public static void main(String[] args) {
Class <?> cls= Calculator.class;
try {
	Method m = cls.getDeclaredMethod("multiply",int.class,int.class);
//	System.out.println("Called Before : "+m.invoke(m,5,4));
	m.setAccessible(true);
	System.out.println("Called After : "+m.invoke(m,5,4));
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