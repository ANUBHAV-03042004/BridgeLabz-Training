package validatecsvdatabeforeprocessing;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateCsvData {
	public static void main(String[] args) {
		String source= "C:\\Users\\HP\\eclipse-workspace\\BridgeLabz-Training\\io-programming-practice\\gcr-codebase\\csvhandling\\validatecsvdatabeforeprocessing\\data.csv";
		
		try (
				BufferedReader br= new BufferedReader(new FileReader(source))
			){
			
			String line;
			line = br.readLine();
			if (line == null) {
			    System.out.println("CSV file is empty or missing header row.");
			    return;
			}
			String[] coulumnNames = line.split(",");
//			line=br.readLine();
//			String[] coulumnNames= line.split(",");
			int indexEmail=-1;
			int indexPhoneNumber=-1;
			for(int i=0;i<coulumnNames.length;i++) {
				if(coulumnNames[i].equalsIgnoreCase("email")) {
					indexEmail=i;
				}
				else if(coulumnNames[i].equalsIgnoreCase("phone number")) {
					indexPhoneNumber=i;
				}
			}
			if(indexEmail==-1 || indexPhoneNumber==-1) {
				System.out.println("Required values not found.");
			}
			while((line= br.readLine())!=null) {
				String[] coulumn= line.split(",");
				
				if(!validateEmail(coulumn[indexEmail]) || !validatePhoneNumber(coulumn[indexPhoneNumber])) {
					System.out.println("Error in field entry:");
					System.out.println(line);
				}
			}
		}
		catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	private static boolean validatePhoneNumber(String string) {
		
		Matcher matcher= Pattern.compile("^\\d{10}$").matcher(string);
		
		if(matcher.matches()) {
			return true;
		}
		
		return false;
	}

	private static boolean validateEmail(String string) {
		
		Matcher matcher= Pattern.compile("^[A-Za-z0-9_%+-]+@[A-Za-z0-9-]+\\.[A-Za-z]{2,}$").matcher(string);
		
		if(matcher.matches()) {
			return true;
		}
		
		return false;
	}
	
	
}