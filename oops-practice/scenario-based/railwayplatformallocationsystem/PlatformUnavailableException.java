package railwayplatformallocationsystem;

class PlatformUnavailableException extends Exception {
    private final int    platformNumber;
    private final String trainId;

    public PlatformUnavailableException(int platformNumber, String trainId) {
        super("Platform [" + platformNumber + "] is unavailable for train [" + trainId + "].");
        this.platformNumber = platformNumber;
        this.trainId        = trainId;
    }

    public PlatformUnavailableException(String message) {
        super(message);
        this.platformNumber = -1;
        this.trainId        = "";
    }

    public int    getPlatformNumber() { return platformNumber; }
    public String getTrainId()        { return trainId;        }
}