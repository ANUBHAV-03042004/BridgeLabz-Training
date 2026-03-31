package employeewagecomputationproblemwith4layerarchitecture.model;

public class Employee {

    private String name;
    private int wagePerHour;
    private int totalWage;
    private int totalHours;
    private int totalWorkingDays;

    public Employee(String name, int wagePerHour) {
        this.name = name;
        this.wagePerHour = wagePerHour;
    }

    public String getName() { return name; }
    public int getWagePerHour() { return wagePerHour; }

    public int getTotalWage() { return totalWage; }
    public void setTotalWage(int totalWage) { this.totalWage = totalWage; }

    public int getTotalHours() { return totalHours; }
    public void setTotalHours(int totalHours) { this.totalHours = totalHours; }

    public int getTotalWorkingDays() { return totalWorkingDays; }
    public void setTotalWorkingDays(int totalWorkingDays) { this.totalWorkingDays = totalWorkingDays; }
}