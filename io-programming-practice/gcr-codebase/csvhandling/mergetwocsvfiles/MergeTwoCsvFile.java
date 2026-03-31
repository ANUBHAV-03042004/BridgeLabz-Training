package mergetwocsvfiles;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class MergeTwoCsvFile {
public static void main(String[] args) {
	String filePath1 = "C:\\Users\\HP\\eclipse-workspace\\BridgeLabz-Training\\io-programming-practice\\gcr-codebase\\csvhandling\\mergetwocsvfiles\\studentA.csv";
	String filePath2 = "C:\\Users\\HP\\eclipse-workspace\\BridgeLabz-Training\\io-programming-practice\\gcr-codebase\\csvhandling\\mergetwocsvfiles\\studentB.csv";
	String filePath3 = "C:\\Users\\HP\\eclipse-workspace\\BridgeLabz-Training\\io-programming-practice\\gcr-codebase\\csvhandling\\mergetwocsvfiles\\studentC.csv";
	LinkedHashMap<String,String> lhm= new LinkedHashMap<>();
	try(BufferedReader br1= new BufferedReader(new FileReader(filePath1))){
		
		String line;
		while((line=br1.readLine())!=null) {
			String Student[]= line.split(",");
			String id= Student[0].trim();
			lhm.put(id,line);
		}
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
	try(BufferedReader br2 = new BufferedReader(new FileReader(filePath2))) {
		String line;
		while((line=br2.readLine())!=null) {
			String Student[]= line.split(",");
			String id= Student[0].trim();
			String details = Student[1]+","+Student[2];
			lhm.put(id,lhm.get(id).concat(",").concat(details));
	}
	} catch (FileNotFoundException e1) {
		e1.printStackTrace();
	} catch (IOException e1) {
		e1.printStackTrace();
	}
	try(BufferedWriter wr= new BufferedWriter(new FileWriter(filePath3))){
			for(String entry:lhm.keySet()) {
				wr.write(lhm.get(entry));
				wr.newLine();
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
}
}
