package futurelogistics;

public class Utility {
	public GoodsTransport parseDetails(String input) {
		String transportDetails[]=input.split(":");
		String TransportId = transportDetails[0];

		String TransportDate = transportDetails[1];
		int TransportRating = Integer.parseInt(transportDetails[2]);
		String TransportType = transportDetails[3];

		if(TransportType.equalsIgnoreCase("BrickTransport")) {
			float brickSize = Float.parseFloat(transportDetails[4]);
		    int brickQuantity = Integer.parseInt(transportDetails[5]);
		    float brickPrice = Float.parseFloat(transportDetails[6]);
			return new BrickTransport(TransportId, TransportDate, TransportRating, brickSize, brickQuantity, brickPrice);
		}
		else if(TransportType.equalsIgnoreCase("TimberTransport")) {
			float timberLength = Float.parseFloat(transportDetails[4]);
			float timberRadius = Float.parseFloat(transportDetails[5]);
			String timberType = transportDetails[6];
			float timberPrice = Float.parseFloat(transportDetails[7]);
			return new TimberTransport(TransportId, TransportDate, TransportRating, timberLength, timberRadius, timberType, timberPrice);
		}
		return null;
	}

	public boolean validateTransportId (String transportId) {
		String regex = "^RTS[0-9]{3}[A-Z]$";
		if(!transportId.matches(regex)) {
			System.out.println("Transport Id : "+ transportId+" is invalid.");
			System.out.println("Please Provide a valid record.");
			return false;
		}
		return true;
	}
	public String findObjectType(GoodsTransport goodsTransport) {
		String objectType = "";
		if( goodsTransport instanceof BrickTransport) {
			objectType = "BrickTransport";
			((BrickTransport) goodsTransport).showDetails();
			System.out.println(goodsTransport.calculateTotalCharge());
		}
		else if(goodsTransport instanceof TimberTransport) {
			objectType = "TimberTransport";
			((TimberTransport) goodsTransport).showDetails();
			System.out.println(goodsTransport.calculateTotalCharge());
		}
		return objectType;
	}
}
