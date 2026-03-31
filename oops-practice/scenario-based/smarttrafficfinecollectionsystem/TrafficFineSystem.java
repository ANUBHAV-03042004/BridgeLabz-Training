package smarttrafficfinecollectionsystem;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public 
class TrafficFineSystem {

    private final Map<String, Vehicle>          vehicleRegistry = new LinkedHashMap<>();
    private final Map<String, List<Violation>>  violationMap    = new LinkedHashMap<>();

    private static final Map<ViolationType, Double> BASE_FINES = new EnumMap<>(ViolationType.class);
    private static final Set<ViolationType> SEVERE_TYPES = new HashSet<>();
    private static final int REPEAT_THRESHOLD = 3;

    static {
        BASE_FINES.put(ViolationType.SIGNAL_JUMP,       500.0);
        BASE_FINES.put(ViolationType.OVERSPEEDING,      1000.0);
        BASE_FINES.put(ViolationType.WRONG_LANE,        300.0);
        BASE_FINES.put(ViolationType.NO_HELMET,         500.0);
        BASE_FINES.put(ViolationType.DRUNK_DRIVING,     5000.0);
        BASE_FINES.put(ViolationType.ILLEGAL_PARKING,   200.0);
        BASE_FINES.put(ViolationType.NO_SEATBELT,       1000.0);
        BASE_FINES.put(ViolationType.TRIPLE_RIDING,     500.0);

        SEVERE_TYPES.add(ViolationType.DRUNK_DRIVING);
        SEVERE_TYPES.add(ViolationType.OVERSPEEDING);
    }

    public void registerVehicle(Vehicle vehicle) {
        vehicleRegistry.put(vehicle.getVehicleNumber(), vehicle);
        violationMap.put(vehicle.getVehicleNumber(), new ArrayList<>());
        System.out.println("Registered: " + vehicle);
    }

    public void validateVehicle(String vehicleNumber) throws InvalidVehicleException {
        if (vehicleNumber == null || vehicleNumber.trim().isEmpty()) {
            throw new InvalidVehicleException(vehicleNumber, "Vehicle number cannot be null or empty");
        }
        if (!vehicleNumber.matches("[A-Z]{2}[0-9]{2}[A-Z]{2}[0-9]{4}")) {
            throw new InvalidVehicleException(vehicleNumber, "Invalid format — expected format: XX00XX0000");
        }
        if (!vehicleRegistry.containsKey(vehicleNumber)) {
            throw new InvalidVehicleException(vehicleNumber, "Vehicle not registered in the system");
        }
        Vehicle v = vehicleRegistry.get(vehicleNumber);
        if (v.isBlacklisted()) {
            throw new InvalidVehicleException(vehicleNumber, "Vehicle is blacklisted");
        }
    }

    public Violation recordViolation(String vehicleNumber, ViolationType type,
                                      LocalDate date, String location)
            throws InvalidVehicleException {
        validateVehicle(vehicleNumber);

        double baseFine = BASE_FINES.getOrDefault(type, 500.0);
        Violation violation = new Violation(vehicleNumber, type, date, location, baseFine);

        List<Violation> history = violationMap.get(vehicleNumber);
        int priorCount = history.size();

        FineCalculator calculator = resolveCalculator(type, priorCount);
        double finalFine = calculator.calculate(violation, priorCount);
        violation.setFinalFine(finalFine);

        history.add(violation);

        if (priorCount >= 10) {
            vehicleRegistry.get(vehicleNumber).setBlacklisted(true);
            System.out.println("[BLACKLIST] " + vehicleNumber + " blacklisted after 10+ violations.");
        }

        System.out.println("[CHALLAN] " + violation.getChallanId()
                + " | " + vehicleNumber + " | " + type
                + " | Rs." + String.format("%.2f", finalFine)
                + " [" + calculator.getCalculatorType() + "]"
                + (priorCount > 0 ? " | Prior violations: " + priorCount : ""));

        return violation;
    }

    private FineCalculator resolveCalculator(ViolationType type, int priorCount) {
        if (SEVERE_TYPES.contains(type)) return new SevereViolationFineCalculator();
        if (priorCount >= REPEAT_THRESHOLD) return new RepeatOffenderFineCalculator();
        return new StandardFineCalculator();
    }

    public void payFine(String challanId) {
        for (List<Violation> list : violationMap.values()) {
            for (Violation v : list) {
                if (v.getChallanId().equals(challanId)) {
                    if (v.getPaymentStatus() == PaymentStatus.PAID) {
                        System.out.println("[WARN]  " + challanId + " already paid.");
                        return;
                    }
                    v.setPaymentStatus(PaymentStatus.PAID);
                    System.out.println("[PAID]  " + challanId
                            + " Rs." + String.format("%.2f", v.getFinalFine()) + " paid.");
                    return;
                }
            }
        }
        System.out.println("[WARN]  Challan " + challanId + " not found.");
    }

