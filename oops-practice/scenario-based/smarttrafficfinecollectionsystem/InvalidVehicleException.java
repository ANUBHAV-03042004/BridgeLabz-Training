package smarttrafficfinecollectionsystem;

public class InvalidVehicleException extends Exception {
    private final String vehicleNumber;
    private final String reason;

    public InvalidVehicleException(String vehicleNumber, String reason) {
        super("Invalid vehicle [" + vehicleNumber + "] — " + reason);
        this.vehicleNumber = vehicleNumber;
        this.reason        = reason;
    }

    public String getVehicleNumber() { return vehicleNumber; }
    public String getReason()        { return reason;        }
}
