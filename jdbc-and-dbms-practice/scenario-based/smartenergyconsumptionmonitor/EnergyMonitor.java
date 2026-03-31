package smartenergyconsumptionmonitor;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.TreeMap;

public class EnergyMonitor {

    private final String homeName;

  
    private final Map<String, Map<LocalDate, List<Double>>> usageData  = new LinkedHashMap<>();


    private final Map<String, Device> devices = new LinkedHashMap<>();

    // Validation constants
    private static final double MIN_READING     =  0.0;    
    private static final double MAX_READING     = 50.0; 
    private static final double MIN_SOLAR       = -20.0;   

    public EnergyMonitor(String homeName) {
        this.homeName = homeName;
    }

    public void registerDevice(Device device) {
        devices.put(device.getDeviceId(), device);
        usageData.put(device.getDeviceId(), new TreeMap<>()); 
        System.out.println("Registered: " + device);
    }

    public Device getDevice(String deviceId) {
        Device d = devices.get(deviceId);
        if (d == null) throw new NoSuchElementException("Device not found: " + deviceId);
        return d;
    }

 
    public void recordReading(String deviceId, LocalDate date, double kWh)
            throws InvalidEnergyReadingException {

        Device device = getDevice(deviceId);

        // Validate
        validateReading(device, kWh);

      
        Map<LocalDate, List<Double>> deviceData = usageData.get(deviceId);
        deviceData.computeIfAbsent(date, d -> new ArrayList<>()).add(kWh);
    }

    public void recordReadings(String deviceId, LocalDate date, double... readings)
            throws InvalidEnergyReadingException {
        for (double r : readings) recordReading(deviceId, date, r);
    }

 
    private void validateReading(Device device, double kWh)
            throws InvalidEnergyReadingException {

        if (!Double.isFinite(kWh)) {
            throw new InvalidEnergyReadingException(
                    device.getDeviceId(), kWh, "reading must be a finite number");
        }

        if (device.isSolar()) {
            if (kWh > 0.0) {
                throw new InvalidEnergyReadingException(device.getDeviceId(), kWh,
                        "solar panel readings should be ≤ 0 (generation is negative)");
            }
            if (kWh < MIN_SOLAR) {
                throw new InvalidEnergyReadingException(device.getDeviceId(), kWh,
                        "solar generation exceeds physical maximum of " + Math.abs(MIN_SOLAR) + " kWh");
            }
        } else {
            if (kWh < MIN_READING) {
                throw new InvalidEnergyReadingException(device.getDeviceId(), kWh,
                        "consumption cannot be negative for a non-solar device");
            }
            if (kWh > MAX_READING) {
                throw new InvalidEnergyReadingException(device.getDeviceId(), kWh,
                        "reading " + kWh + " kWh exceeds spike-guard limit of " + MAX_READING + " kWh");
            }
        }
    }

    public double getDailyTotal(String deviceId, LocalDate date) {
        List<Double> readings = usageData
                .getOrDefault(deviceId, Collections.emptyMap())
                .getOrDefault(date, Collections.emptyList());
        return readings.stream().mapToDouble(Double::doubleValue).sum();
    }

   
    public double getDailyAverage(String deviceId, LocalDate date) {
        List<Double> readings = usageData
                .getOrDefault(deviceId, Collections.emptyMap())
                .getOrDefault(date, Collections.emptyList());
        return readings.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
    }

    public double getHomeDailyTotal(LocalDate date) {
        double total = 0.0;
        for (String deviceId : usageData.keySet()) {
            total += getDailyTotal(deviceId, date);
        }
        return total;
    }

    
    public double getMonthlyTotal(String deviceId, YearMonth month) {
        Map<LocalDate, List<Double>> deviceData =
                usageData.getOrDefault(deviceId, Collections.emptyMap());
        double total = 0.0;
        for (Map.Entry<LocalDate, List<Double>> entry : deviceData.entrySet()) {
            if (YearMonth.from(entry.getKey()).equals(month)) {
                total += entry.getValue().stream().mapToDouble(Double::doubleValue).sum();
            }
        }
        return total;
    }

    public double getMonthlyDailyAverage(String deviceId, YearMonth month) {
        Map<LocalDate, List<Double>> deviceData =
                usageData.getOrDefault(deviceId, Collections.emptyMap());

        List<Double> dailyTotals = new ArrayList<>();
        for (Map.Entry<LocalDate, List<Double>> entry : deviceData.entrySet()) {
            if (YearMonth.from(entry.getKey()).equals(month)) {
                double dayTotal = entry.getValue().stream()
                        .mapToDouble(Double::doubleValue).sum();
                dailyTotals.add(dayTotal);
            }
        }
        return dailyTotals.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
    }

