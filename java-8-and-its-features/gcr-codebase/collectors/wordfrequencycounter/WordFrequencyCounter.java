package wordfrequencycounter;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class WordFrequencyCounter {
public static void main(String[] args) {
	String paragraph ="The time has come to act, for this is the time to change. We have waited too long, and in waiting, we have lost precious time. No more excuses, no more delays—just time to make a difference. If we don't use this time wisely, the time will surely run out.";
	String words [] = paragraph.replaceAll("\\p{Punct}","").toLowerCase().split("\\s+");
//	List<String> counter= Arrays.asList(words);
	Map<String, Integer> wordFrequencies = Arrays.stream(words).collect(Collectors.toMap(
			word->word,word->1,Integer::sum
			));
		System.out.println("word frequencies : "+wordFrequencies);	
}
}
