package employeewagecomputationproblemwith4layerarchitecture.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import employeewagecomputationproblemwith4layerarchitecture.model.Company;

public class CompanyWageBuilderTest {
	  private EmpWageBuilder companyWageBuilder;

	    @BeforeEach
	    void setUp() {
	      
	        companyWageBuilder = new EmpWageBuilder(3);

	        companyWageBuilder.addCompany("TCS", 20, 20, 100);
	        companyWageBuilder.addCompany("Infosys", 25, 22, 120);
	    }
	    @Test
	    void testComputeWagesShouldGenerateNonNegativeWage() {

	        companyWageBuilder.computeCompanyWageForEmployeeWage();

	        int tcsWage = companyWageBuilder.getTotalWage("TCS");
	        int infosysWage = companyWageBuilder.getTotalWage("Infosys");

	        assertTrue(tcsWage >= 0, "TCS wage should not be negative");
	        assertTrue(infosysWage >= 0, "Infosys wage should not be negative");
	    }
	    
	    @Test
	    void testWageShouldNotExceedMaxLimit() {

	        companyWageBuilder.computeCompanyWageForEmployeeWage();

	        int tcsMaxPossible = 100 * 20; // maxHours * wagePerHour
	        int infosysMaxPossible = 120 * 25;

	        assertTrue(companyWageBuilder.getTotalWage("TCS") <= tcsMaxPossible,
	                "TCS wage exceeded maximum possible limit");

	        assertTrue(companyWageBuilder.getTotalWage("Infosys") <= infosysMaxPossible,
	                "Infosys wage exceeded maximum possible limit");
	    }
	    
	    @Test
	    void testGetTotalWageForInvalidCompany() {

	        int wage = companyWageBuilder.getTotalWage("Wipro");

	        assertEquals(-1, wage,
	                "Should return -1 for non-existing company");
	    }

	    @Test
	    void testMultipleCompaniesManagedCorrectly() {

	        companyWageBuilder.computeCompanyWageForEmployeeWage();;

	        assertNotEquals(
	        		 companyWageBuilder.getTotalWage("TCS"),
	        		 companyWageBuilder.getTotalWage("Infosys"),
	                "Different companies should have independent wage calculation"
	        );
	    }
	    
	    @Test
	    void testTotalWageShouldEqualSumOfDailyWages() {

	    	 companyWageBuilder.computeCompanyWageForEmployeeWage();

	    	    Company company = companyWageBuilder.getCompany("TCS");

	        int[] dailyWages = company.getDailyWages();

	        int sum = 0;
	        for (int wage : dailyWages) {
	            sum += wage;
	        }

	        assertEquals(sum,
	                company.getTotalWage(),
	                "Total wage must equal sum of daily wages");
	    }
	    @Test
	    void testGetTotalWageForValidCompany() {

	        // Arrange
	        companyWageBuilder.computeCompanyWageForEmployeeWage();

	        // Act
	        int tcsWage = companyWageBuilder.getTotalWage("TCS");

	        // Assert
	        assertTrue(tcsWage > 0, 
	                "Total wage for TCS should be greater than 0 after computation");
	    }

	  

}
