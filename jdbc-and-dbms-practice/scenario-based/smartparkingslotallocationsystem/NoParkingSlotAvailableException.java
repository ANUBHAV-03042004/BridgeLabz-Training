package smartparkingslotallocationsystem;

 public class NoParkingSlotAvailableException extends Exception {
    private final String vehicleType;

    public NoParkingSlotAvailableException(String vehicleType) {
        super("No parking slot available for vehicle type: " + vehicleType);
        this.vehicleType = vehicleType;
    }

    public String getVehicleType() { return vehicleType; }
}

