package smartwatertankmonitoringsystem;


class InvalidWaterLevelException extends Exception {
    private final String tankId;
    private final double level;
    private final double capacity;

    public InvalidWaterLevelException(String tankId, double level, double capacity) {
        super("Invalid water level for tank [" + tankId + "] — level "
                + level + "L exceeds capacity " + capacity + "L.");
        this.tankId   = tankId;
        this.level    = level;
        this.capacity = capacity;
    }

    public String getTankId()   { return tankId;   }
    public double getLevel()    { return level;     }
    public double getCapacity() { return capacity;  }
}