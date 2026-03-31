package createrolebasedaccesscontrol;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@RoleAllowed()
class User{
	void runUserTask() {
		System.out.println("Run User Task");
	}
}
@RoleAllowed(Role="Admin")
class Admin{
	void runAdminTask() {
		System.out.println("Run Admin Task");
	}
}


public class RoleBasedAccess {
	static void invokeIfAllowed(Object obj, String methodName, String currentRole) {
	    try {
	        Class<?> cls = obj.getClass();
	        RoleAllowed roleAllowed = cls.getAnnotation(RoleAllowed.class);
	        String requiredRole = roleAllowed.Role();

	        Method m = cls.getDeclaredMethod(methodName);

	        if (currentRole.equalsIgnoreCase("Admin")) {
	            // Admin can do everything
	            m.invoke(obj);
	        } else if (currentRole.equalsIgnoreCase("User")) {
	            // User can only access classes annotated as "User"
	            if (requiredRole.equalsIgnoreCase("User")) {
	                m.invoke(obj);
	            } else {
	                System.out.println("Access Denied! Required role: " + requiredRole);
	            }
	        } else {
	            System.out.println("Access Denied! Unknown role: " + currentRole);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	 }
public static void main(String[] args) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException { 

    User user = new User();
    Admin admin = new Admin();

    // User role
    System.out.println("User access:");
    invokeIfAllowed(user, "runUserTask", "User");  
    invokeIfAllowed(admin, "runAdminTask", "User");
    System.out.println("Admin accessed: ");
    invokeIfAllowed(user, "runUserTask", "Admin");  
    invokeIfAllowed(admin, "runAdminTask", "Admin");

}

}
