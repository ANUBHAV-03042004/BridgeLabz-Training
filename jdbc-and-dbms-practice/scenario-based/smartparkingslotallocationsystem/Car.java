package smartparkingslotallocationsystem;

public class Car extends Vehicle {
    private final String brand;

    public Car(String vehicleId, String ownerName, String brand) {
        super(vehicleId, ownerName, VehicleType.CAR);
        this.brand = brand;
    }

    public String getBrand() { return brand; }

    @Override
    public String toString() {
        return "Car[" + getVehicleId() + ", brand=" + brand + ", owner=" + getOwnerName() + "]";
    }
}
