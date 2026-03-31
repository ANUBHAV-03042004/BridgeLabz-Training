package employeeleavemanagementsystem;

import java.util.EnumMap;
import java.util.Map;

public class Employee {

    private final String     employeeId;
    private final String     name;
    private final Department department;

   
    private final Map<LeaveType, Integer> leaveBalance = new EnumMap<>(LeaveType.class);

    public Employee(String employeeId, String name, Department department) {
        this.employeeId = employeeId;
        this.name       = name;
        this.department = department;

        // default annual allocation
        leaveBalance.put(LeaveType.CASUAL,  12);
        leaveBalance.put(LeaveType.SICK,     8);
        leaveBalance.put(LeaveType.EARNED,  15);
        leaveBalance.put(LeaveType.UNPAID,  30); // always available
    }

  
    public String     getEmployeeId() { return employeeId; }
    public String     getName()       { return name; }
    public Department getDepartment() { return department; }

    public int getBalance(LeaveType type) {
        return leaveBalance.getOrDefault(type, 0);
    }

   
    public void validateBalance(LeaveType type, int days)
            throws InsufficientLeaveBalanceException {

        int available = getBalance(type);
        if (type != LeaveType.UNPAID && days > available) {
            throw new InsufficientLeaveBalanceException(employeeId, days, available);
        }
    }

  
    public void deductBalance(LeaveType type, int days) {
        if (type == LeaveType.UNPAID) return; // unpaid never tracked
        leaveBalance.merge(type, -days, Integer::sum);
    }

    public void restoreBalance(LeaveType type, int days) {
        if (type == LeaveType.UNPAID) return;
        leaveBalance.merge(type, days, Integer::sum);
    }

    @Override
    public String toString() {
        return String.format("Employee{id='%s', name='%s', dept=%s, balances=%s}",
                employeeId, name, department, leaveBalance);
    }
}