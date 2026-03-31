package smartvehicledashboard;

public class ElectricVehicle implements Vehicle{

	@Override
	public void speed() {
		System.out.println("Speed 50 km/h");
	}
	public static void main(String[] args) {
		FuelVehicle fv = new FuelVehicle();
		ElectricVehicle ev= new ElectricVehicle();
		System.out.println("Fuel Vehicle");
		fv.speed();
		System.out.println("Electric Vehicle");
		ev.speed();
		ev.batteryPercentage();
	}

}