    public List<Violation> getViolationsByVehicle(String vehicleNumber) {
        return Collections.unmodifiableList(
                violationMap.getOrDefault(vehicleNumber, Collections.emptyList()));
    }

    public double getTotalPendingFine(String vehicleNumber) {
        return violationMap.getOrDefault(vehicleNumber, Collections.emptyList())
                .stream()
                .filter(v -> v.getPaymentStatus() == PaymentStatus.PENDING)
                .mapToDouble(Violation::getFinalFine)
                .sum();
    }

    public Map<String, Long> getMonthlyViolationCount(YearMonth month) {
        Map<String, Long> counts = new LinkedHashMap<>();
        for (Map.Entry<String, List<Violation>> entry : violationMap.entrySet()) {
            long count = entry.getValue().stream()
                    .filter(v -> YearMonth.from(v.getDate()).equals(month))
                    .count();
            if (count > 0) counts.put(entry.getKey(), count);
        }
        return counts;
    }

    public List<String> getRepeatOffenders() {
        List<String> offenders = new ArrayList<>();
        for (Map.Entry<String, List<Violation>> entry : violationMap.entrySet()) {
            if (entry.getValue().size() >= REPEAT_THRESHOLD) {
                offenders.add(entry.getKey());
            }
        }
        return offenders;
    }

    public void printVehicleReport(String vehicleNumber) {
        Vehicle v = vehicleRegistry.get(vehicleNumber);
        if (v == null) { System.out.println("[WARN]  Vehicle not found."); return; }

        List<Violation> list = violationMap.getOrDefault(vehicleNumber, Collections.emptyList());
        double totalFine    = list.stream().mapToDouble(Violation::getFinalFine).sum();
        double pendingFine  = getTotalPendingFine(vehicleNumber);

        System.out.println("\nVehicle Report: " + vehicleNumber);
        System.out.println("  Owner          : " + v.getOwnerName());
        System.out.println("  Category       : " + v.getCategory());
        System.out.println("  Total Violations: " + list.size());
        System.out.printf( "  Total Fine     : Rs.%.2f%n", totalFine);
        System.out.printf( "  Pending Fine   : Rs.%.2f%n", pendingFine);
        System.out.println("  Blacklisted    : " + v.isBlacklisted());
        System.out.println("  Violations:");
        list.forEach(vio -> System.out.println("    " + vio));
    }

    public void printMonthlyReport(YearMonth month) {
        System.out.println("\nMonthly Report: " + month);

        List<Violation> monthlyViolations = new ArrayList<>();
        for (List<Violation> list : violationMap.values()) {
            for (Violation v : list) {
                if (YearMonth.from(v.getDate()).equals(month)) monthlyViolations.add(v);
            }
        }

        Map<ViolationType, Long> typeCount = monthlyViolations.stream()
                .collect(Collectors.groupingBy(Violation::getType, Collectors.counting()));

        double totalCollected = monthlyViolations.stream()
                .filter(v -> v.getPaymentStatus() == PaymentStatus.PAID)
                .mapToDouble(Violation::getFinalFine).sum();

        double totalPending = monthlyViolations.stream()
                .filter(v -> v.getPaymentStatus() == PaymentStatus.PENDING)
                .mapToDouble(Violation::getFinalFine).sum();

        System.out.println("  Total Challans  : " + monthlyViolations.size());
        System.out.printf( "  Total Collected : Rs.%.2f%n", totalCollected);
        System.out.printf( "  Total Pending   : Rs.%.2f%n", totalPending);

        System.out.println("\n  Violations by Type:");
        typeCount.entrySet().stream()
                .sorted(Map.Entry.<ViolationType, Long>comparingByValue().reversed())
                .forEach(e -> System.out.printf("    %-20s : %d%n", e.getKey(), e.getValue()));

        System.out.println("\n  Violations by Vehicle:");
        getMonthlyViolationCount(month).forEach((vn, cnt) ->
            System.out.printf("    %-14s : %d violation(s)%n", vn, cnt));

        System.out.println("\n  Repeat Offenders This Month:");
        List<String> repeats = getRepeatOffenders();
        if (repeats.isEmpty()) System.out.println("    (none)");
        else repeats.forEach(r -> System.out.println("    " + r
                + " — total violations: " + violationMap.get(r).size()));
    }
}