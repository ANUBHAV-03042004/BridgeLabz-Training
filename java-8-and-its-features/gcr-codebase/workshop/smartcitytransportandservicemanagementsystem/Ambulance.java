package smartcitytransportandservicemanagementsystem;

import java.util.List;

public class Ambulance extends Vehicle implements EmergencyService,GeoUtils {
   public Ambulance(String type,String source,double location) {
        super(type,source,location);
    }
   @Override
   public String toString() {
       return type + " -> " + source + " ->"+ location;
   }
   
}

