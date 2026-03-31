package onlinedoctorconsulationscheduler;

public class OnlineDoctorConsultationScheduler {

    public static void main(String[] args) {

        System.out.println("Online Doctor Consultation Scheduler");

        ConsultationScheduler scheduler = new ConsultationScheduler();

        Doctor d1 = new Doctor("D001", "Dr. Sharma",  Specialization.CARDIOLOGIST);
        Doctor d2 = new Doctor("D002", "Dr. Mehta",   Specialization.DERMATOLOGIST);
        Doctor d3 = new Doctor("D003", "Dr. Gupta",   Specialization.CARDIOLOGIST);
        Doctor d4 = new Doctor("D004", "Dr. Reddy",   Specialization.NEUROLOGIST);

        d1.addSlot("10:00 AM"); d1.addSlot("11:00 AM"); d1.addSlot("02:00 PM");
        d2.addSlot("09:00 AM"); d2.addSlot("01:00 PM");
        d3.addSlot("10:00 AM"); d3.addSlot("03:00 PM");
        d4.addSlot("11:00 AM"); d4.addSlot("04:00 PM");

        System.out.println("\nRegistering Doctors");
        scheduler.registerDoctor(d1);
        scheduler.registerDoctor(d2);
        scheduler.registerDoctor(d3);
        scheduler.registerDoctor(d4);

        scheduler.printDoctorsBySpec(Specialization.CARDIOLOGIST);

        System.out.println("\nBooking Appointments");
        String apt1 = null, apt2 = null;
        try {
            Appointment a1 = scheduler.bookAppointment("Alice",   "D001", "10:00 AM");
            Appointment a2 = scheduler.bookAppointment("Bob",     "D001", "11:00 AM");
            Appointment a3 = scheduler.bookAppointment("Carol",   "D002", "09:00 AM");
            Appointment a4 = scheduler.bookAppointment("Dave",    "D003", "03:00 PM");
            apt1 = a1.getAppointmentId();
            apt2 = a3.getAppointmentId();
        } catch (SlotUnavailableException e) {
            System.out.println("[CAUGHT] " + e.getMessage());
        }

        System.out.println("\nTriggering SlotUnavailableException");
        try {
            scheduler.bookAppointment("Eve", "D001", "10:00 AM");
        } catch (SlotUnavailableException e) {
            System.out.println("[CAUGHT] " + e.getMessage());
            System.out.println("         doctorId=" + e.getDoctorId() + ", slot=" + e.getSlot());
        }

        try {
            scheduler.bookAppointment("Frank", "D001", "10:00 AM");
        } catch (SlotUnavailableException e) {
            System.out.println("[CAUGHT] " + e.getMessage());
        }

        System.out.println("\nCancel and Complete");
        if (apt2 != null) scheduler.cancelAppointment(apt2);
        if (apt1 != null) scheduler.completeAppointment(apt1);

        System.out.println("\nHistory for Alice");
        scheduler.getHistoryByPatient("Alice").forEach(a -> System.out.println("  " + a));

        System.out.println("\nHistory for D001");
        scheduler.getHistoryByDoctor("D001").forEach(a -> System.out.println("  " + a));

        scheduler.printHistory();
    }
}