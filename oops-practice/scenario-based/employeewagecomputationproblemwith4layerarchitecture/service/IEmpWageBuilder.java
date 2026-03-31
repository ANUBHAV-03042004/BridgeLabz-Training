package employeewagecomputationproblemwith4layerarchitecture.service;

public interface IEmpWageBuilder {

    void addCompany(String name,
                    int wagePerHour,
                    int maxWorkingDays,
                    int maxWorkingHours);

    void computeCompanyWageForEmployeeWage();

    int getTotalWage(String companyName);
}
