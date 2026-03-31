package courierstaffshiftscheduler;

public class ShiftAlreadyAssignedException extends Exception {
    private final String employeeId;
    private final String shiftTime;

    public ShiftAlreadyAssignedException(String employeeId, String shiftTime) {
        super("Employee [" + employeeId + "] is already assigned to shift: " + shiftTime);
        this.employeeId = employeeId;
        this.shiftTime  = shiftTime;
    }

    public String getEmployeeId() { return employeeId; }
    public String getShiftTime()  { return shiftTime;  }
}
