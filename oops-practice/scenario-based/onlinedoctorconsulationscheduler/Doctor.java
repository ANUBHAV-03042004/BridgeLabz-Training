package onlinedoctorconsulationscheduler;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class Doctor {
    private final String         doctorId;
    private final String         name;
    private final Specialization specialization;
    private final Set<String>    availableSlots;
    private final Set<String>    bookedSlots;

    public Doctor(String doctorId, String name, Specialization specialization) {
        this.doctorId       = doctorId;
        this.name           = name;
        this.specialization = specialization;
        this.availableSlots = new LinkedHashSet<>();
        this.bookedSlots    = new HashSet<>();
    }

    public void addSlot(String slot) {
        availableSlots.add(slot);
    }

    public boolean isSlotAvailable(String slot) {
        return availableSlots.contains(slot) && !bookedSlots.contains(slot);
    }

    public void bookSlot(String slot) {
        bookedSlots.add(slot);
    }

    public void cancelSlot(String slot) {
        bookedSlots.remove(slot);
    }

    public String         getDoctorId()       { return doctorId;       }
    public String         getName()           { return name;           }
    public Specialization getSpecialization() { return specialization; }
    public Set<String>    getAvailableSlots() { return Collections.unmodifiableSet(availableSlots); }
    public Set<String>    getBookedSlots()    { return Collections.unmodifiableSet(bookedSlots);    }

    @Override
    public String toString() {
        return "Doctor[" + doctorId + ", " + name + ", " + specialization + "]";
    }
}

