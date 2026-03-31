package modifyacsvfile;

import java.util.*;
import java.io.*;

public class UpdateAValue {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\HP\\eclipse-workspace\\BridgeLabz-Training\\io-programming-practice\\gcr-codebase\\csvhandling\\writedatatocsvfile\\employeedetails.csv";

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter name of the person whose details you have to change:");
        String name = sc.next();
        System.out.println("Enter the column name to change (id/name/dept/salary):");
        String column = sc.next().toLowerCase();
        System.out.println("Enter the new value:");
        String newValue = sc.next();

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
        int colIndex = -1;
        for (int i = 0; i < header.length; i++) {
            if (header[i].equalsIgnoreCase(column)) {
                colIndex = i;
                break;
            }
        }

        if (colIndex == -1) {
            System.out.println("Invalid column name!");
            return;
        }


        boolean updated = false;
        for (int i = 1; i < rows.size(); i++) { 
            String[] person = rows.get(i);
            if (person[1].equalsIgnoreCase(name)) { 
                person[colIndex] = newValue;
                updated = true;
                System.out.println("Updated record for " + name);
                break;
            }
        }

        if (!updated) {
            System.out.println("No record found for " + name);
            return;
        }

        // Write back to file
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (String[] row : rows) {
                bw.write(String.join(",", row));
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("File updated successfully!");
    }
}