package smartcitytransportandservicemanagementsystem;

import java.util.List;

public class FireService extends Vehicle implements EmergencyService,GeoUtils {
    FireService(String type,String source,double location) {
        super(type,source,location);
    }
    @Override
    public String toString() {
        return type + " -> " + source + " ->"+ location;
    }
}