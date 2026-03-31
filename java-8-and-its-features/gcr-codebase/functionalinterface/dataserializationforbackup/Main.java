package dataserializationforbackup;

public class Main {
public static void main(String[] args) {
	try {
	User u1= new User("anubhav","aks@gmail.com");
	
String filePath = "C:\\Users\\HP\\eclipse-workspace\\BridgeLabz-Training\\java-8-and-its-features\\gcr-codebase\\functionalinterface\\dataserializationforbackup\\Backup.bak";

UserBackup.backup(u1, filePath);

User user = (User) UserBackup.restore(filePath);

System.out.println(user.getname() +"@->"+ user.getemail());

	}catch(Exception e) {
		e.printStackTrace();
	}
}
}
