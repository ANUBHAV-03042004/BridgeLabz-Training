package implementacustomcachingsystem;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

class Sum{
	@CacheResult
	int sumTillN(int n) {
		int sum=0;
		for(int i=1;i<=n;sum+=i,i++);
		return sum;
	}
}
public class CachingSystem {
	private static final HashMap<Integer,Integer>cache = new HashMap<>();
public static void main(String[] args) {
	Sum sum= new Sum();
   Class<?>cls= Sum.class;
   try {
	Method method= cls.getDeclaredMethod("sumTillN",int.class);
	if(method.isAnnotationPresent(CacheResult.class)) {
		System.out.println(invokeWithCache(sum,method,10));
		System.out.println(invokeWithCache(sum,method,10));
	    System.out.println(invokeWithCache(sum,method,20));
		System.out.println(invokeWithCache(sum,method,20));
	}
	
} catch (NoSuchMethodException e) {
	e.printStackTrace();
} catch (SecurityException e) {
	e.printStackTrace();
}
	
}
static int invokeWithCache(Object sum,Method method,int value) {
int result=0;
	if(cache.containsKey(value)) {
		System.out.println("Returned Value From Cache");
		return cache.get(value);
	}
	else {
		try {
		    result = (Integer) method.invoke(sum, value);
			cache.put(value,result);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	return result;

}
}
