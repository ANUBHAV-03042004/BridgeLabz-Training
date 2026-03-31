package disasterreliefresourceallocationsystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

class ReliefCenter {

    private final String               centerId;
    private final String               centerName;
    private final String               location;
    private final HashMap<String, Integer> inventory = new LinkedHashMap<>();
    private final Queue<AreaRequest>   requestQueue  = new LinkedList<>();
    private final List<AllocationRecord> report      = new ArrayList<>();

    public ReliefCenter(String centerId, String centerName, String location) {
        this.centerId   = centerId;
        this.centerName = centerName;
        this.location   = location;
    }

    public void addStock(String item, int quantity) {
        inventory.merge(item, quantity, Integer::sum);
        System.out.println("[STOCK]  " + centerName + " → " + item + " +" + quantity
                + "  (total: " + inventory.get(item) + ")");
    }

    public int getStock(String item) {
        return inventory.getOrDefault(item, 0);
    }

  
    public void submitRequest(AreaRequest request) {
        requestQueue.offer(request);
        System.out.println("[QUEUE]  Request " + request.getRequestId()
                + " from " + request.getAreaName()
                + " [" + request.getPriority() + "] added to queue.");
    }

    public void processNextRequest() throws InsufficientResourceException {
        if (requestQueue.isEmpty()) {
            System.out.println("[INFO]   No pending requests in queue.");
            return;
        }

        AreaRequest request = requestQueue.poll();
        System.out.println("\n[PROCESS] " + request.getRequestId()
                + " — " + request.getAreaName());

        Map<String, Integer> allocated = new LinkedHashMap<>();
        String failedItem = null;
        int    failReq    = 0;
        int    failAvail  = 0;

        for (Map.Entry<String, Integer> need : request.getResourceNeeds().entrySet()) {
            String item     = need.getKey();
            int    required = need.getValue();
            int    stock    = inventory.getOrDefault(item, 0);

            if (stock < required) {
                failedItem = item;
                failReq    = required;
                failAvail  = stock;
                break;
            }
            allocated.put(item, required);
        }

        if (failedItem != null) {
            request.setStatus(AllocationStatus.FAILED);
            report.add(new AllocationRecord(request.getRequestId(),
                    request.getAreaName(), allocated,
                    AllocationStatus.FAILED,
                    "Failed at item: " + failedItem
                    + " (need " + failReq + ", have " + failAvail + ")"));
            throw new InsufficientResourceException(failedItem, failReq, failAvail);
        }

       
        allocated.forEach((item, qty) -> inventory.merge(item, -qty, Integer::sum));
        request.setStatus(AllocationStatus.FULFILLED);

        report.add(new AllocationRecord(request.getRequestId(),
                request.getAreaName(), allocated,
                AllocationStatus.FULFILLED, "All resources dispatched."));

        System.out.println("[ALLOC]  Dispatched to " + request.getAreaName()
                + " → " + allocated);
    }

    public void processAllRequests() {
        System.out.println("\nProcessing All Requests: " + centerName);
        while (!requestQueue.isEmpty()) {
            try {
                processNextRequest();
            } catch (InsufficientResourceException e) {
                System.out.println("[CAUGHT] " + e.getMessage());
            }
        }
    }

    public void printInventory() {
        System.out.println("\nInventory: " + centerName);
        if (inventory.isEmpty()) { System.out.println("  (empty)"); return; }
        inventory.forEach((item, qty) ->
            System.out.printf("  %-20s : %d units%n", item, qty));
    }

    public void printQueue() {
        System.out.println("\nPending Queue: " + centerName
                + " (" + requestQueue.size() + ")");
        if (requestQueue.isEmpty()) { System.out.println("  (empty)"); return; }
        requestQueue.forEach(r -> System.out.println("  " + r));
    }

    public void printReport() {
        System.out.println("\nAllocation Report: " + centerName);
        System.out.printf("  %-10s %-20s %-10s %-30s  %s%n",
                "RequestID", "Area", "Status", "Allocated", "Note");
        if (report.isEmpty()) { System.out.println("  (no records)"); return; }
        report.forEach(System.out::println);
        long fulfilled = report.stream().filter(r -> r.getStatus() == AllocationStatus.FULFILLED).count();
        long failed    = report.stream().filter(r -> r.getStatus() == AllocationStatus.FAILED).count();
        System.out.println("  Fulfilled: " + fulfilled + "  |  Failed: " + failed
                + "  |  Total: " + report.size());
    }

    public String getCenterId()   { return centerId;   }
    public String getCenterName() { return centerName; }
    public String getLocation()   { return location;   }

    @Override
    public String toString() {
        return "ReliefCenter[" + centerId + ", " + centerName + ", " + location + "]";
    }
}
