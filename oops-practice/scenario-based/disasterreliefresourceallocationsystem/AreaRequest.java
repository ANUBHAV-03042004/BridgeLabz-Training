package disasterreliefresourceallocationsystem;

import java.util.LinkedHashMap;
import java.util.Map;

public class AreaRequest {
    private static int counter = 1000;

    private final String               requestId;
    private final String               areaName;
    private final Map<String, Integer> resourceNeeds;
    private final Priority             priority;
    private       AllocationStatus     status;

    public AreaRequest(String areaName, Priority priority) {
        this.requestId     = "REQ-" + (++counter);
        this.areaName      = areaName;
        this.priority      = priority;
        this.resourceNeeds = new LinkedHashMap<>();
        this.status        = AllocationStatus.PENDING;
    }

    public void addNeed(String item, int quantity) {
        resourceNeeds.put(item, quantity);
    }

    public String               getRequestId()    { return requestId;    }
    public String               getAreaName()     { return areaName;     }
    public Map<String, Integer> getResourceNeeds(){ return resourceNeeds;}
    public Priority             getPriority()     { return priority;     }
    public AllocationStatus     getStatus()       { return status;       }
    public void                 setStatus(AllocationStatus s) { this.status = s; }

    @Override
    public String toString() {
        return "AreaRequest[" + requestId + ", area=" + areaName
                + ", priority=" + priority + ", needs=" + resourceNeeds
                + ", status=" + status + "]";
    }
}
