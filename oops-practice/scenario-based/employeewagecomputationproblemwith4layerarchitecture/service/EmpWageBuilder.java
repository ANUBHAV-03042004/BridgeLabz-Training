package employeewagecomputationproblemwith4layerarchitecture.service;

import java.util.Random;
import employeewagecomputationproblemwith4layerarchitecture.model.Company;

public class EmpWageBuilder {
    private static final int FULL_TIME_HOURS = 8;
    private static final int PART_TIME_HOURS = 4;
    private Company company;
    private int totalHours;
    private int totalDays;

    Random random = new Random();
    public EmpWageBuilder(Company company) {
        this.company = company;
    }

    public void computeEmployeeWage() {

        while (totalDays < company.getMaxWorkingDays() &&
               totalHours < company.getMaxWorkingHours()) {

            totalDays++;

        
            int hoursWorked = employeeStatus();

            if (totalHours + hoursWorked > company.getMaxWorkingHours()) {
                break;
            }

            totalHours += hoursWorked;
            company.setTotalWage(
                    company.getTotalWage() +
                    (hoursWorked * company.getWagePerHour()));
        }
    }

    public int employeeStatus() {
    	 int status = random.nextInt(2);

         switch (status) {
             case 0:

                 return FULL_TIME_HOURS;

             case 1:
  
                 return PART_TIME_HOURS;

             default:
                 return 0;
         }
	}

	public Company getCompany() {
        return company;
    }
}