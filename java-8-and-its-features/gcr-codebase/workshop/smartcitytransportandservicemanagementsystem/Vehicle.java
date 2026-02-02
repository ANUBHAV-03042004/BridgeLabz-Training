package smartcitytransportandservicemanagementsystem;

import java.util.List;

class Vehicle {
	String type ;
	List<String> route;
	String source;
	Vehicle (String type,List<String>route){
		this.type=type;
		this.route=route;
	}
	Vehicle(String type,String source){
		this.type = type ;
		this.source=source;
	}
}
