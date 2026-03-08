package smartparkingslotallocationsystem;

public class Bike extends Vehicle {
    private final boolean hasHelmetStorage;

    public Bike(String vehicleId, String ownerName, boolean hasHelmetStorage) {
        super(vehicleId, ownerName, VehicleType.BIKE);
        this.hasHelmetStorage = hasHelmetStorage;
    }

    public boolean hasHelmetStorage() { return hasHelmetStorage; }

    @Override
    public String toString() {
        return "Bike[" + getVehicleId() + ", helmetStorage=" + hasHelmetStorage
                + ", owner=" + getOwnerName() + "]";
    }
}
