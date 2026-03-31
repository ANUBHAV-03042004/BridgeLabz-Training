package courierstaffshiftscheduler;

public class Employee {
    private final String         employeeId;
    private final String         name;
    private final Department     department;
    private       EmployeeStatus status;

    public Employee(String employeeId, String name, Department department) {
        this.employeeId = employeeId;
        this.name       = name;
        this.department = department;
        this.status     = EmployeeStatus.ACTIVE;
    }

    public String         getEmployeeId() { return employeeId; }
    public String         getName()       { return name;       }
    public Department     getDepartment() { return department; }
    public EmployeeStatus getStatus()     { return status;     }
    public void           setStatus(EmployeeStatus status) { this.status = status; }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Employee)) return false;
        return employeeId.equals(((Employee) o).employeeId);
    }

    @Override
    public int hashCode() { return employeeId.hashCode(); }

    @Override
    public String toString() {
        return "Employee[" + employeeId + ", " + name + ", dept=" + department + ", status=" + status + "]";
    }
}