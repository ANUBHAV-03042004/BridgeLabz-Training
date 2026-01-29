package multivehiclerentalsystem;

public class Car implements Vehicle {

	@Override
	public void rent() {
		System.out.println("Car rented @500");
	}

	@Override
	public void returnVehicle() {
		
	System.out.println("returned Car");
	}

}
