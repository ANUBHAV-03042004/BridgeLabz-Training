package railwayplatformallocationsystem;

import java.time.LocalTime;

public class RailwayPlatformAllocationSystem {

    public static void main(String[] args) {


        RailwayStationSystem station = new RailwayStationSystem();

   
        station.addPlatform(new Platform(1, TrainType.SUPERFAST));
        station.addPlatform(new Platform(2, TrainType.EXPRESS));
        station.addPlatform(new Platform(3, TrainType.EXPRESS));
        station.addPlatform(new Platform(4, TrainType.LOCAL));
        station.addPlatform(new Platform(5, TrainType.FREIGHT));

        station.incomingTrain(new Train("T001", "Rajdhani Express",  TrainType.SUPERFAST, LocalTime.of(8,  30)));
        station.incomingTrain(new Train("T002", "Chennai Mail",      TrainType.EXPRESS,   LocalTime.of(8,  45)));
        station.incomingTrain(new Train("T003", "Mumbai Local",      TrainType.LOCAL,     LocalTime.of(9,  0)));
        station.incomingTrain(new Train("T004", "Shatabdi Express",  TrainType.SUPERFAST, LocalTime.of(7,  50)));
        station.incomingTrain(new Train("T005", "Goods Train 1",     TrainType.FREIGHT,   LocalTime.of(10, 0)));
        station.incomingTrain(new Train("T006", "Deccan Queen",      TrainType.EXPRESS,   LocalTime.of(9,  15)));
        station.incomingTrain(new Train("T007", "Duronto Express",   TrainType.SUPERFAST, LocalTime.of(8,  0)));

        station.printQueue();

        station.allocateAll();

        station.printAllPlatforms();

     
        try {
            Train extra = new Train("T008", "Extra Superfast", TrainType.SUPERFAST, LocalTime.of(11, 0));
            station.incomingTrain(extra);
            station.allocateNext();
        } catch (PlatformUnavailableException e) {
            System.out.println("[CAUGHT] " + e.getMessage());
            System.out.println("         platformNumber=" + e.getPlatformNumber()
                    + ", trainId=" + e.getTrainId());
        }

        station.releasePlatform(1);
        station.releasePlatform(2);

        Train newTrain = new Train("T009", "New Express", TrainType.EXPRESS, LocalTime.of(12, 0));
        station.incomingTrain(newTrain);
        try {
            station.allocateNext();
        } catch (PlatformUnavailableException e) {
            System.out.println("[CAUGHT] " + e.getMessage());
        }

        station.printAllPlatforms();
        station.printAllocationHistory();
    }
}