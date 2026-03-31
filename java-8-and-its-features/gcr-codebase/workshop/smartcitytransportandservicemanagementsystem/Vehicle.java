package smartcitytransportandservicemanagementsystem;

import java.util.List;

class Vehicle {
	String type ;
	List<String> route;
	double location;
	String source;
	Vehicle (String type,List<String>route){
		this.type=type;
		this.route=route;
	}
	Vehicle(String type,String source,double location){
		this.type = type ;
		this.source=source;
		this.location=location;
	}
}
