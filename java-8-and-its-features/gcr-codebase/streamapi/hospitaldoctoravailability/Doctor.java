package hospitaldoctoravailability;
public class Doctor {
    private String specialty;
    private String name;
    private String availability;
    private String location;

    public Doctor(String specialty, String name, String availability, String location) {
        this.specialty = specialty;
        this.name = name;
        this.availability = availability;
        this.location = location;
    }

    public String getSpecialty() { return specialty; }
    public String getName() { return name; }
    public String getAvailability() { return availability; }
    public String getLocation() { return location; }

    @Override
    public String toString() {
        return String.format("%s (%s) - %s, %s", name, specialty, availability, location);
    }
}
