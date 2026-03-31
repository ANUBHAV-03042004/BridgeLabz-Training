package sensitivedatatagging;

public class SensitiveDataTagging {
	public void data(Object obj) {
		if(obj instanceof SensitiveData) System.out.println("Sensitive Data : "+ obj.getClass().getSimpleName());
		else System.out.println("Obj is not sensitive.");
	}
	public static void main(String[] args) {
		User u1 = new User("anubhav","aks@gmail.com");
		User u2 = new User("sai","s@gmail.com");
		
		SensitiveDataTagging sdt = new SensitiveDataTagging();
		sdt.data(u1);
	}
}
