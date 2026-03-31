package smarttrafficfinecollectionsystem;

import java.time.LocalDate;
import java.time.YearMonth;

public class SmartTrafficFineCollectionSystem {

    public static void main(String[] args) {


        TrafficFineSystem system = new TrafficFineSystem();

        system.registerVehicle(new Vehicle("KA01AB1234", "Alice",  VehicleCategory.FOUR_WHEELER, "2020-01-15"));
        system.registerVehicle(new Vehicle("KA02CD5678", "Bob",    VehicleCategory.TWO_WHEELER,  "2019-06-10"));
        system.registerVehicle(new Vehicle("KA03EF9012", "Carol",  VehicleCategory.FOUR_WHEELER, "2021-03-22"));
        system.registerVehicle(new Vehicle("KA04GH3456", "Dave",   VehicleCategory.HEAVY_VEHICLE,"2018-11-05"));

        LocalDate july = LocalDate.of(2025, 7, 1);

 
        String ch1 = null, ch2 = null, ch3 = null;
        try {
            Violation v1 = system.recordViolation("KA01AB1234", ViolationType.SIGNAL_JUMP,    july.plusDays(0),  "MG Road");
            Violation v2 = system.recordViolation("KA01AB1234", ViolationType.OVERSPEEDING,   july.plusDays(2),  "Outer Ring Road");
            Violation v3 = system.recordViolation("KA01AB1234", ViolationType.ILLEGAL_PARKING,july.plusDays(5),  "Brigade Road");
            Violation v4 = system.recordViolation("KA01AB1234", ViolationType.WRONG_LANE,     july.plusDays(8),  "Silk Board");
            Violation v5 = system.recordViolation("KA02CD5678", ViolationType.NO_HELMET,      july.plusDays(1),  "Koramangala");
            Violation v6 = system.recordViolation("KA02CD5678", ViolationType.TRIPLE_RIDING,  july.plusDays(3),  "Indiranagar");
            Violation v7 = system.recordViolation("KA02CD5678", ViolationType.SIGNAL_JUMP,    july.plusDays(7),  "Whitefield");
            Violation v8 = system.recordViolation("KA02CD5678", ViolationType.SIGNAL_JUMP,    july.plusDays(10), "HSR Layout");
            Violation v9 = system.recordViolation("KA03EF9012", ViolationType.DRUNK_DRIVING,  july.plusDays(4),  "Electronic City");
            Violation vA = system.recordViolation("KA04GH3456", ViolationType.OVERSPEEDING,   july.plusDays(6),  "Tumkur Road");
            ch1 = v1.getChallanId();
            ch2 = v5.getChallanId();
            ch3 = v9.getChallanId();
        } catch (InvalidVehicleException e) {
            System.out.println("[CAUGHT] " + e.getMessage());
        }

     
        if (ch1 != null) system.payFine(ch1);
        if (ch2 != null) system.payFine(ch2);
        if (ch3 != null) system.payFine(ch3);
        if (ch1 != null) system.payFine(ch1);

        try {
            system.recordViolation("MH01XY9999", ViolationType.SIGNAL_JUMP, july, "Test Road");
        } catch (InvalidVehicleException e) {
            System.out.println("[CAUGHT] " + e.getMessage());
            System.out.println("         vehicle=" + e.getVehicleNumber() + ", reason=" + e.getReason());
        }

        try {
            system.recordViolation("INVALID##", ViolationType.NO_HELMET, july, "Test");
        } catch (InvalidVehicleException e) {
            System.out.println("[CAUGHT] " + e.getMessage());
        }

        System.out.println("\nTriggering InvalidVehicleException (empty)");
        try {
            system.recordViolation("", ViolationType.SIGNAL_JUMP, july, "Test");
        } catch (InvalidVehicleException e) {
            System.out.println("[CAUGHT] " + e.getMessage());
        }

        system.printVehicleReport("KA01AB1234");
        system.printVehicleReport("KA02CD5678");

        System.out.printf("%nPending Fine for KA01AB1234 : Rs.%.2f%n",
                system.getTotalPendingFine("KA01AB1234"));
        System.out.printf("Pending Fine for KA02CD5678 : Rs.%.2f%n",
                system.getTotalPendingFine("KA02CD5678"));

        system.printMonthlyReport(YearMonth.of(2025, 7));
    }
}