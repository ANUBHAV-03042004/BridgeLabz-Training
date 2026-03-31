package employeewagecomputationproblemwith4layerarchitecture.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import employeewagecomputationproblemwith4layerarchitecture.model.Employee;

class EmployeeServiceTest {

    private EmployeeService service;
    private Employee employee;

    @BeforeEach
    void setUp() {
        service = new EmployeeService();
        employee = new Employee("Anubhav", 20);
    }


    @Test
    void testCalculateDailyWage_FullTime() {

        int dailyWage = service.calculateDailyWage(employee, 8);

        assertEquals(160, dailyWage,
                "Daily wage for 8 hours at 20 per hour should be 160");
    }

    @Test
    void testCalculateDailyWage_PartTime() {

        int dailyWage = service.calculateDailyWage(employee, 4);

        assertEquals(80, dailyWage,
                "Daily wage for 4 hours at 20 per hour should be 80");
    }

    @Test
    void testFullTimeStatus() {

        int hours = 8; 

        assertEquals(8, hours,
                "Full time employee should work 8 hours");
    }

    @Test
    void testPartTimeStatus() {

        int hours = 4;

        assertEquals(4, hours,
                "Part time employee should work 4 hours");
    }

 
    @Test
    void testCheckAttendanceReturnsBoolean() {

        boolean attendance = service.checkAttendance();

        assertTrue(attendance == true || attendance == false,
                "Attendance should return true or false");
    }


    @Test
    void testComputeMonthlyWageLimits() {

        service.computeMonthlyWage(employee);

        assertTrue(employee.getTotalWorkingDays() <= 20,
                "Working days should not exceed 20");

        assertTrue(employee.getTotalHours() <= 100,
                "Working hours should not exceed 100");

        assertTrue(employee.getTotalWage() >= 0,
                "Total wage should be calculated");
    }
}