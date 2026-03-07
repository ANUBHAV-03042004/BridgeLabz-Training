package employeeleavemanagementsystem;

class InsufficientLeaveBalanceException extends Exception {
    private final String employeeId;
    private final int    requested;
    private final int    available;

    public InsufficientLeaveBalanceException(String employeeId, int requested, int available) {
        super(String.format(
            "Employee [%s] requested %d day(s) but only has %d day(s) available.",
            employeeId, requested, available));
        this.employeeId = employeeId;
        this.requested  = requested;
        this.available  = available;
    }

    public String getEmployeeId() { return employeeId; }
    public int    getRequested()  { return requested; }
    public int    getAvailable()  { return available; }
}