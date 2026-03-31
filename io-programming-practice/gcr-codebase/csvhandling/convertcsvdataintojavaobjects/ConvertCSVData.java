package convertcsvdataintojavaobjects;
import java.io.*;
import java.util.*;
public class ConvertCSVData {
public static void main(String[] args) {
	String filePath="C:\\Users\\HP\\eclipse-workspace\\BridgeLabz-Training\\io-programming-practice\\gcr-codebase\\csvhandling\\validatecsvdatabeforeprocessing\\data.csv";
	ArrayList<Student> Jobj= new ArrayList<>();
	try (BufferedReader br= new BufferedReader(new FileReader(filePath))){
		String line;
		
		while((line =br.readLine())!=null) {
			String StudentArray[]=line.split(",");
			Jobj.add(new Student(StudentArray[0],StudentArray[1],StudentArray[2],StudentArray[3]));
		}
		while(!Jobj.isEmpty()) {
			System.out.println(Jobj.remove(0).toString());
		}
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
}
}
