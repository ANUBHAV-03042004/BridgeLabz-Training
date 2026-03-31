package methodexecutiontiming;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class MathOps {
    public int add(int a, int b) {
        return a + b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }
}
public class MethodExecutionTiming {
	public static void main(String[] args) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
Class<?>cls=MathOps.class;
Method methodArray[]=cls.getDeclaredMethods();
Constructor cons=cls.getDeclaredConstructor();
MathOps mops= (MathOps) cons.newInstance();
for(Method method:methodArray)
{
	long start= System.nanoTime();
	method.invoke(mops,5,6);
	long end=System.nanoTime();
	System.out.println(method.getName()+" took : "+ (end - start));
}

}
}
