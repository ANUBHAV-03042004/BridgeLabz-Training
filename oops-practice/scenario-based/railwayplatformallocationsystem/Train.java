package railwayplatformallocationsystem;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

class Train implements Comparable<Train> {
    private final String    trainId;
    private final String    trainName;
    private final TrainType type;
    private final LocalTime arrivalTime;
    private final int       priorityWeight;

    public Train(String trainId, String trainName, TrainType type, LocalTime arrivalTime) {
        this.trainId       = trainId;
        this.trainName     = trainName;
        this.type          = type;
        this.arrivalTime   = arrivalTime;
        this.priorityWeight = assignPriority(type);
    }

    private int assignPriority(TrainType type) {
        switch (type) {
            case SUPERFAST: return 1;
            case EXPRESS:   return 2;
            case LOCAL:     return 3;
            case FREIGHT:   return 4;
            default:        return 5;
        }
    }

    @Override
    public int compareTo(Train other) {
        int cmp = Integer.compare(this.priorityWeight, other.priorityWeight);
        if (cmp != 0) return cmp;
        return this.arrivalTime.compareTo(other.arrivalTime);
    }

    public String    getTrainId()       { return trainId;       }
    public String    getTrainName()     { return trainName;     }
    public TrainType getType()          { return type;          }
    public LocalTime getArrivalTime()   { return arrivalTime;   }
    public int       getPriorityWeight(){ return priorityWeight;}

    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("HH:mm");
        return "Train[" + trainId + ", " + trainName + ", " + type
                + ", arrival=" + arrivalTime.format(fmt) + ", priority=" + priorityWeight + "]";
    }
}