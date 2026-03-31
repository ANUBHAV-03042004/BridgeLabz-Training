package smartcitytransportandservicemanagementsystem;

public interface GeoUtils {
	   static double calculateDistance(double source, double destination) {
	        return Math.abs(destination - source);
	    }
}
