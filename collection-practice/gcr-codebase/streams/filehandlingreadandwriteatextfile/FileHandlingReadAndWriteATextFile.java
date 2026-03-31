package filehandlingreadandwriteatextfile;

import java.io.*;

public class FileHandlingReadAndWriteATextFile {
    public static void main(String[] args) {
     
    	File source = new File("C:\\Users\\HP\\eclipse-workspace\\BridgeLabz-Training\\collection-practice\\gcr-codebase\\streams\\filehandlingreadandwriteatextfile\\readandwrite.txt");
    	File destination = new File("C:\\Users\\HP\\eclipse-workspace\\BridgeLabz-Training\\collection-practice\\gcr-codebase\\streams\\filehandlingreadandwriteatextfile\\copy.txt");


        try (FileInputStream fis = new FileInputStream(source);
             FileOutputStream fos = new FileOutputStream(destination)) {

            int data;
            while ((data = fis.read()) != -1) { // Read byte by byte
                fos.write(data);
            }
            System.out.println("File copied successfully to: " + destination.getAbsolutePath());

        } catch (FileNotFoundException e) {
            System.out.println("Source file not found! Please check the path: " + source.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
