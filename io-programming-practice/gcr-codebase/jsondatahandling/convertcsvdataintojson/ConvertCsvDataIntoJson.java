package convertcsvdataintojson;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

public class ConvertCsvDataIntoJson {
    public static void main(String[] args) {
        try {
            // Path to your CSV file
            String filePath = "C:\\Users\\HP\\eclipse-workspace\\BridgeLabz-Training\\io-programming-practice\\gcr-codebase\\csvhandling\\convertjsontocsvandviceversa\\student.csv";

            // Create CsvMapper and schema (first row = header)
            CsvMapper csvMapper = new CsvMapper();
            CsvSchema schema = CsvSchema.emptySchema().withHeader();

            // Read CSV into List of Maps
            List<Object> data = csvMapper
                    .readerFor(Map.class)
                    .with(schema)
                    .readValues(new File(filePath))
                    .readAll();

            // Convert to JSON
            ObjectMapper jsonMapper = new ObjectMapper();
            String json = jsonMapper.writerWithDefaultPrettyPrinter().writeValueAsString(data);

            // Print JSON
            System.out.println("Converted JSON:");
            System.out.println(json);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}