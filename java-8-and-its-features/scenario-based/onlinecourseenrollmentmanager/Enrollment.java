package onlinecourseenrollmentmanager;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Enrollment {

    private final String         studentName;
    private final String         courseName;
    private final CourseCategory category;
    private final LocalDate      enrollmentDate;

    public Enrollment(String studentName, String courseName,
                      CourseCategory category, LocalDate enrollmentDate) {
        this.studentName    = studentName;
        this.courseName     = courseName;
        this.category       = category;
        this.enrollmentDate = enrollmentDate;
    }

    public String         getStudentName()    { return studentName;    }
    public String         getCourseName()     { return courseName;     }
    public CourseCategory getCategory()       { return category;       }
    public LocalDate      getEnrollmentDate() { return enrollmentDate; }

    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        return String.format("%-20s %-35s %-18s %s",
                studentName, courseName, category, enrollmentDate.format(fmt));
    }
}

