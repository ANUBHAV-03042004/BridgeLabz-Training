package createanduserepeatableannotation;

import java.lang.reflect.Method;

public class UseRepeatableandBugReport {
	@BugReport(description="Created a method to run task")
	@BugReport(description="Task Should run without any error")
void runtask() {
	System.out.println("Task executed");
}
public static void main(String args[]) throws NoSuchMethodException, SecurityException {
	UseRepeatableandBugReport  URB= new UseRepeatableandBugReport ();
//	URB.runtask();
	Class<?> cls= UseRepeatableandBugReport.class;
	Method m = cls.getDeclaredMethod("runtask", null);
	if(m.isAnnotationPresent(BugReport.class)) {
		BugReports[] bug= (BugReports[]) m.getAnnotationsByType(BugReport.class);
	for(BugReports b : bug) {
		System.out.println(b);
	}
	}
	
}
}
