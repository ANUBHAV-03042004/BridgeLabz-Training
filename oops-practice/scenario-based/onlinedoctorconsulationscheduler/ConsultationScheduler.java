package onlinedoctorconsulationscheduler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class ConsultationScheduler {

    private final Map<Specialization, List<Doctor>> doctorMap       = new EnumMap<>(Specialization.class);
    private final List<Appointment>                 history         = new ArrayList<>();

    public void registerDoctor(Doctor doctor) {
        doctorMap.computeIfAbsent(doctor.getSpecialization(), k -> new ArrayList<>()).add(doctor);
        System.out.println("Registered: " + doctor);
    }

    public List<Doctor> getDoctorsBySpecialization(Specialization spec) {
        return doctorMap.getOrDefault(spec, Collections.emptyList());
    }

    public Appointment bookAppointment(String patientName, String doctorId, String slot)
            throws SlotUnavailableException {
        Doctor doctor = findDoctor(doctorId);

        if (!doctor.isSlotAvailable(slot)) {
            throw new SlotUnavailableException(doctorId, slot);
        }

        doctor.bookSlot(slot);
        Appointment apt = new Appointment(doctor, patientName, slot);
        history.add(apt);
        System.out.println("[BOOKED] " + apt.getAppointmentId()
                + " → " + patientName + " with Dr." + doctor.getName() + " at " + slot);
        return apt;
    }

    public void cancelAppointment(String appointmentId) {
        Appointment apt = findAppointment(appointmentId);
        apt.setStatus(AppointmentStatus.CANCELLED);
        apt.getDoctor().cancelSlot(apt.getSlot());
        System.out.println("[CANCEL] " + appointmentId + " cancelled for " + apt.getPatientName());
    }

    public void completeAppointment(String appointmentId) {
        Appointment apt = findAppointment(appointmentId);
        apt.setStatus(AppointmentStatus.COMPLETED);
        System.out.println("[DONE]   " + appointmentId + " completed for " + apt.getPatientName());
    }

    public List<Appointment> getHistoryByPatient(String patientName) {
        List<Appointment> result = new ArrayList<>();
        for (Appointment a : history) {
            if (a.getPatientName().equalsIgnoreCase(patientName)) result.add(a);
        }
        return result;
    }

    public List<Appointment> getHistoryByDoctor(String doctorId) {
        List<Appointment> result = new ArrayList<>();
        for (Appointment a : history) {
            if (a.getDoctor().getDoctorId().equals(doctorId)) result.add(a);
        }
        return result;
    }

    private Doctor findDoctor(String doctorId) {
        for (List<Doctor> list : doctorMap.values()) {
            for (Doctor d : list) {
                if (d.getDoctorId().equals(doctorId)) return d;
            }
        }
        throw new NoSuchElementException("Doctor not found: " + doctorId);
    }

    private Appointment findAppointment(String appointmentId) {
        for (Appointment a : history) {
            if (a.getAppointmentId().equals(appointmentId)) return a;
        }
        throw new NoSuchElementException("Appointment not found: " + appointmentId);
    }

    public void printDoctorsBySpec(Specialization spec) {
        System.out.println("\nDoctors [" + spec + "]");
        List<Doctor> list = getDoctorsBySpecialization(spec);
        if (list.isEmpty()) { System.out.println("  (none)"); return; }
        for (Doctor d : list) {
            System.out.println("  " + d + " | Available: " + d.getAvailableSlots()
                    + " | Booked: " + d.getBookedSlots());
        }
    }

    public void printHistory() {
        System.out.println("\n Consultation History (" + history.size() + ")");
        if (history.isEmpty()) { System.out.println("  (none)"); return; }
        for (Appointment a : history) System.out.println("  " + a);
    }
}
