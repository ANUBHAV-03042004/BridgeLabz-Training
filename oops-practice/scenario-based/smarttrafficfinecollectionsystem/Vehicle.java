package smarttrafficfinecollectionsystem;

public class Vehicle {
    private final String          vehicleNumber;
    private final String          ownerName;
    private final VehicleCategory category;
    private final String          registrationDate;
    private       boolean         isBlacklisted;

    public Vehicle(String vehicleNumber, String ownerName,
                   VehicleCategory category, String registrationDate) {
        this.vehicleNumber    = vehicleNumber;
        this.ownerName        = ownerName;
        this.category         = category;
        this.registrationDate = registrationDate;
        this.isBlacklisted    = false;
    }

    public String          getVehicleNumber()    { return vehicleNumber;    }
    public String          getOwnerName()        { return ownerName;        }
    public VehicleCategory getCategory()         { return category;         }
    public String          getRegistrationDate() { return registrationDate; }
    public boolean         isBlacklisted()       { return isBlacklisted;    }
    public void            setBlacklisted(boolean b) { this.isBlacklisted = b; }

    @Override
    public String toString() {
        return "Vehicle[" + vehicleNumber + ", owner=" + ownerName
                + ", category=" + category + ", blacklisted=" + isBlacklisted + "]";
    }
}
