package filterstudentswhoscoredmorethan80anddisplaythem;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FilterStudents {
public static void main(String[] args) {
	String filePath="C:\\Users\\HP\\eclipse-workspace\\BridgeLabz-Training\\io-programming-practice\\gcr-codebase\\csvhandling\\readcsvfileandprintdata\\studentdetails.csv";
	try(BufferedReader br= new BufferedReader(new FileReader(filePath)))
	{
	String line;
	br.readLine();
	while((line = br.readLine())!=null) {
		String person[]= line.split(",");
		int marks = Integer.parseInt(person[3]);
		if(marks>80) {
			System.out.println("ID : "+person[0]);
			System.out.println("Name : "+person[1]);
			System.out.println("Age : "+person[2]);
			System.out.println("Marks : "+person[3]);
		}
	}

	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
}
}
