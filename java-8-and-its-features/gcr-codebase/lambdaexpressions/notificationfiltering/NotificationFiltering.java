package notificationfiltering;
import java.util.*;
import java.util.function.Predicate;
class Alert {
    String type;     
    String patientId;
    String message;

    public Alert(String type, String patientId, String message) {
        this.type = type;
        this.patientId = patientId;
        this.message = message;
    }

    @Override
    public String toString() {
        return  type + ": Patient " + patientId + " -> " + message;
    }
}

public class NotificationFiltering {
	    public static void main(String[] args) {
	        List<Alert> alerts = Arrays.asList(
	            new Alert("Critical", "P001", "High blood pressure detected"),
	            new Alert("Normal", "P002", "Routine checkup scheduled"),
	            new Alert("Reminder", "P001", "Medication due at 8 PM"),
	            new Alert("Critical", "P003", "Low oxygen level detected")
	        );

	    
	        Predicate<Alert> criticalOnly = alert -> alert.type.equals("Critical");
	        Predicate<Alert> patientP001Only = alert -> alert.patientId.equals("P001");
	        Predicate<Alert> remindersOnly = alert -> alert.type.equals("Reminder");

	      
	        System.out.println("Critical Alerts: ");
	        filterAlerts(alerts, criticalOnly);

	        System.out.println(" Alerts for Patient P001 :");
	        filterAlerts(alerts, patientP001Only);

	        System.out.println("\nReminder Alerts:");
	        filterAlerts(alerts, remindersOnly);
	    }

	
	    public static void filterAlerts(List<Alert> alerts, Predicate<Alert> condition) {
	        alerts.stream()
	              .filter(condition)
	              .forEach(System.out::println);
	    }
	}