package multivehiclerentalsystem;

public class Buses implements Vehicle {

	@Override
	public void rent() {
	System.out.println("BUS rented @800");
		
	}

	@Override
	public void returnVehicle() {
	System.out.println("Bus returned");
		
	}

}
