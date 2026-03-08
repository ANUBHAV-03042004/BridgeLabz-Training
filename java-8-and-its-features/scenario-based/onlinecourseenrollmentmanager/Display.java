package onlinecourseenrollmentmanager;

import java.util.List;
import java.util.Map;

public class Display {

    private static final String HEADER =
            String.format("%-20s %-35s %-18s %s", "Student", "Course", "Category", "Date");

    public static void printHeader(String title) {
        System.out.println("\n" + title);
        System.out.println(HEADER);
     
    }

    public static void printList(List<Enrollment> list) {
        if (list.isEmpty()) {
            System.out.println("  (no records found)");
            return;
        }
        list.forEach(System.out::println);
        System.out.println("Total: " + list.size());
    }

    public static void printGrouped(Map<String, List<Enrollment>> grouped) {
        grouped.forEach((course, list) -> {
            System.out.println("\n  [" + course + "]  (" + list.size() + " enrolled)");
            list.forEach(e -> System.out.println("    - " + e.getStudentName()
                    + "  [" + e.getEnrollmentDate() + "]"));
        });
    }

    public static void printCounts(Map<CourseCategory, Long> counts) {
        counts.entrySet().stream()
                .sorted(Map.Entry.<CourseCategory, Long>comparingByValue().reversed())
                .forEach(entry ->
                    System.out.printf("  %-20s : %d enrollment(s)%n",
                            entry.getKey(), entry.getValue()));
    }
}
