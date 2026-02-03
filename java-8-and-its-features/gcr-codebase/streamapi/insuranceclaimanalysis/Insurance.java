package insuranceclaimanalysis;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Insurance {
	    private String type;
	    private double amount;

	    public Insurance(String type, double amount) {
	        this.type = type;
	        this.amount = amount;
	    }

	    public String getType() { return type; }
	    public double getAmount() { return amount; }

	    @Override
	    public String toString() {
	        return String.format("%s: %.2f", type, amount);
	    }
	    public static void main(String[] args) {
	    	 List<Insurance> claims = Arrays.asList(
	    	            new Insurance("Health", 12000),
	    	            new Insurance("Health", 8000),
	    	            new Insurance("Auto", 15000),
	    	            new Insurance("Auto", 10000),
	    	            new Insurance("Home", 25000),
	    	            new Insurance("Home", 20000),
	    	            new Insurance("Travel", 5000)
	    	        );
	    	 Map<String,Double> average = claims.stream().collect(Collectors.groupingBy(Insurance::getType,Collectors.averagingDouble(Insurance::getAmount)));
	    	 average.forEach((getType,getAmount)->{
	    		 System.out.println(getType+" -> "+getAmount);
	    	 });

		}
	}
