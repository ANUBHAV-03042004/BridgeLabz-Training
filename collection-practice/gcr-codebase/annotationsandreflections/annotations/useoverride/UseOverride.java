package useoverride;
class Animal{
	void makeSound() {
		System.out.println("Animal sound");
	}
}
class Dog extends Animal{
	@Override
	void makeSound() {
		System.out.println("Dog sound");
	}
}
public class UseOverride {
public static void main(String args[]) {
	Animal Dog= new Dog();
	Dog.makeSound();
}
}
