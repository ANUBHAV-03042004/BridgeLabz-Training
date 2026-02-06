package downloadmanager;

import java.util.Random;

class FileDownloaderRunnable implements Runnable{
	Random random = new Random();
	private String file;
FileDownloaderRunnable(String file){this.file = file;}
@Override
public void run() {
    for (int progress = 0; progress <= 100; progress += random.nextInt(20) + 5) {
        if (progress > 100) progress = 100;
        System.out.println("[" + Thread.currentThread().getName() + "] Downloading " 
                           + file + ": " + progress + "%");
        try {
            Thread.sleep(random.nextInt(500) + 200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

	
}
public class DownloadManagerUsingRunnableInterfaceApproach {
public static void main(String[] args) {
	Thread t1 = new Thread(new FileDownloaderRunnable("file.pdf"));
	Thread t2 = new Thread(new FileDownloaderRunnable("photo.jpg"));
	Thread t3 = new Thread(new FileDownloaderRunnable("video.mp4"));
	
	
	t1.start();
	t2.start();
	t3.start();
	try {
		t1.join();
		t2.join();
		t3.join();
	}catch(InterruptedException e) {
		e.printStackTrace();
	}
	  System.out.println("All downloads complete!");

}
}
