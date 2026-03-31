package futurelogistics;

import java.util.HashMap;

public class BrickTransport extends GoodsTransport {
	private float brickSize;
	private int brickQuantity;
	private float brickPrice;
	public BrickTransport(String transportId,String transportDate,int transportRating,float brickSize, int brickQuantity,float brickPrice){
		super(transportId,transportDate,transportRating);
		this.brickSize=brickSize;
		this.brickQuantity=brickQuantity;
		this.brickPrice=brickPrice;

	}
	float getbrickSize() {return brickSize;}
	int getbrickQuantity() {return brickQuantity;}
	float getbrickPrice() {return brickPrice;}
	HashMap<String,Integer> vehiclePrice= new HashMap<>();{
	vehiclePrice.put("Truck",1000);
	vehiclePrice.put("Lorry",1700);
	vehiclePrice.put("MonsterLorry", 3000);
	}
	HashMap<Integer,Float>discount_on_transportRating = new HashMap<>();{
		discount_on_transportRating.put(5,0.20f);
		discount_on_transportRating.put(4,0.10f);
		discount_on_transportRating.put(3,0.10f);
		discount_on_transportRating.put(2,0.0f);
		discount_on_transportRating.put(1,0.0f);
	}

	@Override
	public String vehicleSelection() {
		String vehicle="";
		if(getbrickQuantity()<300) vehicle="Truck";
		else if(getbrickQuantity()>=300 && getbrickQuantity()<=500) vehicle="Lorry";
		else if(getbrickQuantity()>500) vehicle="MonsterLorry";
		return vehicle;
	}
	@Override
	public float calculateTotalCharge() {
		float totalbrickcost= (getbrickQuantity() * getbrickPrice()) ;
		String vehicle = vehicleSelection();
		float tax= 0.3f * totalbrickcost;
		float discount = (totalbrickcost * discount_on_transportRating.get(gettransportRating()));
		float totalCharge = ((totalbrickcost)+ vehiclePrice.get(vehicle) + tax ) - discount;
		return totalCharge;
	}
	public void showDetails() {
		System.out.println("Transport ID :"+ transportId);
		System.out.println("Transport Date : "+transportDate);
		System.out.println("Transport Rating :"+ transportRating);
		System.out.println("Brick Size : "+brickSize);
		System.out.println("Brick Quantity : "+brickQuantity);
		System.out.println("Brick Price : "+brickPrice);
	}
}
