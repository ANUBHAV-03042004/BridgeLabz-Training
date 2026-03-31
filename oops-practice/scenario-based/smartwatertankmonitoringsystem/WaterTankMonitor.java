package smartwatertankmonitoringsystem;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

class WaterTankMonitor {

    private final List<WaterTank> tanks = new ArrayList<>();

  
    public void addTank(WaterTank tank) {
        tanks.add(tank);
        System.out.println("Tank added: " + tank);
    }

    public WaterTank findTank(String tankId) {
        return tanks.stream()
                .filter(t -> t.getTankId().equals(tankId))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Tank not found: " + tankId));
    }

    public List<WaterTank> sortByLowestLevel() {
        List<WaterTank> sorted = new ArrayList<>(tanks);
        sorted.sort(Comparator.comparingDouble(WaterTank::getCurrentLevel));
        return sorted;
    }

    public List<WaterTank> sortByLowestPercentage() {
        List<WaterTank> sorted = new ArrayList<>(tanks);
        sorted.sort(Comparator.comparingDouble(WaterTank::getUsagePercentage));
        return sorted;
    }

    
    public void runAlertCheck() {
        System.out.println("\nAlert Check");
        boolean anyAlert = false;
        for (WaterTank tank : tanks) {
            if (tank.getStatus() != TankStatus.ACTIVE) continue;
            AlertLevel level = tank.getAlertLevel();
            if (level != AlertLevel.NORMAL) {
                System.out.printf("  [%-8s] %-6s %-15s %.1f%% — %s%n",
                        level, tank.getTankId(), tank.getLocation(),
                        tank.getUsagePercentage(), getAlertMessage(level));
                anyAlert = true;
            }
        }
        if (!anyAlert) System.out.println("All tanks are at safe levels.");
    }

    private String getAlertMessage(AlertLevel level) {
        if (level == AlertLevel.CRITICAL) return "CRITICAL — Immediate refill required!";
        return "WARNING — Level below 20%, schedule refill.";
    }

    public void printAllTanks() {
        System.out.println("\nAll Tanks (" + tanks.size() + ")");
        System.out.printf("  %-6s %-15s %10s %10s %8s %8s %10s%n",
                "ID", "Location", "Level(L)", "Cap(L)", "Usage%", "Space(L)", "Alert");

        for (WaterTank t : tanks) {
            System.out.printf("  %-6s %-15s %10.1f %10.1f %7.1f%% %8.1f %10s%n",
                    t.getTankId(), t.getLocation(), t.getCurrentLevel(),
                    t.getCapacity(), t.getUsagePercentage(),
                    t.getEmptySpace(), t.getAlertLevel());
        }
    }

    public void printSortedByLevel() {
        System.out.println("\nTanks Sorted by Lowest Water Level");
        System.out.printf("  %-4s %-6s %-15s %10s %8s%n",
                "Rank", "ID", "Location", "Level(L)", "Usage%");
        List<WaterTank> sorted = sortByLowestLevel();
        for (int i = 0; i < sorted.size(); i++) {
            WaterTank t = sorted.get(i);
            System.out.printf("  #%-3d %-6s %-15s %10.1f %7.1f%%%n",
                    (i + 1), t.getTankId(), t.getLocation(),
                    t.getCurrentLevel(), t.getUsagePercentage());
        }
    }

    public void printSummary() {
        double totalCap   = tanks.stream().mapToDouble(WaterTank::getCapacity).sum();
        double totalLevel = tanks.stream().mapToDouble(WaterTank::getCurrentLevel).sum();
        long   warnings   = tanks.stream().filter(t -> t.getAlertLevel() == AlertLevel.WARNING).count();
        long   critical   = tanks.stream().filter(t -> t.getAlertLevel() == AlertLevel.CRITICAL).count();

        System.out.println("\nSystem Summary");
        System.out.printf("  Total Capacity  : %.1f L%n", totalCap);
        System.out.printf("  Total Level     : %.1f L%n", totalLevel);
        System.out.printf("  Overall Usage   : %.1f%%%n", (totalLevel / totalCap) * 100);
        System.out.printf("  Warning Tanks   : %d%n", warnings);
        System.out.printf("  Critical Tanks  : %d%n", critical);
    }
}