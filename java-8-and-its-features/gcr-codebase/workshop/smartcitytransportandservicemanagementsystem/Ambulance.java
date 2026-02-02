package smartcitytransportandservicemanagementsystem;

import java.util.List;

public class Ambulance extends Vehicle implements EmergencyService {
double location ; 
	Ambulance (String type,String source,double location){
		super(type,source);
		this.location = location;
	}

}
