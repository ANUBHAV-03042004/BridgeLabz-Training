package backgroundjobexecution;

public class BackgroundJobExecution {
public static void main(String[] args) {
	
	Runnable task1 = ()-> {
		System.out.println("printing image."+ Thread.currentThread().getName());
	};
	Runnable task2 = () ->{
		System.out.println("Scanning card. "+  Thread.currentThread().getName());
	};
	Runnable task3 = ()->{
		System.out.println("Executing operation."+ Thread.currentThread().getName());
	};
	
	Thread t1 = new Thread(task1);
	Thread t2 = new Thread(task2);
	Thread t3 = new Thread(task3);
	
	t1.start();
	t2.start();
	t3.start();
	
	
}
}
