package addressbook;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonIO {

	public void readAndWrite(Dictionary dictionary) {
	String filePath = "C:\\Users\\HP\\eclipse-workspace\\BridgeLabz-Training\\oops-practice\\gcr-codebase\\addressbookproblem\\addressbook\\Dictionary.json";
	Gson gson = new GsonBuilder().setPrettyPrinting().create();
			
    try (FileWriter writer = new FileWriter(filePath)) {
        gson.toJson(dictionary, writer);
        System.out.println("JSON file written successfully!");
    } catch (IOException e) {
        e.printStackTrace();
    }

    try (FileReader reader = new FileReader(filePath)) {
        Dictionary readDictionary = gson.fromJson(reader, Dictionary.class);
        System.out.println(readDictionary.dictionary); // prints the map
    } catch (IOException e) {
        e.printStackTrace();
    }
	
	}

}
