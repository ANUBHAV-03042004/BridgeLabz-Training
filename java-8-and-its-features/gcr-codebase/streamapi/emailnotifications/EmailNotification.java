package emailnotifications;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class EmailNotification {
	  public static void main(String[] args) {
	        List<String> emails = Arrays.asList(
	            "rahul.sharma@example.com",
	            "priya.nair@example.com",
	            "amit.verma@example.com",
	            "sneha.kapoor@example.com",
	            "vikram.singh@example.com",
	            "anita.desai@example.com",
	            "rohit.mehta@example.com"
	        );
	        Consumer<String> sendEmailNotification = (email) -> System.out.println("Email sent at : "+ email);
      emails.forEach(email->sendEmailNotification.accept(email));

}
}
