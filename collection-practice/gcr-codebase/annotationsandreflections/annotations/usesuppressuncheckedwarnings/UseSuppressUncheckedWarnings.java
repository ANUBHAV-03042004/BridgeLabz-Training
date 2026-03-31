package usesuppressuncheckedwarnings;
import java.util.ArrayList;
public class UseSuppressUncheckedWarnings {
	@SuppressWarnings("unchecked")
public static void main(String args[]) {
	ArrayList check = new ArrayList();
	check.add(123);
	check.add("hello");
	System.out.println(check);
	
}
}
