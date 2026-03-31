package employeeleavemanagementsystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class LeaveManagementService {

    private final Map<String, Employee>   employees     = new HashMap<>();   
    private final List<LeaveRequest>      leaveRequests = new ArrayList<>(); 


    public void addEmployee(Employee employee) {
        if (employees.containsKey(employee.getEmployeeId())) {
            System.out.println("[WARN] Employee " + employee.getEmployeeId() + " already exists.");
            return;
        }
        employees.put(employee.getEmployeeId(), employee);
        System.out.println("Employee added: " + employee.getName()
                           + " (" + employee.getEmployeeId() + ")");
    }

    public Employee getEmployee(String employeeId) {
        Employee e = employees.get(employeeId);
        if (e == null) throw new NoSuchElementException("Employee not found: " + employeeId);
        return e;
    }

    public Map<String, Employee> getAllEmployees() {
        return Collections.unmodifiableMap(employees);
    }

    
    public LeaveRequest applyLeave(String employeeId, LeaveType leaveType,
                                    String startDate, String endDate,
                                    int days, String reason)
            throws InsufficientLeaveBalanceException {

        Employee employee = getEmployee(employeeId);       // throws if not found
        employee.validateBalance(leaveType, days);        // throws if insufficient

        LeaveRequest request = new LeaveRequest(employeeId, leaveType,
                                                startDate, endDate, days, reason);
        leaveRequests.add(request);

        System.out.println("Leave applied: " + request.getRequestId()
                           + " by " + employee.getName()
                           + " (" + leaveType + ", " + days + " day(s))");
        return request;
    }

  
    public void approveLeave(String requestId, String managerRemarks) {
        LeaveRequest request = findRequest(requestId);

        if (request.getStatus() != LeaveStatus.PENDING) {
            System.out.println("[WARN] Request " + requestId
                               + " is already " + request.getStatus() + ".");
            return;
        }

        Employee employee = getEmployee(request.getEmployeeId());
        employee.deductBalance(request.getLeaveType(), request.getDays());

        request.setStatus(LeaveStatus.APPROVED);
        request.setRemarks(managerRemarks);

        System.out.println("Leave APPROVED: " + requestId
                           + " | " + employee.getName()
                           + " | Remaining " + request.getLeaveType()
                           + " balance: " + employee.getBalance(request.getLeaveType()));
    }

    public void rejectLeave(String requestId, String reason) {
        LeaveRequest request = findRequest(requestId);

        if (request.getStatus() != LeaveStatus.PENDING) {
            System.out.println("[WARN] Request " + requestId
                               + " is already " + request.getStatus() + ".");
            return;
        }

        request.setStatus(LeaveStatus.REJECTED);
        request.setRemarks(reason);

        System.out.println("Leave REJECTED: " + requestId
                           + " | Reason: " + reason);
    }

    public List<LeaveRequest> getRequestsByEmployee(String employeeId) {
        List<LeaveRequest> result = new ArrayList<>();
        for (LeaveRequest r : leaveRequests) {
            if (r.getEmployeeId().equals(employeeId)) result.add(r);
        }
        return result;
    }

    public List<LeaveRequest> getRequestsByStatus(LeaveStatus status) {
        List<LeaveRequest> result = new ArrayList<>();
        for (LeaveRequest r : leaveRequests) {
            if (r.getStatus() == status) result.add(r);
        }
        return result;
    }

    public void printAllRequests() {
        System.out.println("\nAll Leave Requests (" + leaveRequests.size() + ")");
        if (leaveRequests.isEmpty()) {
            System.out.println("  (none)");
            return;
        }
        for (LeaveRequest r : leaveRequests) System.out.println("  " + r);
    }

    public void printLeaveBalance(String employeeId) {
        Employee e = getEmployee(employeeId);
        System.out.println("\nLeave Balance for " + e.getName());
        for (LeaveType type : LeaveType.values()) {
            if (type == LeaveType.UNPAID) continue;
            System.out.printf("   %-8s : %d days%n", type, e.getBalance(type));
        }
    }

   
    private LeaveRequest findRequest(String requestId) {
        for (LeaveRequest r : leaveRequests) {
            if (r.getRequestId().equals(requestId)) return r;
        }
        throw new NoSuchElementException("Leave request not found: " + requestId);
    }
}