    public double getHomeMonthlyTotal(YearMonth month) {
        return devices.keySet().stream()
                .mapToDouble(id -> getMonthlyTotal(id, month))
                .sum();
    }

  
    public Optional<LocalDate> getPeakDay(String deviceId, YearMonth month) {
        Map<LocalDate, List<Double>> deviceData =
                usageData.getOrDefault(deviceId, Collections.emptyMap());

        return deviceData.entrySet().stream()
                .filter(e -> YearMonth.from(e.getKey()).equals(month))
                .max(Comparator.comparingDouble(
                        e -> e.getValue().stream().mapToDouble(Double::doubleValue).sum()))
                .map(Map.Entry::getKey);
    }

    public void runDailyAlerts(LocalDate date) {
        System.out.println("\nDaily Alerts: " + date);
        boolean anyAlert = false;
        for (Map.Entry<String, Device> entry : devices.entrySet()) {
            String deviceId = entry.getKey();
            Device device   = entry.getValue();
            if (device.isSolar()) continue; // solar never triggers threshold alerts

            double daily = getDailyTotal(deviceId, date);
            if (daily > device.getMaxDailyKwh()) {
                AlertLevel level = daily > device.getMaxDailyKwh() * 1.5
                        ? AlertLevel.CRITICAL : AlertLevel.WARNING;
                System.out.printf("   [%s] %-20s consumed %.3f kWh (threshold: %.2f kWh)%n",
                        level, device.getName(), daily, device.getMaxDailyKwh());
                anyAlert = true;
            }
        }
        if (!anyAlert) System.out.println("   All devices within threshold.");
    }

   
    public void printDailyReport(LocalDate date) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("MMMM d, yyyy");
        System.out.println("\nDaily Report: " + date.format(fmt));
        System.out.printf("   %-22s %12s %12s %8s%n",
                "Device", "Total (kWh)", "Avg (kWh)", "Readings");

        for (Map.Entry<String, Device> entry : devices.entrySet()) {
            String deviceId = entry.getKey();
            String name     = entry.getValue().getName();
            List<Double> readings = usageData
                    .getOrDefault(deviceId, Collections.emptyMap())
                    .getOrDefault(date, Collections.emptyList());

            double total = readings.stream().mapToDouble(Double::doubleValue).sum();
            double avg   = readings.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
            int    count = readings.size();

            if (count > 0) {
                System.out.printf("   %-22s %12.3f %12.3f %8d%n", name, total, avg, count);
            }
        }

        System.out.printf("   %-22s %12.3f%n", "NET HOME TOTAL", getHomeDailyTotal(date));
    }

    public void printMonthlyReport(YearMonth month) {
        System.out.println("\nMonthly Report: " + month.format(DateTimeFormatter.ofPattern("MMMM yyyy")));
        System.out.printf("   %-22s %15s %15s%n",
                "Device", "Monthly Total", "Daily Average");
   

        for (Map.Entry<String, Device> entry : devices.entrySet()) {
            String deviceId = entry.getKey();
            String name     = entry.getValue().getName();
            double monthly  = getMonthlyTotal(deviceId, month);
            double dailyAvg = getMonthlyDailyAverage(deviceId, month);

            if (monthly != 0.0) {
                System.out.printf("   %-22s %12.3f kWh %12.3f kWh%n",
                        name, monthly, dailyAvg);
            }
        }

        System.out.printf("   %-22s %12.3f kWh%n",
                "TOTAL HOME USAGE", getHomeMonthlyTotal(month));


        for (String deviceId : devices.keySet()) {
            getPeakDay(deviceId, month).ifPresent(day ->
                System.out.printf("   %-22s peak on %s (%.3f kWh)%n",
                        devices.get(deviceId).getName(), day,
                        getDailyTotal(deviceId, day)));
        }
    }

    public void printAllData() {
        System.out.println("\nAll Stored Readings: " + homeName);
        for (Map.Entry<String, Map<LocalDate, List<Double>>> deviceEntry : usageData.entrySet()) {
            String deviceId = deviceEntry.getKey();
            String name     = devices.get(deviceId).getName();
            System.out.println("   Device: " + name);
            for (Map.Entry<LocalDate, List<Double>> dateEntry : deviceEntry.getValue().entrySet()) {
                System.out.printf("     %s → %s (total: %.3f kWh)%n",
                        dateEntry.getKey(),
                        dateEntry.getValue(),
                        dateEntry.getValue().stream().mapToDouble(Double::doubleValue).sum());
            }
        }
    }
}
