package readajsonfileandprintallkeysandvalues;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONArray;
import org.json.JSONObject;

public class ReadJsonFileAndPrintKeysAndValues {
public static void main(String[] args) throws IOException {
	String content = new String(Files.readAllBytes(Paths.get("C:\\Users\\HP\\eclipse-workspace\\BridgeLabz-Training\\io-programming-practice\\gcr-codebase\\jsondatahandling\\readajsonfileandprintallkeysandvalues\\student.json")));
	JSONArray jarray= new JSONArray(content);
	for(int i=0;i<jarray.length();i++) {
		JSONObject jobj = jarray.getJSONObject(i);
		int id= jobj.getInt("id");
		String name = jobj.getString("name");
		String department = jobj.getString("department");
		int salary = jobj.getInt("salary");
		
		System.out.println(id +":"+ name +" - "+ department+": "+salary);
		
	}

}
}
