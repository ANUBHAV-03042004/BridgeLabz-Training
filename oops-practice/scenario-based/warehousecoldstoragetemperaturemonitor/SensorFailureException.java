package warehousecoldstoragetemperaturemonitor;

class SensorFailureException extends Exception {
    private final String roomId;
    private final String reason;

    public SensorFailureException(String roomId, String reason) {
        super("Sensor failure in room [" + roomId + "] — " + reason);
        this.roomId = roomId;
        this.reason = reason;
    }

    public String getRoomId() { return roomId; }
    public String getReason() { return reason; }
}
