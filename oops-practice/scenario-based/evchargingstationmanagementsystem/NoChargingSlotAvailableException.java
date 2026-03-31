package evchargingstationmanagementsystem;

public class NoChargingSlotAvailableException extends Exception {
    private final String vehicleId;
    private final String stationType;

    public NoChargingSlotAvailableException(String vehicleId, String stationType) {
        super("No charging slot available for vehicle [" + vehicleId
                + "] at station type [" + stationType + "].");
        this.vehicleId    = vehicleId;
        this.stationType  = stationType;
    }

    public String getVehicleId()    { return vehicleId;    }
    public String getStationType()  { return stationType;  }
}
