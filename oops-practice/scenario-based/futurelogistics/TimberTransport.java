package futurelogistics;

import java.util.HashMap;

public class TimberTransport extends GoodsTransport {
	
	private float timberLength;
	private float timberRadius;
	private String timberType;
	private float timberPrice;
	TimberTransport(String transportId, String transportDate, int transportRating,float timberLength,float timberRadius,String timberType,float TimberPrice){
		super(transportId, transportDate, transportRating);
        this.timberLength=timberLength;
        this.timberRadius=timberRadius;
        this.timberType=timberType;
        this.timberPrice=TimberPrice;
	}
	float gettimberLength() {return timberLength;}
	float gettimberRadius() {return timberRadius;}
	String gettimberType() {return timberType;}
	float gettimberPrice() {return timberPrice;}
	
	
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
		String vehicle = "";
		float area = (2 * 3.147f * gettimberRadius() * gettimberLength());
		if(area < 250)vehicle = "Truck";
		else if(area >= 250 && area <= 400)vehicle = "Lorry";
		else if(area > 400) vehicle = "MonsterLorry";
		return vehicle;
	}
	@Override
	public float calculateTotalCharge() {
	float Volume = 3.147f * gettimberRadius() * gettimberRadius() * gettimberLength();
	String vehicle = vehicleSelection();
	float timberTypeValue = timberType.equalsIgnoreCase("Premium")? 0.25f : 0.15f;
	float Price = Volume * gettimberPrice() * timberTypeValue;
	float tax= 0.3f * Price;
	float discount = (Price * discount_on_transportRating.get(gettransportRating()));
	float totalCharge = ((Price)+ vehiclePrice.get(vehicle) + tax ) - discount;
	return totalCharge;
	}
	
	public void showDetails() {
		System.out.println("Transport ID :"+ transportId);
		System.out.println("Transport Date : "+transportDate);
		System.out.println("Transport Rating :"+ transportRating);
		System.out.println("Timber Length : "+timberLength);
		System.out.println("Timber Radius : "+timberRadius);
		System.out.println("Timber Type : "+timberType);
		System.out.println("Timber Price : "+timberPrice);
	}
	
}
