package railwayplatformallocationsystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class RailwayStationSystem {

    private final Map<Integer, Platform>  platforms    = new TreeMap<>();
    private final PriorityQueue<Train>    trainQueue   = new PriorityQueue<>();
    private final List<AllocationRecord>  allocHistory = new ArrayList<>();

    public void addPlatform(Platform platform) {
        platforms.put(platform.getPlatformNumber(), platform);
        System.out.println("Platform added: " + platform);
    }

    public void incomingTrain(Train train) {
        trainQueue.offer(train);
        System.out.println("[QUEUE]  " + train + " added to priority queue.");
    }

    public void allocateNext() throws PlatformUnavailableException {
        if (trainQueue.isEmpty()) {
            System.out.println("[INFO]   No trains in queue.");
            return;
        }

        Train train = trainQueue.poll();
        System.out.println("\n[ALLOC]  Processing: " + train);

        for (Map.Entry<Integer, Platform> entry : platforms.entrySet()) {
            Platform platform = entry.getValue();
            if (platform.isFree() && platform.supports(train.getType())) {
                platform.assign(train);
                AllocationRecord record = new AllocationRecord(
                        train.getTrainId(), train.getTrainName(),
                        platform.getPlatformNumber(), train.getArrivalTime());
                allocHistory.add(record);
                System.out.println("[OK]     " + train.getTrainName()
                        + " → Platform " + platform.getPlatformNumber());
                return;
            }
        }
        throw new PlatformUnavailableException(
                "No free platform available for train [" + train.getTrainId()
                + "] of type " + train.getType());
    }

    public void allocateAll() {
        System.out.println("\n Allocating All Trains in Priority Queue ");
        while (!trainQueue.isEmpty()) {
            try {
                allocateNext();
            } catch (PlatformUnavailableException e) {
                System.out.println("[CAUGHT] " + e.getMessage());
            }
        }
    }

    public void releasePlatform(int platformNumber) {
        Platform p = platforms.get(platformNumber);
        if (p == null) {
            System.out.println("[WARN]   Platform " + platformNumber + " not found.");
            return;
        }
        if (p.isFree()) {
            System.out.println("[WARN]   Platform " + platformNumber + " is already free.");
            return;
        }
        p.release();
    }

    public void printAllPlatforms() {
        System.out.println("\nAll Platforms");
        platforms.values().forEach(p -> System.out.println("  " + p));
    }

    public void printQueue() {
        System.out.println("\n Incoming Train Queue (" + trainQueue.size() + ")");
        if (trainQueue.isEmpty()) { System.out.println("  (empty)"); return; }
        List<Train> copy = new ArrayList<>(trainQueue);
        copy.sort(null);
        copy.forEach(t -> System.out.println("  " + t));
    }

    public void printAllocationHistory() {
        System.out.println("\nAllocation History (" + allocHistory.size() + " records)");
        if (allocHistory.isEmpty()) { System.out.println("  (none)"); return; }
        allocHistory.forEach(System.out::println);
    }
}

