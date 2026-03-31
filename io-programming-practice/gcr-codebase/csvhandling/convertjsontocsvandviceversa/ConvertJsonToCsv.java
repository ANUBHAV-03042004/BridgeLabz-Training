package convertjsontocsvandviceversa;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ConvertJsonToCsv {

    public static void main(String[] args) throws IOException {
        String filePath = "C:\\Users\\HP\\eclipse-workspace\\BridgeLabz-Training\\io-programming-practice\\gcr-codebase\\csvhandling\\convertjsontocsvandviceversa\\student.json";
        String outfilePath= "C:\\Users\\HP\\eclipse-workspace\\BridgeLabz-Training\\io-programming-practice\\gcr-codebase\\csvhandling\\convertjsontocsvandviceversa\\student.csv";
        ArrayList<String> col = new ArrayList<>();
        ArrayList<ArrayList<String>> rows = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            ArrayList<String> currentRow = new ArrayList<>();

            while ((line = br.readLine()) != null) {
                line = line.replaceAll("[\\[\\]{}\"]", "").trim();
                if (line.isEmpty()) continue;

                String[] Person = line.split(",");
                for (String emp : Person) {
                    emp = emp.replaceAll(": ", ",");
                    String[] data = emp.split(",");
                    if (data.length == 2) {
                        String key = data[0].trim();
                        String value = data[1].trim();

                        if (!col.contains(key)) {
                            col.add(key);
                        }
                        currentRow.add(value);
                    }
                }

                if (line.endsWith("}") || line.contains("salary")) {
                    if (!currentRow.isEmpty()) {
                        rows.add(new ArrayList<>(currentRow));
                        currentRow.clear();
                    }
                }
            }
        }
        try (FileWriter writer = new FileWriter(outfilePath)) {
            writer.write(String.join(",",col));
            writer.write("\n");


            for (ArrayList<String> row : rows) {
                writer.write(String.join(",", row));
                writer.write("\n");
            }
        }


    }
}