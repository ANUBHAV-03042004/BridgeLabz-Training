package sensitivedatatagging;

public class User implements SensitiveData {
private String name;
private String email;

User(String name,String email){
	this.email=email;
	this.name=name;
}
public String getName() {
	return name;
}
public String getEmail() {
	return email;
}
}
