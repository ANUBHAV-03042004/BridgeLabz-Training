package onlinecourseenrollmentmanager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class EnrollmentManager {

    private final List<Enrollment> enrollments = new ArrayList<>();

 
    public void addEnrollment(Enrollment e) {
        enrollments.add(e);
    }

    public List<Enrollment> filterByCourse(String courseName) {
        return enrollments.stream()
                .filter(e -> e.getCourseName().equalsIgnoreCase(courseName))
                .collect(Collectors.toList());
    }

    public List<Enrollment> filterByCategory(CourseCategory category) {
        return enrollments.stream()
                .filter(e -> e.getCategory() == category)
                .collect(Collectors.toList());
    }

   
    public Map<String, List<Enrollment>> groupByCourseName() {
        return enrollments.stream()
                .collect(Collectors.groupingBy(Enrollment::getCourseName));
    }

   
    public Map<CourseCategory, Long> countPerCategory() {
        return enrollments.stream()
                .collect(Collectors.groupingBy(Enrollment::getCategory, Collectors.counting()));
    }

    public List<Enrollment> sortByEnrollmentDate() {
        return enrollments.stream()
                .sorted(Comparator.comparing(Enrollment::getEnrollmentDate))
                .collect(Collectors.toList());
    }

  
    public List<Enrollment> sortByEnrollmentDateDesc() {
        return enrollments.stream()
                .sorted(Comparator.comparing(Enrollment::getEnrollmentDate).reversed())
                .collect(Collectors.toList());
    }

    public List<String> getAllCourseNames() {
        return enrollments.stream()
                .map(Enrollment::getCourseName)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    public List<String> getAllStudentNames() {
        return enrollments.stream()
                .map(Enrollment::getStudentName)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    
    public long getTotalEnrollments() {
        return enrollments.stream().count();
    }

    public Optional<String> getMostPopularCourse() {
        return enrollments.stream()
                .collect(Collectors.groupingBy(Enrollment::getCourseName, Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey);
    }

    public List<Enrollment> enrollmentsAfter(LocalDate date) {
        return enrollments.stream()
                .filter(e -> e.getEnrollmentDate().isAfter(date))
                .sorted(Comparator.comparing(Enrollment::getEnrollmentDate))
                .collect(Collectors.toList());
    }

    public List<String> studentsByCategory(CourseCategory category) {
        return enrollments.stream()
                .filter(e -> e.getCategory() == category)
                .map(Enrollment::getStudentName)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }
}
