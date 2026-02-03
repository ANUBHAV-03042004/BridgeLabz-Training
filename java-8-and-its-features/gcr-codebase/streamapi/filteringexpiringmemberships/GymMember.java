package filteringexpiringmemberships;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class GymMember {
    private String name;
    private LocalDate expiryDate;

    public GymMember(String name, LocalDate expiryDate) {
        this.name = name;
        this.expiryDate = expiryDate;
    }

    public String getName() { return name; }
    public LocalDate getExpiryDate() { return expiryDate; }

    @Override
    public String toString() {
        return String.format("%s - Membership expires on %s", name, expiryDate);
    }
    public static void main(String[] args) {
    	 List<GymMember> members = Arrays.asList(
    	            new GymMember("Rahul Sharma", LocalDate.of(2026, 3, 15)),
    	            new GymMember("Priya Nair", LocalDate.of(2026, 2, 28)),
    	            new GymMember("Amit Verma", LocalDate.of(2026, 5, 10)),
    	            new GymMember("Sneha Kapoor", LocalDate.of(2026, 1, 31)),
    	            new GymMember("Vikram Singh", LocalDate.of(2026, 4, 20))
    	        );
   members.stream().filter((mem) -> {
   LocalDate today = LocalDate.now();
   LocalDate next30 = today.plusDays(30);
  return mem.getExpiryDate().isAfter(today) && mem.getExpiryDate().isBefore(next30);
  }).forEach(System.out::println);
   
	}
}