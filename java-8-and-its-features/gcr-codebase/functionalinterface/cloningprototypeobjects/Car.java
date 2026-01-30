package cloningprototypeobjects;

public class Car implements Cloneable{
	private String model;
	Car(String model){
		this.model=model;
	}
	String getmodel() {
		return model;
	}
	 
	   
	public static void main(String[] args) throws CloneNotSupportedException {
		Car car = new Car("maruti");
		Car car2 =  (Car) car.clone();
		
		System.out.println(car.getmodel());
		System.out.println(car2.getmodel());
		
		System.out.println("Are they equal ?"+ (car == car2));
	}

}
