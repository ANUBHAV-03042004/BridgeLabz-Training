package m1practiceproblem.set2.quizperformancerankingsystem;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

class StudentRecord{
	String studentName;
	String department;
	int quiz1;
	int quiz2;
	int quiz3;
public StudentRecord(String studentName, String department, int quiz1, int quiz2, int quiz3) {
		this.studentName = studentName;
		this.department = department;
		this.quiz1 = quiz1;
		this.quiz2 = quiz2;
		this.quiz3 = quiz3;
	}
public String getStudentName() {
	return studentName;
}
public void setStudentName(String studentName) {
	this.studentName = studentName;
}
public String getDepartment() {
	return department;
}
public void setDepartment(String department) {
	this.department = department;
}
public int getQuiz1() {
	return quiz1;
}
public void setQuiz1(int quiz1) {
	this.quiz1 = quiz1;
}
public int getQuiz2() {
	return quiz2;
}
public void setQuiz2(int quiz2) {
	this.quiz2 = quiz2;
}
public int getQuiz3() {
	return quiz3;
}
public void setQuiz3(int quiz3) {
	this.quiz3 = quiz3;
}
public int getTotalScore()
{
	return getQuiz1()+ getQuiz2()+getQuiz3();
}
}
public class QuizPerformanceRankingSystem {
	static ArrayList<StudentRecord> record = new ArrayList<>();
	public static void recordData(StudentRecord studentRecord) {
		record.add(studentRecord);
	}
	public static void top(int quiz) {
		if(record.isEmpty()) { System.out.println("No records available"); return ;}
		Comparator<StudentRecord> comparator=null;
	      switch(quiz) {
	      case 1 :
	    	comparator = Comparator.comparingInt(StudentRecord::getQuiz1).reversed();
	    	break;
	      case 2:
	         comparator =  Comparator.comparingInt(StudentRecord::getQuiz2).reversed();
	         break;
	      case 3:
	    	 comparator =  Comparator.comparingInt(StudentRecord::getQuiz3).reversed();
	    	 break;
	      }
	      List<StudentRecord> sorted = record.stream()
	    	        .sorted(comparator.thenComparing(StudentRecord::getStudentName))
	    	        .collect(Collectors.toList());

	    	    int highest;
	    	    if (quiz == 1) highest = sorted.get(0).getQuiz1();
	    	    else if (quiz == 2) highest = sorted.get(0).getQuiz2();
	    	    else highest = sorted.get(0).getQuiz3();

	    	    sorted.stream()
	    	          .filter(s -> (quiz == 1 && s.getQuiz1() == highest) ||
	    	                       (quiz == 2 && s.getQuiz2() == highest) ||
	    	                       (quiz == 3 && s.getQuiz3() == highest))
	    	          .forEach(s -> {
	    	              int score = (quiz == 1 ? s.getQuiz1() : quiz == 2 ? s.getQuiz2() : s.getQuiz3());
	    	              System.out.println(s.getStudentName() + " " + score);
	    	          });

	}
	public static void top(String department) {
		if(record.isEmpty()) { System.out.println("No records available"); return ;}
	    List<StudentRecord> groupDept = record.stream()
	        .filter(s -> s.getDepartment().equalsIgnoreCase(department))
	        .sorted(
	            Comparator.comparingInt(StudentRecord::getTotalScore)
	                      .reversed()
	                      .thenComparing(StudentRecord::getStudentName)
	        )
	        .collect(Collectors.toList());

	    if (groupDept.isEmpty()) {
	        System.out.println("Department Not Found");
	    } else {
	        int highest = groupDept.get(0).getTotalScore();

	        groupDept.stream()
	                 .filter(s -> s.getTotalScore() == highest)
	                 .forEach(s -> System.out.println(s.getStudentName() + " " + s.getTotalScore()));
	    }
	}
public static void main(String[] args) {
	Scanner sc = new Scanner(System.in);
	int numberOfInput = sc.nextInt();
	sc.nextLine();
	while(numberOfInput-->0) {
		
	String input = sc.nextLine();
	String inputArray[]=input.split(" ");
	String command = inputArray[0];
	if(command.equalsIgnoreCase("record")) {
		StudentRecord sr = new StudentRecord(inputArray[1],inputArray[2],Integer.parseInt(inputArray[3]),Integer.parseInt(inputArray[4]),Integer.parseInt(inputArray[5]));
	recordData(sr);
	}
	if(command.equalsIgnoreCase("top")) {
		if(command.equalsIgnoreCase("top")) {
		    String arg = inputArray[1];
		    if(arg.equalsIgnoreCase("Q1") || arg.equalsIgnoreCase("Q2") || arg.equalsIgnoreCase("Q3")) {
		        int quizNum = Integer.parseInt(arg.substring(1)); // get 1,2,3
		        top(quizNum);
		    } else {
		        top(arg); 
		    }
		}
	}
	}
}
}
