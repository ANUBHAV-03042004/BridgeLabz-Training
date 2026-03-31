package disasterreliefresourceallocationsystem;

import java.util.Map;

public class AllocationRecord {
    private final String               requestId;
    private final String               areaName;
    private final Map<String, Integer> allocated;
    private final AllocationStatus     status;
    private final String               note;

    public AllocationRecord(String requestId, String areaName,
                            Map<String, Integer> allocated,
                            AllocationStatus status, String note) {
        this.requestId = requestId;
        this.areaName  = areaName;
        this.allocated = allocated;
        this.status    = status;
        this.note      = note;
    }

    public String               getRequestId() { return requestId; }
    public String               getAreaName()  { return areaName;  }
    public Map<String, Integer> getAllocated()  { return allocated; }
    public AllocationStatus     getStatus()    { return status;    }
    public String               getNote()      { return note;      }

    @Override
    public String toString() {
        return String.format("  [%-8s] %-20s %-10s allocated=%s  | %s",
                requestId, areaName, status, allocated, note);
    }
}
