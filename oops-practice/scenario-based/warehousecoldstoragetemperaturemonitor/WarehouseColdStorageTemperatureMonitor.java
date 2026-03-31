package warehousecoldstoragetemperaturemonitor;

import java.time.LocalDate;

public class WarehouseColdStorageTemperatureMonitor {

    public static void main(String[] args) {

        WarehouseMonitor monitor = new WarehouseMonitor();

        monitor.registerRoom(new StorageRoom("R001", "Frozen Meat",    StorageType.FROZEN,  -25.0, -18.0));
        monitor.registerRoom(new StorageRoom("R002", "Dairy Chiller",  StorageType.CHILLED,   2.0,   8.0));
        monitor.registerRoom(new StorageRoom("R003", "Fruit Storage",  StorageType.AMBIENT,  10.0,  15.0));
        monitor.registerRoom(new StorageRoom("R004", "Dry Goods",      StorageType.DRY,      15.0,  25.0));

        LocalDate today = LocalDate.of(2025, 7, 1);

      
        try {
            monitor.recordReadings("R001", today, -22.0, -20.5, -19.8, -21.0);
            monitor.recordReadings("R002", today,  4.5,   5.0,   5.2,   4.8);
            monitor.recordReadings("R003", today, 16.0,  17.5,  18.0,  16.5);  // too high
            monitor.recordReadings("R004", today, 20.0,  21.0,  22.5,  20.5);
        } catch (SensorFailureException e) {
            System.out.println("[CAUGHT] " + e.getMessage());
        }

     
        try {
            monitor.recordReading("R001", today, 75.0);
        } catch (SensorFailureException e) {
            System.out.println("[CAUGHT] " + e.getMessage());
            System.out.println("         roomId=" + e.getRoomId() + ", reason=" + e.getReason());
        }

        try {
            monitor.recordReading("R002", today, Double.NaN);
        } catch (SensorFailureException e) {
            System.out.println("[CAUGHT] " + e.getMessage());
        }

        System.out.printf("%nDaily Average R001: %.2f°C%n", monitor.getDailyAverage("R001", today));
        System.out.printf("Daily Average R003: %.2f°C%n", monitor.getDailyAverage("R003", today));

        monitor.printDailyReport(today);
        monitor.runAlerts(today);
    }
}