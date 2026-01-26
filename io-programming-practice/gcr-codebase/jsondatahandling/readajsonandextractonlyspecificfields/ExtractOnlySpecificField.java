package readajsonandextractonlyspecificfields;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;
public class ExtractOnlySpecificField {
public static void main(String[] args) {
	  String filePath = "C:\\Users\\HP\\eclipse-workspace\\BridgeLabz-Training\\io-programming-practice\\gcr-codebase\\csvhandling\\convertjsontocsvandviceversa\\student.json";
	  try(BufferedReader br =  new BufferedReader(new FileReader(filePath))){
		  String line ;
		  StringBuilder sb= new StringBuilder();
		  while((line = br.readLine())!=null) {
			  sb.append(line);
		  }
		  JSONArray jsonArr = new JSONArray(sb.toString());
		  for(int i=0;i<jsonArr.length();i++) {
	     JSONObject employee = jsonArr.getJSONObject(i);
		  String name =  employee.getString("name");
		  int salary = employee.getInt("salary");
		  System.out.println("Name : "+ name);
		  System.out.println("Salary : "+salary);
		  }
		  
	  } catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
	
	}
}
