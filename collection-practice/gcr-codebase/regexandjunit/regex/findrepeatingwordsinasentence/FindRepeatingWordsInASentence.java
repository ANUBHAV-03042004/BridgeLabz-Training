package findrepeatingwordsinasentence;
import java.util.*;
public class FindRepeatingWordsInASentence {
	public static void main(String args[]) {
		HashMap<String,Integer>hmap= new HashMap<>();
		String text="This is is a repeated repeated word test.";
		String str[]=text.split("[,\\s\\.]");
		for(String s:str) {
			hmap.put(s,hmap.getOrDefault(s,0)+1);
		}
//		for(Map.Entry<String,Integer>entry:hmap.entrySet())
//		{
//			if(entry.getValue()>=2) System.out.println(entry.getKey());
//		}
		for(String key:hmap.keySet()) {
			if(hmap.get(key)>=2) System.out.println(key);
		}
}
}