package employeewagecomputationproblemwith4layerarchitecture.service;

import java.util.ArrayList;
import java.util.Random;
import employeewagecomputationproblemwith4layerarchitecture.model.Company;

public class EmpWageBuilder implements IEmpWageBuilder {
    private static final int FULL_TIME_HOURS = 8;
    private static final int PART_TIME_HOURS = 4;
    private Company company;
    private int totalHours;
    private int totalDays;

//    private Company[] companyEmpWage;
    private ArrayList<Company> companyEmpWage;
//    private int companyCount = 0;

    Random random = new Random();
    public EmpWageBuilder(Company company) {
        this.company = company;
    }
    public EmpWageBuilder(int size) {
//        this.companyEmpWage = new Company[size];
    	this.companyEmpWage = new ArrayList<>(size);
    }
  
    @Override
  public void addCompany(String name, int wagePerHour, int maxDays,int maxHours) {
//companyEmpWage[companyCount++] = new Company(name, wagePerHour, maxDays, maxHours);
    	companyEmpWage.add(new Company(name, wagePerHour, maxDays, maxHours));
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
	@Override
	 public int getTotalWage(String companyName) {
//	        for (int i = 0; i < companyCount; i++) {
		   for (int i = 0; i < companyEmpWage.size(); i++) {
//	            if (companyEmpWage[i].getCompanyName().equals(companyName)) {
		       if (companyEmpWage.get(i).getCompanyName().equals(companyName)) {
//	                return companyEmpWage[i].getTotalWage();
		    	   return companyEmpWage.get(i).getTotalWage();
	            }
	        }
	        return -1;
	    }
	@Override
	 public void computeCompanyWageForEmployeeWage() {
//		 for(int i=0;i<companyCount;i++) {
		 for(int i=0;i<companyEmpWage.size();i++) {
//           Company company = companyEmpWage[i];
			  Company company = companyEmpWage.get(i);
           int totalHours = 0;
           int totalDays = 0;
           int totalWage = 0;
	        while (totalDays < company.getMaxWorkingDays() &&
	               totalHours < company.getMaxWorkingHours()) {

	            totalDays++;

	        
	            int hoursWorked = employeeStatus();

	            if (totalHours + hoursWorked > company.getMaxWorkingHours()) {
	                break;
	            }

	            totalHours += hoursWorked;
	            totalWage += hoursWorked * company.getWagePerHour();
	            company.setTotalWage(totalWage);
//	            System.out.println(company);
	        }
		 }
	    }
	
}