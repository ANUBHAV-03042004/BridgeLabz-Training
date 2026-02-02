package smartcitytransportandservicemanagementsystem;

public class Passenger {
String name;
String destination;
double currentLocation;
 Passenger(String name,String destination){
	this.name=name;
	this.destination=destination;
}
 Passenger(String name,double currentLocation){
	this.name=name;
	this.currentLocation=currentLocation;
}
}
