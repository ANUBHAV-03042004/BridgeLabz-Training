package smartparkingslotallocationsystem;

public class SmartParkingSlotAllocationSystem {

    public static void main(String[] args) {

        SmartParkingSystem parking = new SmartParkingSystem();

      
        for (int i = 1; i <= 4; i++) parking.addSlot(new ParkingSlot(i, VehicleType.CAR));
        for (int i = 5; i <= 7; i++) parking.addSlot(new ParkingSlot(i, VehicleType.BIKE));

        // create vehicles
        Car  c1 = new Car("KA01AB1234", "Alice",  "Toyota");
        Car  c2 = new Car("KA02CD5678", "Bob",    "Honda");
        Car  c3 = new Car("KA03EF9012", "Carol",  "Hyundai");
        Car  c4 = new Car("KA04GH3456", "Dave",   "Ford");
        Car  c5 = new Car("KA05IJ7890", "Eve",    "BMW");
        Bike b1 = new Bike("KA06KL1111", "Frank", true);
        Bike b2 = new Bike("KA07MN2222", "Grace", false);
        Bike b3 = new Bike("KA08OP3333", "Heidi", true);
        Bike b4 = new Bike("KA09QR4444", "Ivan",  false);

      
        parking.parkVehicle(c1);
        parking.parkVehicle(c2);
        parking.parkVehicle(c3);
        parking.parkVehicle(c4);
        parking.parkVehicle(b1);
        parking.parkVehicle(b2);
        parking.parkVehicle(b3);

        parking.parkVehicle(c5);  
        parking.parkVehicle(b4); 

        parking.printAllSlots();
        parking.printWaitingQueue();
        parking.printAvailability();

        try {
            Car extra = new Car("KA10ST5555", "Judy", "Tesla");
            SmartParkingSystem testSystem = new SmartParkingSystem();
            testSystem.allocateSlot(extra); // no slots registered
        } catch (NoParkingSlotAvailableException e) {
            System.out.println("[CAUGHT] " + e.getMessage());
            System.out.println("         vehicleType=" + e.getVehicleType());
        }

        parking.releaseSlot(1);  
        parking.releaseSlot(5);  

        parking.printAllSlots();
        parking.printWaitingQueue();
    }
}
