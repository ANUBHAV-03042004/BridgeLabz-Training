package searchforarecordincsv;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
public class SearchForARecord {
public static void main(String[] args) {
	String filePath="C:\\Users\\HP\\eclipse-workspace\\BridgeLabz-Training\\io-programming-practice\\gcr-codebase\\csvhandling\\writedatatocsvfile\\employeedetails.csv";
	try(BufferedReader br= new BufferedReader(new FileReader(filePath)))
	{
	String line;
	br.readLine();
	Scanner sc= new Scanner(System.in);
	System.out.println("Enter the name to be found : ");
	String name =  sc.next();
	while((line = br.readLine())!=null) {
		String Person[]= line.split(",");
		if(Person[1].equalsIgnoreCase(name)) {
			System.out.println("Name :"+ name);
			System.out.println("Department :"+ Person[2]);
			System.out.println("Salary :"+ Person[3]);
		}
	}

	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
}
}
