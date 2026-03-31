package smartdevicecontrolinterface;

public class Light implements SmartDevice{

	@Override
	public void on() {
		System.out.println("Device on");
		
	}

	@Override
	public void off() {
		System.out.println("Device off");
	}

}
