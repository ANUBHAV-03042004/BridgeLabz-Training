package iplcensoranalyzer;

import java.io.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.*;


public class IplCensorAnalyzer {

    public static void main(String[] args) {

        String inputFile= "C:\\Users\\HP\\eclipse-workspace\\BridgeLabz-Training\\io-programming-practice\\gcr-codebase\\jsondatahandling\\iplcensoranalyzer\\matches.csv";

        if (inputFile.endsWith(".csv")) {
            censorCSV(inputFile, "C:\\Users\\HP\\eclipse-workspace\\BridgeLabz-Training\\io-programming-practice\\gcr-codebase\\jsondatahandling\\iplcensoranalyzer\\censored_ipl.csv");
        } else if (inputFile.endsWith(".json")) {
            censorJSON(inputFile, "censored_ipl.json");
        } else {
            System.out.println("Unsupported file format");
        }
    }

    private static void censorCSV(String input, String output) {
        try (
            BufferedReader br= new BufferedReader(new FileReader(input));
            BufferedWriter bw= new BufferedWriter(new FileWriter(output))
        ) {
            String line;

            bw.write(br.readLine());
            bw.newLine();

            while ((line=br.readLine())!=null) {
                String[] data= line.split(",");

                data[1]= maskTeam(data[1]);
                data[2]= maskTeam(data[2]);
                data[5]= maskTeam(data[5]);
                data[6]= "REDACTED";

                bw.write(String.join(",", data));
                bw.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void censorJSON(String input, String output) {
        try {
            ObjectMapper mapper= new ObjectMapper();

            JsonNode matches= mapper.readTree(new File(input));

            for (JsonNode match:matches) {

                ObjectNode obj= (ObjectNode) match;

                obj.put("team1", maskTeam(obj.get("team1").asText()));
                obj.put("team2", maskTeam(obj.get("team2").asText()));

                obj.put("winner", maskTeam(obj.get("winner").asText()));

                obj.put("player_of_match", "REDACTED");
            }

            mapper.writeValue(new File(output), matches);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static String maskTeam(String team) {
        String[] parts= team.trim().split(" ");
        return parts[0] + " ***";
    }
}
