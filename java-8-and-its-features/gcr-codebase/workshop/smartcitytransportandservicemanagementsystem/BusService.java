package smartcitytransportandservicemanagementsystem;

import java.util.List;

public class BusService extends Vehicle implements TransportService {
String departureTime;
double fare;
	BusService(String type,List<String>route,String departureTime,double fare){
		super(type,route);
		this.departureTime=departureTime;
		this.fare=fare;
	}
	

}
