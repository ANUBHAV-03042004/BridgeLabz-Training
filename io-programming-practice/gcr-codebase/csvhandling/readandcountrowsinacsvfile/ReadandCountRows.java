package readandcountrowsinacsvfile;
import java.io.*;
import java.util.*;
public class ReadandCountRows {
public static void main(String[] args) {
	String filePath="C:\\Users\\HP\\eclipse-workspace\\BridgeLabz-Training\\io-programming-practice\\gcr-codebase\\csvhandling\\writedatatocsvfile\\employeedetails.csv";
	try(BufferedReader br= new BufferedReader(new FileReader(filePath)))
	{
	String line;
	br.readLine();
	int rows=0;
	while((line = br.readLine())!=null) {
		rows++;
	}
	System.out.println("Number of Rows : "+ rows);
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
}
}
