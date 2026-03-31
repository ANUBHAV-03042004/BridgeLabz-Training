package nameuppercasing;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class NameUppercasing {
public static void main(String[] args) {
	  List<String> employeeNames = Arrays.asList("Akash", "Prakash", "Dinesh", "Paresh","Rakesh");
	 List<String> upperCaseEmployeeNames= employeeNames.stream().map(String::toUpperCase).collect(Collectors.toList());
	 upperCaseEmployeeNames.forEach(System.out::println);
}
}
