package employeewagecomputationproblemwith4layerarchitecture.model;

public class Company {

    private String companyName;
    private int wagePerHour;
    private int maxWorkingDays;
    private int maxWorkingHours;
    private int totalWage;

    public Company(String companyName, int wagePerHour,
                   int maxWorkingDays, int maxWorkingHours) {

        this.companyName = companyName;
        this.wagePerHour = wagePerHour;
        this.maxWorkingDays = maxWorkingDays;
        this.maxWorkingHours = maxWorkingHours;
    }

    public String getCompanyName() { return companyName; }
    public int getWagePerHour() { return wagePerHour; }
    public int getMaxWorkingDays() { return maxWorkingDays; }
    public int getMaxWorkingHours() { return maxWorkingHours; }

    public int getTotalWage() { return totalWage; }
    public void setTotalWage(int totalWage) { this.totalWage = totalWage; }
    
    @Override
    public String toString() {
        return "Total Wage for " + companyName + " is: " + totalWage;
    }
}