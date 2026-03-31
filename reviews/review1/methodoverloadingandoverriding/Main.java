package reviews.review1.methodoverloadingandoverriding;

public class Main extends MethodOverloadingAndOverriding{
	Main(int age) {
		super(age);
	
	}
	@Override
    public void play() {
		System.out.println("Anubhav play");
	}
	public static void main(String agrs[]) {
	String name="anubhav";
	MethodOverloadingAndOverriding moao= new MethodOverloadingAndOverriding(5);
	MethodOverloadingAndOverriding moao2= new MethodOverloadingAndOverriding(name);
	moao.play();
	MethodOverloadingAndOverriding main = new Main(0);
	main.play();
	}
}
