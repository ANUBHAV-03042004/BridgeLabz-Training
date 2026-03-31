package writedatatocsvfile;
import java.util.*;
import java.io.*;
public class WriteDataToACsvFile {
public static void main(String[] args) {
	String filePath="C:\\Users\\HP\\eclipse-workspace\\BridgeLabz-Training\\io-programming-practice\\gcr-codebase\\csvhandling\\writedatatocsvfile\\employeedetails.csv";
	try(BufferedWriter wr= new BufferedWriter(new FileWriter(filePath))) {
		  wr.write("Id ,Name,Department,Salary\n");
		  wr.write("001,Aks,CS,45000\n");
		  wr.write("002,Sahil,Pharma,40000\n");
		  wr.write("003,Sai,MBA,40000\n");
		  wr.write("004,Sam,Pharma,50000\n");
		  wr.write("005,Prakash,CS,80000\n");
		
	}catch(IOException e) {
		e.printStackTrace();
	}
}
}
