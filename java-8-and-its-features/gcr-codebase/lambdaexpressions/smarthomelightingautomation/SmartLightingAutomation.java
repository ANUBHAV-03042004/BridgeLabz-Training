package smarthomelightingautomation;

import java.util.HashMap;
import java.util.Map;

@ FunctionalInterface
interface LightBehaviour{
	void activate();
}
public class SmartLightingAutomation {
	public static void main(String[] args) {

	HashMap<String,LightBehaviour> Light = new HashMap<>();
	Light.put("motion",()->System.out.println("Light is White"));
	Light.put("time of day",()->System.out.println("Light is Yellow"));
	Light.put("voice commands",()->System.out.println("Light Turn ON/OFF"));

	triggerEvent("motion",Light);
	triggerEvent("time of day",Light);
	triggerEvent("voice commands",Light);
}
	public static void triggerEvent(String trigger,Map<String,LightBehaviour>Light) {
		LightBehaviour light = Light.get(trigger);
		if(light!=null)
		{
		     light.activate();	
		}else {
			System.out.println("No behaviour for :"+ trigger);
		}
	}
}
