package smartcitytransportandservicemanagementsystem;

import java.util.List;

public class TaxiService extends Vehicle implements TransportService{

	TaxiService(String type,List<String>route){
		super(type,route);
	}

}
