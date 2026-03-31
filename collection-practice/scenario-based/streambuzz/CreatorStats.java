package streambuzz;

import java.util.ArrayList;
import java.util.List;

public class CreatorStats {
String CreatorName;
double [] WeeklyLikes;
 CreatorStats(String CreatorName,double [] WeeklyLikes){
	 this.CreatorName=CreatorName;
	 this.WeeklyLikes=WeeklyLikes;
 }
public String getCreatorName() {
	return CreatorName;
}
public double[] getWeeklyLikes() {
	return WeeklyLikes;
}
public static List<CreatorStats> EngagementBoard =new ArrayList<>();
;
}
