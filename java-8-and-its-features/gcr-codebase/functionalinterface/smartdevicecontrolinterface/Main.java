package smartdevicecontrolinterface;

public class Main {
public static void main(String[] args) {
	AC ac = new AC();
	Light light = new Light();
	
	ac.on();
	ac.off();
	light.off();
	light.on();
}
}
