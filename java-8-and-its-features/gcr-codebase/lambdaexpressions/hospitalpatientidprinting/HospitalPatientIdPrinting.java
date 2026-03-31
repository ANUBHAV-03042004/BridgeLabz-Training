package hospitalpatientidprinting;

import java.util.Arrays;
import java.util.List;

public class HospitalPatientIdPrinting {
public static void main(String[] args) {
	  List<String> patientIds = Arrays.asList("P001", "P002", "P003", "P004");
System.out.println("Patiend Id's:");
patientIds.forEach(System.out::println);
}
}
