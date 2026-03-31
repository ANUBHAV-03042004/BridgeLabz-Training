package employeeleavemanagementsystem;

public class LeaveRequest {

    private static int counter = 1000;

    private final String      requestId;
    private final String      employeeId;
    private final LeaveType   leaveType;
    private final String      startDate;
    private final String      endDate;
    private final int         days;
    private final String      reason;
    private       LeaveStatus status;
    private       String      remarks;

    public LeaveRequest(String employeeId, LeaveType leaveType,
                        String startDate, String endDate, int days, String reason) {
        this.requestId  = "REQ-" + (++counter);
        this.employeeId = employeeId;
        this.leaveType  = leaveType;
        this.startDate  = startDate;
        this.endDate    = endDate;
        this.days       = days;
        this.reason     = reason;
        this.status     = LeaveStatus.PENDING;
    }

    public String      getRequestId()  { return requestId;  }
    public String      getEmployeeId() { return employeeId; }
    public LeaveType   getLeaveType()  { return leaveType;  }
    public int         getDays()       { return days;       }
    public LeaveStatus getStatus()     { return status;     }

    public void setStatus(LeaveStatus status)   { this.status  = status;  }
    public void setRemarks(String remarks)       { this.remarks = remarks; }

    @Override
    public String toString() {
        return String.format(
            "LeaveRequest{id='%s', empId='%s', type=%s, %s→%s (%d days), "
            + "status=%s, reason='%s'%s}",
            requestId, employeeId, leaveType, startDate, endDate, days,
            status, reason,
            (remarks != null ? ", remarks='" + remarks + "'" : ""));
    }
}
