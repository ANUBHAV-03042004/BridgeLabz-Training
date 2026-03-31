package trafficmanager;

public class Vehicle {
    String VehicleType;
   int roundaboutTime;
   Vehicle(String VehicleType,int roundaboutTime){
	   this.VehicleType=VehicleType;
	   this.roundaboutTime=roundaboutTime;
   }
   String getVehicleType() {
	   return VehicleType;
   }
  
}
