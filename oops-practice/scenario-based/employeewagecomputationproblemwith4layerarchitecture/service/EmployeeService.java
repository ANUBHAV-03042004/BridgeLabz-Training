package employeewagecomputationproblemwith4layerarchitecture.service;

import java.util.Random;
import employeewagecomputationproblemwith4layerarchitecture.model.Employee;
import employeewagecomputationproblemwith4layerarchitecture.model.Company;

public class EmployeeService {

//class variables (constants)
    private static final int MAX_WORKING_DAYS = 20;
    private static final int MAX_WORKING_HOURS = 100;
    private static final int FULL_TIME_HOURS = 8;
    private static final int PART_TIME_HOURS = 4;

    private Random random = new Random();


    public boolean checkAttendance() {
        return random.nextInt(2) == 1;
    }

    
    public int findEmployeeStatus() {

        int status = random.nextInt(2);

        switch (status) {
            case 0:
            	System.out.println("Full Time Employee");
                return FULL_TIME_HOURS;

            case 1:
            	System.out.println("Part Time Employee");
                return PART_TIME_HOURS;

            default:
                return 0;
        }
    }

    public int calculateDailyWage(Employee employee, int hoursWorked) {
        return hoursWorked * employee.getWagePerHour();
    }

    public void computeMonthlyWage(Employee employee) {

        while (employee.getTotalWorkingDays() < MAX_WORKING_DAYS &&
               employee.getTotalHours() < MAX_WORKING_HOURS) {

            employee.setTotalWorkingDays(
                    employee.getTotalWorkingDays() + 1);

            System.out.println("\nDay: " + employee.getTotalWorkingDays());

            if (checkAttendance()) {

                System.out.println(employee.getName() + " is Present");

                int hoursWorked = findEmployeeStatus();

                employee.setTotalHours(
                        employee.getTotalHours() + hoursWorked);

                int dailyWage = calculateDailyWage(employee, hoursWorked);

                employee.setTotalWage(
                        employee.getTotalWage() + dailyWage);

                System.out.println("Hours Worked: " + hoursWorked);
                System.out.println("Daily Wage: " + dailyWage);
            }
            else {
                System.out.println(employee.getName() + " is Absent");
            }

        }

        System.out.println("\nMonthly Wage: " + employee.getTotalWage());
    }
}




