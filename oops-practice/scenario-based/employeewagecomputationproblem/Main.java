package employeewagecomputationproblem;

import java.util.Scanner;
public class Main {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Employee Name : ");
        String Empname = sc.next();
//        created employee object
        EmployeeWageComputationProblem employee = new EmployeeWageComputationProblem();
        int totalworkingday = 0;
        int totalhours = 0;
// check for 20 working days or total hours worked
        while (totalworkingday < 20 && totalhours < 100) {
            String attendance = employee.checkattendance(Empname);
            if (attendance.equalsIgnoreCase("present")) {
                int hoursWorked = employee.findEmployeeStatus(Empname, attendance);
                totalhours += hoursWorked;
                totalworkingday++;
            }
        }
        System.out.println(employee.calculateWageForAMonth());
        sc.close();
    }
}