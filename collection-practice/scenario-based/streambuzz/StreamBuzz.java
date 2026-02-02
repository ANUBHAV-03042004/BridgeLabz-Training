package streambuzz;

import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;

public class StreamBuzz {
	
	public void RegisterCreator(CreatorStats record) {
		CreatorStats.EngagementBoard.add(record);
	}
	public Dictionary<String, Integer> GetTopPostCounts(List<CreatorStats> records, double likeThreshold){
		Dictionary<String,Integer> dict = new Hashtable<String, Integer>();
		
		for( CreatorStats rec: records) {
			int no_ofweeks=0;
			for(double week : rec.getWeeklyLikes()) {
				if(week >= likeThreshold) no_ofweeks++;
			}
			if(no_ofweeks>0) dict.put(rec.CreatorName, no_ofweeks);
		}
		return dict;
		
	}
	public double CalculateAverageLikes() {
	    double totalSum = 0.0d;
	    int totalCount = 0;
	    for(CreatorStats rec : CreatorStats.EngagementBoard) {
	        for(double weekLike: rec.getWeeklyLikes()) {
	            totalSum += weekLike;
	            totalCount++;
	        }
	    }
	    return totalSum / totalCount;
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		boolean stop=false;
		while(true) {
		System.out.println("Enter choice : ");
		int choice = sc.nextInt();
		  
		switch(choice) {
		case 1:
			System.out.println("Enter name : ");
			sc.nextLine();
			String CreatorName = sc.nextLine();
			double [] WeeklyLikes = new double [4];
			for(int i = 0; i < WeeklyLikes.length; i++) {
			    System.out.println("Enter likes for week " + (i+1) + " : ");
			    WeeklyLikes[i] = sc.nextDouble();
			}
			CreatorStats cs = new CreatorStats(CreatorName, WeeklyLikes);
			StreamBuzz sb= new StreamBuzz();
			sb.RegisterCreator(cs);
			System.out.println("Creator Registered Successfully.\n");
			break;
			
		case 2 :
			System.out.println("Enter the threshold :");
			double threshold = sc.nextDouble();
			Dictionary<String,Integer> dict;
		   	sb = new StreamBuzz();
			dict  = sb.GetTopPostCounts(CreatorStats.EngagementBoard, threshold);
			if(dict.isEmpty()) {
				System.out.println("No top-performing posts this week");
				break;
			}
			Enumeration<String> enumerator = dict.keys();
			while (enumerator.hasMoreElements()) {
			    String key = enumerator.nextElement();
			    System.out.println(key + " -> " + dict.get(key)); 
			}
            break;
		case 3 :
			sb = new StreamBuzz();
			double average = sb.CalculateAverageLikes();
			System.out.println("Overall average weekly likes: "+ average);
			break;
		case 4:
		     System.out.println("Logging off — Keep Creating with StreamBuzz!");
		     stop = true;
		     break;	
		}
	if(stop) break;
		}
		
	}
}
