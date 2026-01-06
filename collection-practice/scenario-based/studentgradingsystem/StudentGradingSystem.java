package studentgradingsystem;

	import java.util.*;

	class StudentGradingSystem {
	    String[] studentNames;
	    int[] studentIDs;
	    double[][] grades;

	    public StudentGradingSystem(String[] names, int[] ids, double[][] grades) {
	        this.studentNames = names;
	        this.studentIDs = ids;
	        this.grades = grades;
	    }

	    //  Validate grades
	    private double validateGrade(double grade) throws IllegalArgumentException {
	        if (grade < 0 || grade > 100) {
	            throw new IllegalArgumentException("Grade must be between 0 and 100.");
	        }
	        return grade;
	    }

	    // Calculate total and average for each student
	    public void calculateStudentTotals() {
	        for (int i = 0; i < grades.length; i++) {
	            double total = 0;
	            int count = 0;
	            for (int j = 0; j < grades[i].length; j++) {
	                if (grades[i][j] >= 0) { // skip missing grades (-1 used as placeholder)
	                    try {
	                        total += validateGrade(grades[i][j]);
	                        count++;
	                    } catch (IllegalArgumentException e) {
	                        System.out.println("Invalid grade for " + studentNames[i] + ": " + grades[i][j]);
	                    }
	                }
	            }
	            double avg = (count > 0) ? total / count : 0;
	            System.out.println(studentNames[i] + " (ID: " + studentIDs[i] + ") → Total: " + total + ", Average: " + avg);
	        }
	    }

	    //  Highest grade in each subject
	    public void highestGradePerSubject() {
	        int subjects = grades[0].length;
	        for (int j = 0; j < subjects; j++) {
	            double highest = -1;
	            String topStudent = "None";
	            for (int i = 0; i < grades.length; i++) {
	                if (grades[i][j] >= 0) { // skip missing
	                    if (grades[i][j] > highest) {
	                        highest = grades[i][j];
	                        topStudent = studentNames[i];
	                    }
	                }
	            }
	            System.out.println("Subject " + (j + 1) + " → Highest: " + highest + " by " + topStudent);
	        }
	    }

	    // Overall class average
	    public void classAverage() {
	        double total = 0;
	        int count = 0;
	        for (int i = 0; i < grades.length; i++) {
	            for (int j = 0; j < grades[i].length; j++) {
	                if (grades[i][j] >= 0) {
	                    try {
	                        total += validateGrade(grades[i][j]);
	                        count++;
	                    } catch (IllegalArgumentException e) {
	                        System.out.println("Invalid grade detected: " + grades[i][j]);
	                    }
	                }
	            }
	        }
	        double avg = (count > 0) ? total / count : 0;
	        System.out.println("Overall Class Average: " + avg);
	    }

	    // Main method
	    public static void main(String[] args) {
	        String[] names = {"Alice", "Bob", "Charlie"};
	        int[] ids = {101, 102, 103};

	        // -1 indicates missing grade
	        double[][] grades = {
	            {85, 90, 78},     // Alice
	            {88, -1, 92},     // Bob (missing one subject)
	            {100, 95, 99}     // Charlie
	        };

	        StudentGradingSystem sgs = new StudentGradingSystem(names, ids, grades);

	        System.out.println("Student Totals & Averages");
	        sgs.calculateStudentTotals();

	        System.out.println("\nHighest Grade Per Subject ");
	        sgs.highestGradePerSubject();

	        System.out.println("\nClass Average ");
	        sgs.classAverage();
	    }
	}