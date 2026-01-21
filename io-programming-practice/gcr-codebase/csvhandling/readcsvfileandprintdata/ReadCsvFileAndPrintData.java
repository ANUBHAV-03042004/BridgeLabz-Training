package readcsvfileandprintdata;

import java.io.*;

public class ReadCsvFileAndPrintData {
    public static void main(String[] args) {
        String filepath = "C:\\Users\\HP\\eclipse-workspace\\BridgeLabz-Training\\io-programming-practice\\gcr-codebase\\csvhandling\\readcsvfileandprintdata\\studentdetails.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line;

            br.readLine(); 

            while ((line = br.readLine()) != null) {
                String[] columns = line.split(",");

              
                if (columns.length >= 4) {
                    String id = columns[0].trim();
                    String name = columns[1].trim();
                    String age = columns[2].trim();
                    String marks = columns[3].trim();

                    System.out.println("Student Record:");
                    System.out.println("ID : " + id);
                    System.out.println("Name: " + name);
                    System.out.println("Age: " + age);
                    System.out.println("Marks: " + marks);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}