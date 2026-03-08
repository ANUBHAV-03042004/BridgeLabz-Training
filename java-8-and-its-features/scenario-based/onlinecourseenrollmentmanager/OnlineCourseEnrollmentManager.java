package onlinecourseenrollmentmanager;

import java.time.LocalDate;

public class OnlineCourseEnrollmentManager {

    public static void main(String[] args) {

        EnrollmentManager manager = new EnrollmentManager();

        manager.addEnrollment(new Enrollment("Alice",   "Java Programming",         CourseCategory.PROGRAMMING,    LocalDate.of(2025, 1, 10)));
        manager.addEnrollment(new Enrollment("Bob",     "Python for Data Science",  CourseCategory.DATA_SCIENCE,   LocalDate.of(2025, 1, 15)));
        manager.addEnrollment(new Enrollment("Carol",   "Java Programming",         CourseCategory.PROGRAMMING,    LocalDate.of(2025, 1, 20)));
        manager.addEnrollment(new Enrollment("Dave",    "UI/UX Design Fundamentals",CourseCategory.DESIGN,         LocalDate.of(2025, 2, 5)));
        manager.addEnrollment(new Enrollment("Eve",     "Machine Learning A-Z",     CourseCategory.DATA_SCIENCE,   LocalDate.of(2025, 2, 10)));
        manager.addEnrollment(new Enrollment("Frank",   "AWS Cloud Practitioner",   CourseCategory.CLOUD,          LocalDate.of(2025, 2, 18)));
        manager.addEnrollment(new Enrollment("Grace",   "Java Programming",         CourseCategory.PROGRAMMING,    LocalDate.of(2025, 3, 1)));
        manager.addEnrollment(new Enrollment("Heidi",   "Ethical Hacking",          CourseCategory.CYBERSECURITY,  LocalDate.of(2025, 3, 5)));
        manager.addEnrollment(new Enrollment("Ivan",    "Python for Data Science",  CourseCategory.DATA_SCIENCE,   LocalDate.of(2025, 3, 12)));
        manager.addEnrollment(new Enrollment("Judy",    "Digital Marketing",        CourseCategory.BUSINESS,       LocalDate.of(2025, 3, 20)));
        manager.addEnrollment(new Enrollment("Karl",    "AWS Cloud Practitioner",   CourseCategory.CLOUD,          LocalDate.of(2025, 4, 2)));
        manager.addEnrollment(new Enrollment("Laura",   "Machine Learning A-Z",     CourseCategory.DATA_SCIENCE,   LocalDate.of(2025, 4, 8)));
        manager.addEnrollment(new Enrollment("Mike",    "Ethical Hacking",          CourseCategory.CYBERSECURITY,  LocalDate.of(2025, 4, 14)));
        manager.addEnrollment(new Enrollment("Nancy",   "Java Programming",         CourseCategory.PROGRAMMING,    LocalDate.of(2025, 4, 22)));
        manager.addEnrollment(new Enrollment("Oscar",   "UI/UX Design Fundamentals",CourseCategory.DESIGN,         LocalDate.of(2025, 5, 3)));
        manager.addEnrollment(new Enrollment("Priya",   "Digital Marketing",        CourseCategory.BUSINESS,       LocalDate.of(2025, 5, 9)));
        manager.addEnrollment(new Enrollment("Quinn",   "Python for Data Science",  CourseCategory.DATA_SCIENCE,   LocalDate.of(2025, 5, 15)));
        manager.addEnrollment(new Enrollment("Rachel",  "AWS Cloud Practitioner",   CourseCategory.CLOUD,          LocalDate.of(2025, 5, 20)));

        System.out.println("\nTotal Enrollments : " + manager.getTotalEnrollments());

        Display.printHeader("Filter by Course: Java Programming");
        Display.printList(manager.filterByCourse("Java Programming"));

        Display.printHeader("Filter by Course: Python for Data Science");
        Display.printList(manager.filterByCourse("Python for Data Science"));

     
        Display.printHeader("Filter by Category: DATA_SCIENCE");
        Display.printList(manager.filterByCategory(CourseCategory.DATA_SCIENCE));

        Display.printHeader("Filter by Category: CLOUD");
        Display.printList(manager.filterByCategory(CourseCategory.CLOUD));

     
        Display.printGrouped(manager.groupByCourseName());

     
        Display.printCounts(manager.countPerCategory());

    

        Display.printHeader("Sorted by Enrollment Date (Ascending)");
        Display.printList(manager.sortByEnrollmentDate());

      
        Display.printHeader("Sorted by Enrollment Date (Descending)");
        Display.printList(manager.sortByEnrollmentDateDesc());

        System.out.println("\nAll Available Courses");
        manager.getAllCourseNames().forEach(c -> System.out.println("  " + c));

       
        System.out.println("\nAll Enrolled Students");
        manager.getAllStudentNames().forEach(s -> System.out.println("  " + s));

        System.out.println("\nMost Popular Course");
        manager.getMostPopularCourse().ifPresent(c -> System.out.println("  " + c));

     
        LocalDate cutoff = LocalDate.of(2025, 4, 1);
        Display.printHeader("Enrollments After " + cutoff);
        Display.printList(manager.enrollmentsAfter(cutoff));

  
        System.out.println("\nStudents in CYBERSECURITY");
        manager.studentsByCategory(CourseCategory.CYBERSECURITY)
               .forEach(s -> System.out.println("  " + s));

        System.out.println("\nStudents in PROGRAMMING");
        manager.studentsByCategory(CourseCategory.PROGRAMMING)
               .forEach(s -> System.out.println("  " + s));
    }
}