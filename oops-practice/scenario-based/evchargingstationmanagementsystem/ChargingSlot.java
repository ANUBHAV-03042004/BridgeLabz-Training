package evchargingstationmanagementsystem;

class ChargingSlot {
    private final int         slotId;
    private final VehicleType allowedType;
    private       SlotStatus  status;
    private       Vehicle     currentVehicle;

    public ChargingSlot(int slotId, VehicleType allowedType) {
        this.slotId        = slotId;
        this.allowedType   = allowedType;
        this.status        = SlotStatus.FREE;
        this.currentVehicle = null;
    }

    public boolean    isFree()            { return status == SlotStatus.FREE; }
    public int        getSlotId()         { return slotId;         }
    public VehicleType getAllowedType()   { return allowedType;    }
    public SlotStatus getStatus()         { return status;         }
    public Vehicle    getCurrentVehicle() { return currentVehicle; }

    public void assign(Vehicle v) {
        this.currentVehicle = v;
        this.status= SlotStatus.OCCUPIED;
    }

    public void release() {
        this.currentVehicle = null;
        this.status         = SlotStatus.FREE;
    }

    @Override
    public String toString() {
        String occ = currentVehicle != null ? currentVehicle.getVehicleId() : "Empty";
        return "Slot[" + slotId + ", " + allowedType + ", " + status + ", vehicle=" + occ + "]";
    }
}
