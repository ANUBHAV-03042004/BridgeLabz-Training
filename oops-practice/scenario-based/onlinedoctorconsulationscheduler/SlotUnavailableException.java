package onlinedoctorconsulationscheduler;

class SlotUnavailableException extends Exception {
    private final String doctorId;
    private final String slot;

    public SlotUnavailableException(String doctorId, String slot) {
        super("Slot [" + slot + "] is unavailable for doctor [" + doctorId + "].");
        this.doctorId = doctorId;
        this.slot     = slot;
    }

    public String getDoctorId() { return doctorId; }
    public String getSlot()     { return slot;     }
}
