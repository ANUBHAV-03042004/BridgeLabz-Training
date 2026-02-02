package streambuzz;

import java.util.Dictionary;
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
		double sum = 0.0d,avg=0.0d;
		for(CreatorStats rec : CreatorStats.EngagementBoard) {
			for(double weekLike: rec.getWeeklyLikes()) {
				sum+= weekLike;
			}
			avg= sum/rec.getWeeklyLikes().length;
		}
		return avg = avg / CreatorStats.EngagementBoard.size();  
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while(true) {
		System.out.println("Enter choice : ");
		int choice = sc.nextInt();
		StreamBuzz sb = null;
		switch(choice) {
		case 1:
			System.out.println("Enter name : ");
			String CreatorName = sc.nextLine();
			System.out.println("Enter no. of weekly likes");
			int no_ofWeeklyLikes = sc.nextInt();
			double [] WeeklyLikes = new double [no_ofWeeklyLikes];
			for(double i:WeeklyLikes) {
				System.out.println("Enter values : ");
				i=sc.nextDouble();
			}
			CreatorStats cs = new CreatorStats(CreatorName, WeeklyLikes);
			sb= new StreamBuzz();
			sb.RegisterCreator(cs);
			System.out.println("Creator Registered Successfully.");
			break;
			
		case 2 :
			System.out.println("Enter the threshold");
			double threshold = sc.nextDouble();
			Dictionary<String,Integer> dict;
			dict  = sb.GetTopPostCounts(CreatorStats.EngagementBoard, threshold);
			
			
			
			
		}
	
		}
		
	}
}
