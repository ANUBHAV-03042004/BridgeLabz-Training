package createtodoannotationforpendingtasks;

import java.lang.reflect.Method;

public class PendingTasks {
@Todo(task="Change UI",assignedTo="AKS",priority="Medium")
 void UI() {
	System.out.println("UI");
}
@Todo(task="Restructure backend",assignedTo="Prakash",priority="Low")
 void backend() {
	 System.out.println("Backend");
 }
 public static void main(String[] args) {
	Class<?> cls=PendingTasks.class;
	Method methodArray[]=cls.getDeclaredMethods();
	for(Method method:methodArray)
	{
		if(method.isAnnotationPresent(Todo.class)) System.out.println(method.getName());
	}
}
}
