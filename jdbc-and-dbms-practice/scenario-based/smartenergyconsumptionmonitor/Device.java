package smartenergyconsumptionmonitor;

class Device {

    private final String     deviceId;
    private final String     name;
    private final DeviceType type;
    private final double     maxDailyKwh;   
    private final boolean    isSolar;       

    public Device(String deviceId, String name, DeviceType type,
                  double maxDailyKwh, boolean isSolar) {
        this.deviceId    = deviceId;
        this.name        = name;
        this.type        = type;
        this.maxDailyKwh = maxDailyKwh;
        this.isSolar     = isSolar;
    }

    public String     getDeviceId()    { return deviceId;    }
    public String     getName()        { return name;        }
    public DeviceType getType()        { return type;        }
    public double     getMaxDailyKwh() { return maxDailyKwh; }
    public boolean    isSolar()        { return isSolar;     }

    @Override
    public String toString() {
        return String.format("Device{id='%s', name='%s', type=%s, threshold=%.2f kWh}",
                deviceId, name, type, maxDailyKwh);
    }
}