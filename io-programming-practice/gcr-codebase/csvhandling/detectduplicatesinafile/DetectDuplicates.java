package detectduplicatesinafile;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

public class DetectDuplicates {
public static void main(String[] args) {
	String filePath ="C:\\Users\\HP\\eclipse-workspace\\BridgeLabz-Training\\io-programming-practice\\gcr-codebase\\csvhandling\\readcsvfileandprintdata\\studentdetails.csv";
	HashSet<String> hset= new HashSet<>();
	try(BufferedReader br = new BufferedReader(new FileReader(filePath))){
		String line;
		br.readLine();
		while((line=br.readLine())!=null) {
			if(!hset.contains(line)) {
			hset.add(line);
			}else {
				System.out.println(line);
			}
		}
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
}
}
