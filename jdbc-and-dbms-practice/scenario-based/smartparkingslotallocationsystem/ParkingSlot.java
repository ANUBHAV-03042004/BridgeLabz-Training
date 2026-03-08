package smartparkingslotallocationsystem;

public class ParkingSlot {
    private final int         slotNumber;
    private final VehicleType allowedType;
    private       SlotStatus  status;
    private       Vehicle     parkedVehicle;

    public ParkingSlot(int slotNumber, VehicleType allowedType) {
        this.slotNumber   = slotNumber;
        this.allowedType  = allowedType;
        this.status       = SlotStatus.FREE;
        this.parkedVehicle = null;
    }

    public boolean isFree()           { return status == SlotStatus.FREE; }
    public int         getSlotNumber()    { return slotNumber;   }
    public VehicleType getAllowedType()   { return allowedType;  }
    public SlotStatus  getStatus()        { return status;       }
    public Vehicle     getParkedVehicle() { return parkedVehicle;}

    public void assignVehicle(Vehicle vehicle) {
        this.parkedVehicle = vehicle;
        this.status        = SlotStatus.OCCUPIED;
    }

    public void freeSlot() {
        this.parkedVehicle = null;
        this.status        = SlotStatus.FREE;
    }

    @Override
    public String toString() {
        String occupant = parkedVehicle != null ? parkedVehicle.toString() : "Empty";
        return "Slot[" + slotNumber + ", type=" + allowedType + ", status=" + status
                + ", vehicle=" + occupant + "]";
    }
}
