package transformingnamesfordisplay;

import java.util.Arrays;
import java.util.List;

public class TransformName {
	public static void main(String[] args) {
        List<String> customers = Arrays.asList(
            "Rahul Sharma",
            "Priya Nair",
            "Amit Verma",
            "Sneha Kapoor",
            "Vikram Singh",
            "Anita Desai",
            "Rohit Mehta"
        );
        customers.stream().map(c->c.toUpperCase()).sorted().forEach(System.out::println);
	}
}
