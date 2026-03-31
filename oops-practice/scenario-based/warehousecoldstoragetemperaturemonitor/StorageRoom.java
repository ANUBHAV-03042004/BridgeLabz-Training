package warehousecoldstoragetemperaturemonitor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class StorageRoom {
    private final String      roomId;
    private final String      roomName;
    private final StorageType storageType;
    private final double      minSafeTemp;
    private final double      maxSafeTemp;

    public StorageRoom(String roomId, String roomName, StorageType storageType,
                       double minSafeTemp, double maxSafeTemp) {
        this.roomId      = roomId;
        this.roomName    = roomName;
        this.storageType = storageType;
        this.minSafeTemp = minSafeTemp;
        this.maxSafeTemp = maxSafeTemp;
    }

    public String      getRoomId()      { return roomId;      }
    public String      getRoomName()    { return roomName;    }
    public StorageType getStorageType() { return storageType; }
    public double      getMinSafeTemp() { return minSafeTemp; }
    public double      getMaxSafeTemp() { return maxSafeTemp; }

    @Override
    public String toString() {
        return "StorageRoom[" + roomId + ", " + roomName + ", " + storageType
                + ", safe=" + minSafeTemp + "°C to " + maxSafeTemp + "°C]";
    }
}

class WarehouseMonitor {

    private final Map<String, StorageRoom>        rooms  = new LinkedHashMap<>();
    private final Map<String, List<TemperatureLog>> logs  = new LinkedHashMap<>();

    public void registerRoom(StorageRoom room) {
        rooms.put(room.getRoomId(), room);
        logs.put(room.getRoomId(), new ArrayList<>());
        System.out.println("Room registered: " + room);
    }

    public StorageRoom getRoom(String roomId) {
        StorageRoom r = rooms.get(roomId);
        if (r == null) throw new NoSuchElementException("Room not found: " + roomId);
        return r;
    }

    public void recordReading(String roomId, LocalDate date, double temp) throws SensorFailureException {
        StorageRoom room = getRoom(roomId);
        TemperatureLog log = getOrCreateLog(roomId, date, room.getMinSafeTemp(), room.getMaxSafeTemp());
        log.addReading(temp);
    }

    public void recordReadings(String roomId, LocalDate date, double... temps) throws SensorFailureException {
        for (double t : temps) recordReading(roomId, date, t);
    }

    private TemperatureLog getOrCreateLog(String roomId, LocalDate date,
                                           double minSafe, double maxSafe) {
        List<TemperatureLog> roomLogs = logs.get(roomId);
        for (TemperatureLog log : roomLogs) {
            if (log.getDate().equals(date)) return log;
        }
        TemperatureLog newLog = new TemperatureLog(roomId, date, minSafe, maxSafe);
        roomLogs.add(newLog);
        return newLog;
    }

    public double getDailyAverage(String roomId, LocalDate date) {
        for (TemperatureLog log : logs.getOrDefault(roomId, Collections.emptyList())) {
            if (log.getDate().equals(date)) return log.getDailyAverage();
        }
        return 0.0;
    }

    public void runAlerts(LocalDate date) {
        System.out.println("\n Alerts for " + date);
        boolean any = false;
        for (Map.Entry<String, List<TemperatureLog>> entry : logs.entrySet()) {
            String roomId = entry.getKey();
            for (TemperatureLog log : entry.getValue()) {
                if (log.getDate().equals(date) && !log.isSafe()) {
                    StorageRoom room = rooms.get(roomId);
                    System.out.printf("  [%s] %-12s %-20s avg=%.2f°C safe=[%.1f to %.1f]%n",
                            log.getAlertType(), roomId, room.getRoomName(),
                            log.getDailyAverage(), log.getMinSafeTemp(), log.getMaxSafeTemp());
                    any = true;
                }
            }
        }
        if (!any) System.out.println("  All rooms within safe range.");
    }

    public void printDailyReport(LocalDate date) {
        System.out.println("\nDaily Report: " + date);
        System.out.printf("  %-6s %-20s %-12s %8s %8s %8s %10s%n",
                "ID", "Room", "Type", "Avg°C", "Min°C", "Max°C", "Status");
        for (Map.Entry<String, List<TemperatureLog>> entry : logs.entrySet()) {
            String roomId = entry.getKey();
            for (TemperatureLog log : entry.getValue()) {
                if (log.getDate().equals(date)) {
                    StorageRoom room = rooms.get(roomId);
                    System.out.printf("  %-6s %-20s %-12s %8.2f %8.2f %8.2f %10s%n",
                            roomId, room.getRoomName(), room.getStorageType(),
                            log.getDailyAverage(), log.getMinReading(),
                            log.getMaxReading(), log.getAlertType());
                }
            }
        }
    }
}
