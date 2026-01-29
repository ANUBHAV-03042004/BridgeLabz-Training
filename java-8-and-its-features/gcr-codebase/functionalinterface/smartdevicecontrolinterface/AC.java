package smartdevicecontrolinterface;

public class AC implements SmartDevice {
	@Override
	public void on() {
		System.out.println("Device on");
		
	}

	@Override
	public void off() {
		System.out.println("Device off");
	}
}
