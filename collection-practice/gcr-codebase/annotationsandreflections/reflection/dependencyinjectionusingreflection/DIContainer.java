package dependencyinjectionusingreflection;

import java.lang.reflect.Field;

class ServiceA {
    public void doWork() {
        System.out.println("ServiceA is working...");
    }
}

class ServiceB {
    public void execute() {
        System.out.println("ServiceB is executing...");
    }
}
class Client {
    @Inject
    private ServiceA serviceA;

    @Inject
    private ServiceB serviceB;

    public void run() {
        serviceA.doWork();
        serviceB.execute();
    }
}
public class DIContainer {
	 public Object createInstance(Class<?> cls) {
	        try {
	            Object obj = cls.getDeclaredConstructor().newInstance();

	            for (Field field : cls.getDeclaredFields()) {
	                if (field.isAnnotationPresent(Inject.class)) {
	                    Class<?> dependencyType = field.getType();
	                    Object dependency = dependencyType.getDeclaredConstructor().newInstance();


	                    field.setAccessible(true);
	                    field.set(obj, dependency);
	                }
	            }
	            return obj;
	        } catch (Exception e) {
	            throw new RuntimeException("DI failed: " + e.getMessage(), e);
	        }
	    }

public static void main(String[] args) {
	        DIContainer container = new DIContainer();

	        // Create Client with dependencies injected
	        Client client = (Client) container.createInstance(Client.class);

	        // Run client logic
	        client.run();
	    }

}
