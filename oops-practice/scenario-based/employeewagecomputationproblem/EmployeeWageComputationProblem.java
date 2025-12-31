package employeewagecomputationproblem;

import java.util.Random;

public class EmployeeWageComputationProblem {
    Random random = new Random();
    String Empname;
    int wageperhour = 20;
    int totalwage = 0;

    public String checkattendance(String Empname) {
        int attendance_value = random.nextInt(2);
        String attendance = (attendance_value == 1) ? "Present" : "Absent";
        System.out.println(Empname + " is " + attendance);
        return attendance;
    }

    public int findEmployeeStatus(String Empname, String attendance) {
        int status_value = random.nextInt(2);
        String empstatus="";
        int EmployeehoursWorked=0;
        switch (status_value) {
            case 0:
                empstatus = Empname + " is a Full Time Employee.";    
                EmployeehoursWorked=8;
                dailyEmployeeWage(attendance, 8);
                break;
            case 1:
                empstatus = Empname + " is a Part Time Employee.";
                EmployeehoursWorked=4;
                dailyEmployeeWage(attendance, 4);
                break;
        }
        System.out.println(empstatus);
        return EmployeehoursWorked;
    }

    public void dailyEmployeeWage(String attendance, int hoursworked) {
        if (attendance.equalsIgnoreCase("present")) {
            int wage = wageperhour * hoursworked;
            totalwage += wage;
            System.out.println(Empname + " will get " + wage + " rs. as daily wage.");
        }
    }

    public String calculateWageForAMonth() {
        return Empname + " will get " + totalwage + " rs. as Monthly wage.";
    }
}