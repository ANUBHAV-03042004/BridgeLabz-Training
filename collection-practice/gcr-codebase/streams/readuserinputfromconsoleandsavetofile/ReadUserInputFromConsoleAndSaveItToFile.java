package readuserinputfromconsoleandsavetofile;
	import java.io.*;

	public class ReadUserInputFromConsoleAndSaveItToFile {
	    public static void main(String[] args) {
	        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	             FileWriter fw = new FileWriter("C:\\Users\\HP\\eclipse-workspace\\BridgeLabz-Training\\collection-practice\\gcr-codebase\\streams\\readuserinputfromconsoleandsavetofile\\userinfo.txt")) {

	            System.out.print("Enter your name: ");
	            String name = br.readLine();
	            System.out.print("Enter your age: ");
	            String age = br.readLine();
	            System.out.print("Enter your favorite programming language: ");
	            String language = br.readLine();

	            fw.write("Name: " + name + "\nAge: " + age + "\nLanguage: " + language);
	            System.out.println("Data saved to userinfo.txt");

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	}
