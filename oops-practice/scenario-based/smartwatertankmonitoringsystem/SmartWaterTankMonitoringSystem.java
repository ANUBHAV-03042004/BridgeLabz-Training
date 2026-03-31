package smartwatertankmonitoringsystem;


public class SmartWaterTankMonitoringSystem {

    public static void main(String[] args) {
        WaterTankMonitor monitor = new WaterTankMonitor();

        try {
            monitor.addTank(new WaterTank("T001", "Floor 1",    1000.0, 850.0));
            monitor.addTank(new WaterTank("T002", "Floor 2",    800.0,  150.0));
            monitor.addTank(new WaterTank("T003", "Basement",   1200.0, 60.0));
            monitor.addTank(new WaterTank("T004", "Terrace",    500.0,  490.0));
            monitor.addTank(new WaterTank("T005", "East Wing",  900.0,  80.0));
            monitor.addTank(new WaterTank("T006", "West Wing",  700.0,  20.0));
        } catch (InvalidWaterLevelException e) {
            System.out.println("[CAUGHT] " + e.getMessage());
        }

        monitor.printAllTanks();
        monitor.runAlertCheck();
        monitor.printSortedByLevel();

     
        try {
            monitor.findTank("T001").consume(200.0);
            monitor.findTank("T004").consume(400.0);
            monitor.findTank("T002").consume(50.0);
        } catch (InvalidWaterLevelException e) {
            System.out.println("[CAUGHT] " + e.getMessage());
        }

   
        try {
            monitor.findTank("T003").consume(500.0); // only 60L available
        } catch (InvalidWaterLevelException e) {
            System.out.println("[CAUGHT] " + e.getMessage());
            System.out.println("         tankId=" + e.getTankId()
                    + ", level=" + e.getLevel()
                    + ", capacity=" + e.getCapacity());
        }

        try {
            monitor.findTank("T004").refill(200.0); 
            monitor.findTank("T001").setLevel(2000.0); 
        } catch (InvalidWaterLevelException e) {
            System.out.println("[CAUGHT] " + e.getMessage());
        }

     
        try {
            new WaterTank("T007", "Roof", 300.0, 500.0); 
        } catch (InvalidWaterLevelException e) {
            System.out.println("[CAUGHT] " + e.getMessage());
        }

        try {
            new WaterTank("T008", "Garden", 200.0, -10.0);
        } catch (InvalidWaterLevelException e) {
            System.out.println("[CAUGHT] " + e.getMessage());
        }

        try {
            monitor.findTank("T002").refill(500.0);
            monitor.findTank("T006").refill(300.0);
        } catch (InvalidWaterLevelException e) {
            System.out.println("[CAUGHT] " + e.getMessage());
        }

       
        monitor.printAllTanks();
        monitor.runAlertCheck();
        monitor.printSortedByLevel();
        monitor.printSummary();
    }
}
