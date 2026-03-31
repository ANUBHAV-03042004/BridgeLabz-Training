package disasterreliefresourceallocationsystem;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;

class DisasterReliefSystem {
    private final Map<String, ReliefCenter> centers = new LinkedHashMap<>();

    public void addCenter(ReliefCenter center) {
        centers.put(center.getCenterId(), center);
        System.out.println("Center registered: " + center);
    }

    public ReliefCenter getCenter(String centerId) {
        ReliefCenter c = centers.get(centerId);
        if (c == null) throw new NoSuchElementException("Center not found: " + centerId);
        return c;
    }

    public void printAllCenters() {
        System.out.println("\nRegistered Centers");
        centers.values().forEach(System.out::println);
    }
}
