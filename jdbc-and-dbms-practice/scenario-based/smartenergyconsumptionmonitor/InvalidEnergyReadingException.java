package smartenergyconsumptionmonitor;


class InvalidEnergyReadingException extends Exception {

    private final double  reading;
    private final String  deviceId;
    private final String  reason;

    public InvalidEnergyReadingException(String deviceId, double reading, String reason) {
        super(String.format(
            "Invalid energy reading for device [%s]: %.4f kWh — %s",
            deviceId, reading, reason));
        this.deviceId = deviceId;
        this.reading  = reading;
        this.reason   = reason;
    }

    public double getReading()  { return reading;  }
    public String getDeviceId() { return deviceId; }
    public String getReason()   { return reason;   }
}
