package smartwatertankmonitoringsystem;


class WaterTank {

    private static final double ALERT_THRESHOLD  = 20.0;
    private static final double CRITICAL_THRESHOLD = 10.0;

    private final String     tankId;
    private final String     location;
    private final double     capacity;
    private       double     currentLevel;
    private       TankStatus status;

    public WaterTank(String tankId, String location, double capacity, double currentLevel)
            throws InvalidWaterLevelException {
        this.tankId   = tankId;
        this.location = location;
        this.capacity = capacity;
        this.status   = TankStatus.ACTIVE;
        setLevel(currentLevel);
    }

    public void setLevel(double level) throws InvalidWaterLevelException {
        if (level < 0 || level > capacity) {
            throw new InvalidWaterLevelException(tankId, level, capacity);
        }
        this.currentLevel = level;
    }

    public void refill(double amount) throws InvalidWaterLevelException {
        double newLevel = currentLevel + amount;
        if (newLevel > capacity) {
            throw new InvalidWaterLevelException(tankId, newLevel, capacity);
        }
        this.currentLevel = newLevel;
        System.out.println("[REFILL] " + tankId + " refilled +" + amount
                + "L → current: " + currentLevel + "L");
    }

    public void consume(double amount) throws InvalidWaterLevelException {
        double newLevel = currentLevel - amount;
        if (newLevel < 0) {
            throw new InvalidWaterLevelException(tankId, newLevel, capacity);
        }
        this.currentLevel = newLevel;
        System.out.println("[USE]    " + tankId + " consumed " + amount
                + "L → current: " + currentLevel + "L");
    }

   
    public double getUsagePercentage() {
        return (currentLevel / capacity) * 100.0;
    }

    public double getEmptySpace() {
        return capacity - currentLevel;
    }

    public AlertLevel getAlertLevel() {
        double pct = getUsagePercentage();
        if (pct <= CRITICAL_THRESHOLD) return AlertLevel.CRITICAL;
        if (pct <= ALERT_THRESHOLD)    return AlertLevel.WARNING;
        return AlertLevel.NORMAL;
    }

    public boolean isBelowThreshold() {
        return getUsagePercentage() <= ALERT_THRESHOLD;
    }

 
    public String     getTankId()      { return tankId;       }
    public String     getLocation()    { return location;     }
    public double     getCapacity()    { return capacity;     }
    public double     getCurrentLevel(){ return currentLevel; }
    public TankStatus getStatus()      { return status;       }
    public void       setStatus(TankStatus s) { this.status = s; }

    @Override
    public String toString() {
        return String.format("WaterTank[%-6s %-15s level=%-7.1fL cap=%-7.1fL usage=%-6.1f%% alert=%s]",
                tankId, location, currentLevel, capacity, getUsagePercentage(), getAlertLevel());
    }
}