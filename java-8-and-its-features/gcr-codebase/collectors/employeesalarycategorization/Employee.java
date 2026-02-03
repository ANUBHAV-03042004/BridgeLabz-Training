package employeesalarycategorization;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Employee {
String name;
String department;
double salary;
Employee(String name,String department,double salary){
	this.name=name;
	this.department=department;
	this.salary=salary;
}
String getDepartment() {
	return department;
}
String getName() {
	 return name;
 }
 double getSalary() {
	 return salary;
 }
 
 public static void main(String[] args) {
     Employee e1 = new Employee("Alice", "HR", 45000.0);
     Employee e2 = new Employee("Bob", "Finance", 55000.0);
     Employee e3 = new Employee("Charlie", "IT", 60000.0);
     Employee e4 = new Employee("Diana", "HR", 50000.0);
     Employee e5 = new Employee("Eve", "Sales", 47000.0);
     Employee e6 = new Employee("Frank", "HR", 52000.0);
     
     List<Employee> employeeList = List.of(e1, e2, e3, e4, e5, e6);
     Map<String, Double>employeeCategorization = employeeList.stream().collect(Collectors.groupingBy(Employee::getDepartment,Collectors.averagingDouble(Employee::getSalary)));
     employeeCategorization.forEach((getDepartment,getSalary)->{
    	 System.out.println(getDepartment+" -> "+ getSalary);
     });
}
}
