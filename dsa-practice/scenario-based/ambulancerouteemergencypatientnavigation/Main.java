package ambulancerouteemergencypatientnavigation;

public class Main {
    public static void main(String args[]) {
        AmbulanceRouteEmergencyPatientNavigation ambulanceroute = new AmbulanceRouteEmergencyPatientNavigation();

        ambulanceroute.addroute("Emergency", "Full");
        ambulanceroute.addroute("ICU", "EMPTY");
        ambulanceroute.addroute("Radiology", "Full");
        ambulanceroute.addroute("X-Ray", "Empty");

        System.out.println(ambulanceroute.bedAvailable());
        System.out.println(ambulanceroute.bedAvailable());
        System.out.println(ambulanceroute.bedAvailable());
    }
}
