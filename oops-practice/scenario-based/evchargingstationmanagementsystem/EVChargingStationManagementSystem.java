package evchargingstationmanagementsystem;


public class EVChargingStationManagementSystem {

    public static void main(String[] args) {

        System.out.println("EV Charging Station Management System");

        EVChargingStationSystem station = new EVChargingStationSystem(new StandardPricing());

        System.out.println("\n Adding Charging Slots");
        station.addSlot(new ChargingSlot(1, VehicleType.CAR));
        station.addSlot(new ChargingSlot(2, VehicleType.CAR));
        station.addSlot(new ChargingSlot(3, VehicleType.BIKE));
        station.addSlot(new ChargingSlot(4, VehicleType.BIKE));
        station.addSlot(new ChargingSlot(5, VehicleType.TRUCK));

        Vehicle v1 = new Vehicle("KA01AB1234", "Alice",  VehicleType.CAR);
        Vehicle v2 = new Vehicle("KA02CD5678", "Bob",    VehicleType.CAR);
        Vehicle v3 = new Vehicle("KA03EF9012", "Carol",  VehicleType.BIKE);
        Vehicle v4 = new Vehicle("KA04GH3456", "Dave",   VehicleType.CAR);
        Vehicle v5 = new Vehicle("KA05IJ7890", "Eve",    VehicleType.BIKE);
        Vehicle v6 = new Vehicle("KA06KL1111", "Frank",  VehicleType.TRUCK);

        System.out.println("\nStandard Pricing Charging");
        try {
            station.chargeVehicle(v1, 25.0);
            station.chargeVehicle(v2, 18.5);
            station.chargeVehicle(v3, 8.0);
        } catch (NoChargingSlotAvailableException e) {
            System.out.println("[CAUGHT] " + e.getMessage());
        }

        System.out.println("\nSwitch to Peak Hour Pricing");
        station.setPricing(new PeakHourPricing());

        try {
            station.chargeVehicle(v4, 30.0);
            station.chargeVehicle(v5, 6.5);
            station.chargeVehicle(v6, 50.0);
        } catch (NoChargingSlotAvailableException e) {
            System.out.println("[CAUGHT] " + e.getMessage());
        }

        System.out.println("\nSwitch to Night Pricing");
        station.setPricing(new NightPricing());

        Vehicle v7 = new Vehicle("KA07MN2222", "Grace", VehicleType.CAR);
        Vehicle v8 = new Vehicle("KA08OP3333", "Heidi", VehicleType.CAR);
        station.queueVehicle(v7);
        station.queueVehicle(v8);

        station.printQueue();

  
        EVChargingStationSystem emptyStation = new EVChargingStationSystem(new StandardPricing());
        try {
            emptyStation.chargeVehicle(v1, 10.0);
        } catch (NoChargingSlotAvailableException e) {
            System.out.println("[CAUGHT] " + e.getMessage());
            System.out.println("         vehicleId=" + e.getVehicleId()
                    + ", stationType=" + e.getStationType());
        }

        System.out.println("\n Processing Queue with Night Pricing ");
        station.processQueue(12.0);

        station.printSlots();
        station.printSessionReport();
    }
}
