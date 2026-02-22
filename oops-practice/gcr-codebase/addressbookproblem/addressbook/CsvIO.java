package addressbook;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

public class CsvIO {
	public void readAndWrite(Dictionary dictionary) {
		String filePath ="C:\\Users\\HP\\eclipse-workspace\\BridgeLabz-Training\\oops-practice\\gcr-codebase\\addressbookproblem\\addressbook\\Dictionary.csv";
  try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
	   
	            String[] header = {"Key", "AddressBook"};
	            writer.writeNext(header);

	 
	            for (Map.Entry<String, AddressBook> entry : dictionary.dictionary.entrySet()) {
	                String[] row = {entry.getKey(), entry.getValue().toString()};
	                writer.writeNext(row);
	            }

	            System.out.println("CSV file written successfully!");

	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
  try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
      List<String[]> rows;
		rows = reader.readAll();
      for (String[] row : rows) {
          System.out.println(String.join(" | ", row)+"\n");
      }

  } catch (IOException | CsvException e) {
      e.printStackTrace();
  }
	}
}
