package createannotationtomarkimportantmethods;

import java.lang.reflect.Method;

public class ImportantMethodsMarker {
	@ImportantMethod(level="Low")
 void loginUI() {
	 System.out.println("Login UI");
 }
	@ImportantMethod(level="High")
void BackendProcess() {
	System.out.println("Backend Process");
}
	@ImportantMethod()
void DB() {
	System.out.println("DB");
}
	@ImportantMethod(level="Medium")
void Data() {
	System.out.println("Data");
}	
	public static void main(String args[]) {
//		ImportantMethodsMarker imp= new ImportantMethodsMarker();
//		imp.loginUI();
//		imp.BackendProcess();
//		imp.DB();
//		imp.Data();
		Class<?> cls= ImportantMethodsMarker.class;
		Method[] methodArray = cls.getDeclaredMethods();
		for(Method method : methodArray) {
			if(method.isAnnotationPresent(ImportantMethod.class)) {
				System.out.println(method.getName());
			}
		}
	}
}
