package downloadmanager;

import java.util.Random;

class FileDownloader extends Thread{
	Random random = new Random();
	private String file = "";
	public FileDownloader(String file) {
		this.file=file;
	}
	public String getfile() { return file;}
	
	@Override
    public void run() {
        for (int progress = 0; progress <= 100; progress += random.nextInt(20) + 5) {
            if (progress > 100) progress = 100;
            System.out.println("[" + Thread.currentThread().getName() + "] Downloading " 
                               + getfile() + ": " + progress + "%");
            try {
                Thread.sleep(random.nextInt(500) + 200); // random delay
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
public class DownloadManagerUsingThreadClassApproach {
	
public static void main(String[] args) {
	FileDownloader file1 = new FileDownloader("file.pdf");
	FileDownloader file2 = new FileDownloader("photo.jpg");
	FileDownloader file3 = new FileDownloader("video.mp4");
	
	file1.start();
	file2.start();
	file3.start();
	
	try {
		file1.join();
		file2.join();
		file3.join();
	}catch(InterruptedException e) {
		e.printStackTrace();
	}
	  System.out.println("All downloads complete!");

}
}
