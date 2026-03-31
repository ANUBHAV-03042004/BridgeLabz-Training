package smartvehicledashboard;

public interface Vehicle {
   void speed();
   default void batteryPercentage() {
	   System.out.println("Battery 90%");
   }
}
