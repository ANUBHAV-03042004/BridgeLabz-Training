package railwayplatformallocationsystem;

class Platform {
    private final int         platformNumber;
    private final TrainType   supportedType;
    private       PlatformStatus status;
    private       Train         currentTrain;

    public Platform(int platformNumber, TrainType supportedType) {
        this.platformNumber = platformNumber;
        this.supportedType  = supportedType;
        this.status         = PlatformStatus.FREE;
        this.currentTrain   = null;
    }

    public boolean isFree()                  { return status == PlatformStatus.FREE; }
    public boolean supports(TrainType type)  { return this.supportedType == type;    }
    public int     getPlatformNumber()       { return platformNumber;                }
    public TrainType getSupportedType()      { return supportedType;                 }
    public PlatformStatus getStatus()        { return status;                        }
    public Train   getCurrentTrain()         { return currentTrain;                  }

    public void assign(Train train) {
        this.currentTrain = train;
        this.status       = PlatformStatus.OCCUPIED;
    }

    public void release() {
        System.out.println("[FREE]   Platform " + platformNumber
                + " released from " + (currentTrain != null ? currentTrain.getTrainId() : "N/A"));
        this.currentTrain = null;
        this.status       = PlatformStatus.FREE;
    }

    @Override
    public String toString() {
        String occ = currentTrain != null ? currentTrain.getTrainId() : "Empty";
        return "Platform[" + platformNumber + ", type=" + supportedType
                + ", status=" + status + ", train=" + occ + "]";
    }
}
