package readalargefilelinebyline;
	import java.io.*;

	public class ReadALargeFileLineByLine {
	    public static void main(String[] args) {
	        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\HP\\eclipse-workspace\\BridgeLabz-Training\\collection-practice\\gcr-codebase\\streams\\readalargefilelinebyline\\input.txt"))) {
	            String line;
	            while ((line = br.readLine()) != null) {
	                if (line.toLowerCase().contains("error")) {
	                    System.out.println(line);
	                }
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	}
