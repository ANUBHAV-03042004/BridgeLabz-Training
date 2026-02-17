package employeewagecomputationproblemwith4layerarchitecture.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import employeewagecomputationproblemwith4layerarchitecture.model.Company;

public class EmpWageBuilderTest {

    private Company company;
    private EmpWageBuilder builder;

    @BeforeEach
    void setUp() {
        company = new Company("TCS", 20, 20, 100);
        builder = new EmpWageBuilder(company);
    }

    @Test
    void testEmployeeStatusShouldReturnValidHours() {
        int hours = builder.employeeStatus();

        assertTrue(hours == 8 || hours == 4,
                "Employee status should return either 8 (Full Time) or 4 (Part Time)");
    }

    @Test
    void testComputeEmployeeWageShouldCalculateWage() {
        builder.computeEmployeeWage();

        int totalWage = company.getTotalWage();

        assertTrue(totalWage >= 0,
                "Total wage should not be negative");
    }

    @Test
    void testTotalWageShouldNotExceedMaxPossibleWage() {
        builder.computeEmployeeWage();

        int maxPossibleWage =
                company.getMaxWorkingHours() * company.getWagePerHour();

        assertTrue(company.getTotalWage() <= maxPossibleWage,
                "Total wage should not exceed maximum allowed wage");
    }

    @Test
    void testCompanyShouldBeReturnedCorrectly() {
        Company returnedCompany = builder.getCompany();

        assertEquals("TCS", returnedCompany.getCompanyName());
    }
}
