package convertajavaobjectintojsonformat;
import org.json.JSONObject;
public class Car {
String carType;
String Model;
String VehicleNumber;
Car(String carType,String Model,String vehicleNumber){
	this.carType=carType;
	this.Model=Model;
	this.VehicleNumber=vehicleNumber;
}
public JSONObject toJson() {
    JSONObject json = new JSONObject();
    json.put("carType", carType);
    json.put("model", Model);
    json.put("vehicleNumber", VehicleNumber);
    return json;
}

public static void main(String[] args) {
	 JSONObject JsonCar = new JSONObject();
	 Car car= new Car("CNG","SUV","UP32JAIL231");
	 Car car2= new Car("Diesel","Suzuki","UP85GHIL231");
	 JsonCar.put(car2.VehicleNumber,car2.toJson());
	 JsonCar.put(car.VehicleNumber,car.toJson());
	 for(String c: JsonCar.keySet()) {
		 System.out.println(c +": "+ JsonCar.getJSONObject(c));
	 }
	 
}
}
