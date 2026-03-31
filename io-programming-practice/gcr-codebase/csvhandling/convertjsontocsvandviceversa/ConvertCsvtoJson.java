package convertjsontocsvandviceversa;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
public class ConvertCsvtoJson {
public static boolean isInteger(String str) {
	try {
		if(str == null) return false;
		Integer.parseInt(str);
		return true;
	}catch(Exception e){
		return false;
	}
}

	    public static void main(String[] args) throws IOException {
	        String filePath = "C:\\Users\\HP\\eclipse-workspace\\BridgeLabz-Training\\io-programming-practice\\gcr-codebase\\csvhandling\\convertjsontocsvandviceversa\\student.csv";
	        String outfilePath= "C:\\Users\\HP\\eclipse-workspace\\BridgeLabz-Training\\io-programming-practice\\gcr-codebase\\csvhandling\\convertjsontocsvandviceversa\\converted.json";
	       ArrayList <String> rows= new ArrayList<>();
            StringBuilder Json = new StringBuilder();
            Json.append("[\n");
	        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
	            String line;
	            String header[]= br.readLine().split(",");
	            while((line= br.readLine())!=null) {
	            	String row[]= line.split(",");
	            	rows.addAll(Arrays.asList(row));
	            }
	           int n=(rows.size()/ header.length);
	           int index=0,countcomma=0;
	           for(int i=0;i<n;i++) {
	        	   Json.append("{\n");
	        	 for(int j=0;j<header.length;j++) {
	        		 Json.append("\"").append(header[j]).append("\" : ");
	        		 if(isInteger(rows.get(index))) {
	        			 Json.append(rows.get(index)).append(",");
	        		 }
	        		 else Json.append("\"").append(rows.get(index)).append("\"").append(",");
	        		 index++;
	        		 if(j != (header.length-1)) Json.append("\n");
	        	 }
	        	 Json.append("\n}");
	        	 if(countcomma<n-1) Json.append(",\n");
	        	 countcomma++;
	           }
	           Json.append("\n]");
       System.out.println(Json.toString());
	        }
	


	    }
	}
