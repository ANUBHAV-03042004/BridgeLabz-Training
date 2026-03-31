package futurelogistics;

public abstract class GoodsTransport {
protected String transportId;
protected String transportDate;
protected int transportRating;
GoodsTransport(String transportId,String transportDate,int transportRating){
	this.transportId=transportId;
	this.transportDate=transportDate;
	this.transportRating=transportRating;
}
String gettransportId() { return transportId; }
String gettransportDate() { return transportDate;}
int gettransportRating() { return transportRating;}

abstract public String vehicleSelection();
abstract public float calculateTotalCharge();
}
