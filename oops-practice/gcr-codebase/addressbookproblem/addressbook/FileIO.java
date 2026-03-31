package addressbook;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class FileIO {
  void readAndWrite(Dictionary dictionary) {
	
	  String filePath = "C:\\Users\\HP\\eclipse-workspace\\BridgeLabz-Training\\oops-practice\\gcr-codebase\\addressbookproblem\\addressbook\\Dictionary.txt";
	  
	  try(BufferedWriter writer = new BufferedWriter(new FileWriter(filePath,true))){
		 for(Map.Entry<String,AddressBook> entry :  dictionary.dictionary.entrySet()) {
		     writer.write(entry.getKey() +" : ");
			 writer.write(entry.getValue().toString());
			 writer.write("\n");
		 }
	  } catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
	  
	  try(BufferedReader reader = new BufferedReader(new FileReader(filePath))){
		  String line;
		  while((line=reader.readLine())!=null) {
			  System.out.println(line);
		  }
	  } catch (FileNotFoundException e) {
	
		e.printStackTrace();
	} catch (IOException e) {
	
		e.printStackTrace();
	}
  }
}
