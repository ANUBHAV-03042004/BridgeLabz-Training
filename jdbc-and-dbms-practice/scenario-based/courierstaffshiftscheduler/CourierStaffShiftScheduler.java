package courierstaffshiftscheduler;

import java.util.NoSuchElementException;

public class CourierStaffShiftScheduler {

    public static void main(String[] args) {

        ShiftScheduler scheduler = new ShiftScheduler();

        scheduler.addEmployee(new Employee("E001", "Alice",   Department.DELIVERY));
        scheduler.addEmployee(new Employee("E002", "Bob",     Department.SORTING));
        scheduler.addEmployee(new Employee("E003", "Carol",   Department.DISPATCH));
        scheduler.addEmployee(new Employee("E004", "Dave",    Department.DELIVERY));
        scheduler.addEmployee(new Employee("E005", "Eve",     Department.CUSTOMER_SUPPORT));
        scheduler.addEmployee(new Employee("E006", "Frank",   Department.SORTING));
        scheduler.addEmployee(new Employee("E001", "Duplicate", Department.DELIVERY)); // warn

       
        try {
            scheduler.assignShift("E001", ShiftTime.MORNING);
            scheduler.assignShift("E002", ShiftTime.MORNING);
            scheduler.assignShift("E003", ShiftTime.AFTERNOON);
            scheduler.assignShift("E004", ShiftTime.EVENING);
            scheduler.assignShift("E005", ShiftTime.NIGHT);
            scheduler.assignShift("E006", ShiftTime.MORNING);
            scheduler.assignShift("E001", ShiftTime.EVENING); 
        } catch (ShiftAlreadyAssignedException e) {
            System.out.println("[CAUGHT] " + e.getMessage());
        }

        try {
            scheduler.assignShift("E001", ShiftTime.MORNING); // already in MORNING
        } catch (ShiftAlreadyAssignedException e) {
            System.out.println("[CAUGHT] " + e.getMessage());
            System.out.println("         employeeId=" + e.getEmployeeId()
                    + ", shift=" + e.getShiftTime());
        }

        try {
            scheduler.assignShift("E003", ShiftTime.AFTERNOON); 
        } catch (ShiftAlreadyAssignedException e) {
            System.out.println("[CAUGHT] " + e.getMessage());
        }

     
        Employee frank = scheduler.findEmployee("E006");
        frank.setStatus(EmployeeStatus.ON_LEAVE);
        try {
            scheduler.assignShift("E006", ShiftTime.NIGHT); // skipped — on leave
        } catch (ShiftAlreadyAssignedException e) {
            System.out.println("[CAUGHT] " + e.getMessage());
        }

        try {
            scheduler.assignShift("E999", ShiftTime.MORNING);
        } catch (NoSuchElementException e) {
            System.out.println("[CAUGHT] " + e.getMessage());
        } catch (ShiftAlreadyAssignedException e) {
            System.out.println("[CAUGHT] " + e.getMessage());
        }

       
        scheduler.printFullSchedule();

      
        scheduler.printEmployeeShifts("E001");
        scheduler.printEmployeeShifts("E005");

      
        scheduler.printUnassigned();

     
        scheduler.unassignShift("E002", ShiftTime.MORNING);
        scheduler.unassignShift("E002", ShiftTime.NIGHT); // not assigned — warns
        try {
            scheduler.assignShift("E002", ShiftTime.EVENING);
        } catch (ShiftAlreadyAssignedException e) {
            System.out.println("[CAUGHT] " + e.getMessage());
        }

       
        scheduler.getByDepartment(Department.DELIVERY)
                 .forEach(e -> System.out.println("  " + e));

   
        scheduler.getEmployeesOnShift(ShiftTime.MORNING)
                 .forEach(e -> System.out.println("  " + e.getName() + " [" + e.getDepartment() + "]"));

   
        scheduler.printFullSchedule();
        scheduler.printAllEmployees();
        scheduler.printUnassigned();
    }
}
