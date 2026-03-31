package employeewagecomputationproblemwith4layerarchitecture.model;

public class Company {

    private String companyName;
    private int wagePerHour;
    private int maxWorkingDays;
    private int maxWorkingHours;
    private int totalWage;
    private int[] dailyWages;
    private int dayCount;
    public Company(String companyName, int wagePerHour,
                   int maxWorkingDays, int maxWorkingHours) {

        this.companyName = companyName;
        this.wagePerHour = wagePerHour;
        this.maxWorkingDays = maxWorkingDays;
        this.maxWorkingHours = maxWorkingHours;
        this.dailyWages = new int[maxWorkingDays];
    }

    public String getCompanyName() { return companyName; }
    public int getWagePerHour() { return wagePerHour; }
    public int getMaxWorkingDays() { return maxWorkingDays; }
    public int getMaxWorkingHours() { return maxWorkingHours; }

    public int getTotalWage() { return totalWage; }
    public void setTotalWage(int totalWage) { this.totalWage = totalWage; }
    public void addDailyWage(int wage) {
        dailyWages[dayCount++] = wage;
        totalWage += wage;
    }

    public int[] getDailyWages() {
        return dailyWages;
    }

    @Override
    public String toString() {
        return "Total Wage for " + companyName + " is: " + totalWage;
    }
}