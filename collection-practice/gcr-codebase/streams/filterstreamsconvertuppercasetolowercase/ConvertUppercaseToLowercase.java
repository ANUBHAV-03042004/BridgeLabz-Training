package filterstreamsconvertuppercasetolowercase;

	import java.io.*;

	public class ConvertUppercaseToLowercase {
	    public static void main(String[] args) {
	        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\HP\\eclipse-workspace\\BridgeLabz-Training\\collection-practice\\gcr-codebase\\streams\\filterstreamsconvertuppercasetolowercase\\input.txt"));
	             BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\HP\\eclipse-workspace\\BridgeLabz-Training\\collection-practice\\gcr-codebase\\streams\\filterstreamsconvertuppercasetolowercase\\output.txt"))) {

	            String line;
	            while ((line = br.readLine()) != null) {
	                bw.write(line.toLowerCase()); // Convert to lowercase
	                bw.newLine();
	            }
	            System.out.println("File converted to lowercase!");

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	}
