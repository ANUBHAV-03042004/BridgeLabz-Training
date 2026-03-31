package createcustomannotationanduseit;
import java.lang.reflect.Method;
public class UseCustomAnnotation {
	@TaskInfo(priority=5,assignedTo="AKS")
	void Task() {
		System.out.println("Assigned Task");
	}
public static void main(String args[]) throws NoSuchMethodException, SecurityException {
	Class<?> cls= UseCustomAnnotation.class;
	Method method= cls.getDeclaredMethod("Task", null);
	if(method.isAnnotationPresent(TaskInfo.class)) {
		TaskInfo task= method.getAnnotation(TaskInfo.class);
		System.out.println(task.assignedTo());
		System.out.println(task.priority());
	}
		
}
}
