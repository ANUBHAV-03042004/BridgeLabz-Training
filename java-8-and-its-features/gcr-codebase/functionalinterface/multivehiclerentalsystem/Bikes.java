package multivehiclerentalsystem;

public class Bikes implements Vehicle{

	@Override
	public void rent() {
		System.out.println("Bike rented @200");
	}

	@Override
	public void returnVehicle() {
		
		System.out.println("returned Bike");
	}

}
