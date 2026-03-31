package onlinedoctorconsulationscheduler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Appointment {
    private static int counter = 500;

    private final String            appointmentId;
    private final Doctor            doctor;
    private final String            patientName;
    private final String            slot;
    private       AppointmentStatus status;
    private final LocalDateTime     bookedAt;

    public Appointment(Doctor doctor, String patientName, String slot) {
        this.appointmentId = "APT-" + (++counter);
        this.doctor        = doctor;
        this.patientName   = patientName;
        this.slot          = slot;
        this.status        = AppointmentStatus.CONFIRMED;
        this.bookedAt      = LocalDateTime.now();
    }

    public String            getAppointmentId() { return appointmentId; }
    public Doctor            getDoctor()        { return doctor;        }
    public String            getPatientName()   { return patientName;   }
    public String            getSlot()          { return slot;          }
    public AppointmentStatus getStatus()        { return status;        }
    public void              setStatus(AppointmentStatus s) { this.status = s; }

    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm");
        return String.format("Appointment[%s, patient=%s, doctor=%s, slot=%s, status=%s, bookedAt=%s]",
                appointmentId, patientName, doctor.getName(), slot, status, bookedAt.format(fmt));
    }
}