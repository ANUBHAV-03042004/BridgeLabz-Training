package loggingmethodexecutiontime;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;

public class CalculateLoggingMethod {
	@LogExecutionTime
 int fast() {
	 int start=(int) System.nanoTime();
	 for(int i=0;i<10;i++);
	 int end=(int) System.nanoTime();
	 return end-start;
 }
@LogExecutionTime
 int slow() {
	 int start=(int) System.nanoTime();
	 for(int i=0;i<100;i++);
	 int end=(int) System.nanoTime();
	 return end-start;
 }
 public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
	 CalculateLoggingMethod clm= new CalculateLoggingMethod();
	Class<?> cls=CalculateLoggingMethod.class;
	Method methodArray[]= cls.getDeclaredMethods();
	for(Method method:methodArray) {
		if(method.isAnnotationPresent(LogExecutionTime.class)) {
			System.out.println("Time Taken by :"+ method.getName() +" - "+ method.invoke(clm,null));
		}
	}
}
}
