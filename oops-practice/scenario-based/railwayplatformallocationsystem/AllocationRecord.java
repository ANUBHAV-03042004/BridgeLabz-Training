package railwayplatformallocationsystem;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class AllocationRecord {
    private final String    trainId;
    private final String    trainName;
    private final int       platformNumber;
    private final LocalTime allocatedAt;

    public AllocationRecord(String trainId, String trainName, int platformNumber, LocalTime allocatedAt) {
        this.trainId        = trainId;
        this.trainName      = trainName;
        this.platformNumber = platformNumber;
        this.allocatedAt    = allocatedAt;
    }

    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("HH:mm");
        return String.format("  %-8s %-20s Platform %-4d at %s",
                trainId, trainName, platformNumber, allocatedAt.format(fmt));
    }
}

