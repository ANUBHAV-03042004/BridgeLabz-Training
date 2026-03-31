package evchargingstationmanagementsystem;

class Vehicle {
    private final String      vehicleId;
    private final String      ownerName;
    private final VehicleType type;

    public Vehicle(String vehicleId, String ownerName, VehicleType type) {
        this.vehicleId = vehicleId;
        this.ownerName = ownerName;
        this.type      = type;
    }

    public String      getVehicleId() { return vehicleId; }
    public String      getOwnerName() { return ownerName; }
    public VehicleType getType()      { return type;      }

    @Override
    public String toString() {
        return "Vehicle[" + vehicleId + ", " + ownerName + ", " + type + "]";
    }
}