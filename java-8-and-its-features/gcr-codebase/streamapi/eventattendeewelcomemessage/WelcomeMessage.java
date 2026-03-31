package eventattendeewelcomemessage;

import java.util.Arrays;
import java.util.List;

public class WelcomeMessage {
	 public static void main(String[] args) {
	        List<String> attendees = Arrays.asList(
	            "Rahul Sharma",
	            "Priya Nair",
	            "Amit Verma",
	            "Sneha Kapoor",
	            "Vikram Singh"
	        );

	        attendees.stream()
	                 .forEach(name -> System.out.println("Welcome, " + name + "!"));
	    }

}
