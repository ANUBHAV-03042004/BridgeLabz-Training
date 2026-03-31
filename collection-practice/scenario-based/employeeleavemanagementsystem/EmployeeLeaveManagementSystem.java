package employeeleavemanagementsystem;

import java.util.NoSuchElementException;

public class EmployeeLeaveManagementSystem {

    public static void main(String[] args) {

        LeaveManagementService service = new LeaveManagementService();

      
        System.out.println("\nAdding Employees");
        service.addEmployee(new Employee("E001", "Alice Johnson",  Department.ENGINEERING));
        service.addEmployee(new Employee("E002", "Bob Smith",      Department.HR));
        service.addEmployee(new Employee("E003", "Carol Williams", Department.FINANCE));
        service.addEmployee(new Employee("E001", "Duplicate",      Department.SALES)); // warn

     
        service.printLeaveBalance("E001");
        service.printLeaveBalance("E002");

        System.out.println("\nApplying Leave Requests");
        String req1 = null, req2 = null, req3 = null;

        try {
            LeaveRequest r = service.applyLeave(
                    "E001", LeaveType.CASUAL,
                    "2025-07-01", "2025-07-03", 3, "Family trip");
            req1 = r.getRequestId();
        } catch (InsufficientLeaveBalanceException e) {
            System.out.println("[ERROR] " + e.getMessage());
        }

        try {
            LeaveRequest r = service.applyLeave(
                    "E002", LeaveType.SICK,
                    "2025-07-10", "2025-07-11", 2, "Fever");
            req2 = r.getRequestId();
        } catch (InsufficientLeaveBalanceException e) {
            System.out.println("[ERROR] " + e.getMessage());
        }

        try {
            LeaveRequest r = service.applyLeave(
                    "E001", LeaveType.EARNED,
                    "2025-08-01", "2025-08-15", 15, "Vacation");
            req3 = r.getRequestId();
        } catch (InsufficientLeaveBalanceException e) {
            System.out.println("[ERROR] " + e.getMessage());
        }

        try {
            service.applyLeave(
                    "E001", LeaveType.CASUAL,
                    "2025-09-01", "2025-09-20", 20, "Extended trip"); // only 12 casual days
        } catch (InsufficientLeaveBalanceException e) {
            System.out.println("[CAUGHT] " + e.getMessage());
            System.out.println("         employeeId=" + e.getEmployeeId()
                               + ", requested=" + e.getRequested()
                               + ", available=" + e.getAvailable());
        }

    
        try {
            service.applyLeave("E999", LeaveType.SICK, "2025-07-01", "2025-07-02", 1, "Test");
        } catch (NoSuchElementException e) {
            System.out.println("[CAUGHT] " + e.getMessage());
        } catch (InsufficientLeaveBalanceException e) {
            System.out.println("[ERROR] " + e.getMessage());
        }

        if (req1 != null) service.approveLeave(req1, "Approved, enjoy your trip!");
        if (req2 != null) service.rejectLeave(req2, "Critical project deadline this week");
        if (req3 != null) service.approveLeave(req3, "Approved.");

      
        if (req1 != null) service.approveLeave(req1, "Should warn");

        service.printLeaveBalance("E001");
        service.printLeaveBalance("E002");

        service.printAllRequests();

        System.out.println("\nPending Requests");
        service.getRequestsByStatus(LeaveStatus.PENDING)
               .forEach(r -> System.out.println("  " + r));

        System.out.println("\nRequests for E001");
        service.getRequestsByEmployee("E001")
               .forEach(r -> System.out.println("  " + r));
    }
}



















































































