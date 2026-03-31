package studentresultgrouping;
	import java.util.*;
	import java.util.stream.Collectors;

	class Student {
	    String name;
	    String gradeLevel;

	    Student(String name, String gradeLevel) {
	        this.name = name;
	        this.gradeLevel = gradeLevel;
	    }

	    public String getName() {
	        return name;
	    }

	    public String getGradeLevel() {
	        return gradeLevel;
	    }
	}

	public class StudentResult {
	    public static void main(String[] args) {
	        List<Student> students = Arrays.asList(
	            new Student("Alice", "Grade 10"),
	            new Student("Bob", "Grade 10"),
	            new Student("Charlie", "Grade 11"),
	            new Student("David", "Grade 12"),
	            new Student("Eva", "Grade 11")
	        );

	        // Group students by grade level and collect names
	        Map<String, List<String>> groupedByGrade = students.stream()
	            .collect(Collectors.groupingBy(
	                Student::getGradeLevel,                     // key = grade level
	                Collectors.mapping(Student::getName, Collectors.toList()) // value = list of names
	            ));

	        // Print result
	        groupedByGrade.forEach((grade, names) -> 
	            System.out.println(grade + " → " + names)
	        );
	    }
	}