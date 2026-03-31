package reviews.review1.methodoverloadingandoverriding;

public class MethodOverloadingAndOverriding {
	int age;
	String name;
	MethodOverloadingAndOverriding(int age){
		this.age=age;
	}
	MethodOverloadingAndOverriding(String name){
		this.name=name;
	}
	public void play() {
		System.out.println("play");
	}
}
