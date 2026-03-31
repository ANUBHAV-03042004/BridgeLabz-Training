package multivehiclerentalsystem;

public class Main {
public static void main(String[] args) {
	Car car = new Car();
	Bikes bike = new Bikes();
	Buses bus = new Buses();
	
	car.rent();
	car.returnVehicle();
	bus.rent();
	bus.returnVehicle();
	bike.rent();
	bike.returnVehicle();
}
}
