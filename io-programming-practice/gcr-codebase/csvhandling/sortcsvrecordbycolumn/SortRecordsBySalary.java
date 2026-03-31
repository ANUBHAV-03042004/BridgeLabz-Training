package sortcsvrecordbycolumn;
import java.util.*;
import java.io.*;
public class SortRecordsBySalary {
public static void main(String[] args) {
	 String filePath = "C:\\Users\\HP\\eclipse-workspace\\BridgeLabz-Training\\io-programming-practice\\gcr-codebase\\csvhandling\\writedatatocsvfile\\employeedetails.csv";
	 List<String[]> rows = new ArrayList<>();

	 
     try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
         String line;
         while ((line = br.readLine()) != null) {
             rows.add(line.split(","));
         }
     } catch (IOException e) {
         e.printStackTrace();
     }

     if (rows.isEmpty()) {
         System.out.println("CSV file is empty!");
         return;
     }
     String[] header = rows.get(0);
     List<String[]> records = rows.subList(1, rows.size());

     records.sort((a, b) -> Integer.compare(Integer.parseInt(b[3].trim()), Integer.parseInt(a[3].trim())));


     try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
         bw.write(String.join(",", header));
         bw.newLine();

         for (String[] row : records) {
             bw.write(String.join(",", row));
             bw.newLine();
         }
     } catch (IOException e) {
         e.printStackTrace();
     }

     System.out.println("Records sorted by salary and saved back to: " + filePath);
 }

}
