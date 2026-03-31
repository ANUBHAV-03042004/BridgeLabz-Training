package hospitaldoctoravailability;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class Hospital {
	 public static void main(String[] args) {
	        List<Doctor> doctors = Arrays.asList(
	            new Doctor("Psychology", "Mrs. Gunjan Arya", "Weekends", "Delhi"),
	            new Doctor("Ayurveda", "Dr. Geetika Pahuja", "Weekends", "Faridabad, Haryana"),
	            new Doctor("Dentistry", "Dr. Vikrant Ravindra Pardeshi", "24/7", "Pune, Maharashtra"),
	            new Doctor("Internal Medicine", "Dr. Aparna More", "Weekends", "Thane, Maharashtra"),
	            new Doctor("Neurosurgery", "Dr. Bhuvaneswara R Basina", "Mondays", "Hyderabad, Telangana"),
	            new Doctor("Ophthalmology", "Dr. Divya Saxena", "Weekends", "Delhi"),
	            new Doctor("Cardiology", "Dr. Ramesh Gupta", "Tuesday", "Delhi"),
	            new Doctor("Dermatology", "Dr. Anita Sharma", "Weekends", "Mumbai"),
	            new Doctor("Orthopedics", "Dr. Vikram Singh", "Wednesday", "Lucknow"),
	            new Doctor("Pediatrics", "Dr. Neha Kapoor", "Weekends", "Noida"),
	            new Doctor("Neurology", "Dr. Arjun Mehta", "24/7", "Hyderabad"),
	            new Doctor("Dentistry", "Dr. Priya Nair", "Thursday", "Chennai"),
	            new Doctor("Ophthalmology", "Dr. Sanjay Rao", "Friday", "Bangalore")
	        );
	        Stream<Doctor> weekendsAvailableDoctors = doctors.stream().filter(s->s.getAvailability().equalsIgnoreCase("Weekends")).sorted(Comparator.comparing(Doctor::getSpecialty));
	        weekendsAvailableDoctors.forEach(System.out::println);
	        
	        
	 }
}
