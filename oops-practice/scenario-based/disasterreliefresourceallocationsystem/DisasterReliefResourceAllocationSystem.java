package disasterreliefresourceallocationsystem;


public class DisasterReliefResourceAllocationSystem {

    public static void main(String[] args) {

        DisasterReliefSystem system = new DisasterReliefSystem();

      
        ReliefCenter c1 = new ReliefCenter("RC01", "Central Hub", "Chennai");
        ReliefCenter c2 = new ReliefCenter("RC02", "North Camp",  "Delhi");
        system.addCenter(c1);
        system.addCenter(c2);

        c1.addStock("FoodPack",    50);
        c1.addStock("WaterBottle", 100);
        c1.addStock("MedKit",      30);

        c2.addStock("FoodPack",    20);
        c2.addStock("WaterBottle", 40);
        c2.addStock("MedKit",      10);

        c1.printInventory();
        c2.printInventory();

    
        AreaRequest r1 = new AreaRequest("Village A", Priority.CRITICAL);
        r1.addNeed("FoodPack",    10);
        r1.addNeed("WaterBottle", 20);
        r1.addNeed("MedKit",      5);

        AreaRequest r2 = new AreaRequest("Town B", Priority.HIGH);
        r2.addNeed("FoodPack",    15);
        r2.addNeed("WaterBottle", 30);

        AreaRequest r3 = new AreaRequest("Suburb C", Priority.MEDIUM);
        r3.addNeed("FoodPack",    30);
        r3.addNeed("MedKit",      25);   // will cause insufficient stock

        AreaRequest r4 = new AreaRequest("Camp D", Priority.LOW);
        r4.addNeed("WaterBottle", 10);
        r4.addNeed("MedKit",      2);

        c1.submitRequest(r1);
        c1.submitRequest(r2);
        c1.submitRequest(r3);
        c1.submitRequest(r4);

        c1.printQueue();

        
        c1.processAllRequests();

      
        c1.printInventory();

     
        AreaRequest r5 = new AreaRequest("Zone E", Priority.CRITICAL);
        r5.addNeed("MedKit", 999);
        c2.submitRequest(r5);
        try {
            c2.processNextRequest();
        } catch (InsufficientResourceException e) {
            System.out.println("[CAUGHT] " + e.getMessage());
            System.out.println("         item=" + e.getItem()
                    + ", requested=" + e.getRequested()
                    + ", available=" + e.getAvailable());
        }

        // reports
        c1.printReport();
        c2.printReport();
    }
}