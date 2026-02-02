package smartcitytransportandservicemanagementsystem;

import java.util.List;

public class FireService extends Vehicle implements EmergencyService {
double location ; 
	FireService(String type,String source,double location){
		super(type,source);
		this.location=location;
	}

}
