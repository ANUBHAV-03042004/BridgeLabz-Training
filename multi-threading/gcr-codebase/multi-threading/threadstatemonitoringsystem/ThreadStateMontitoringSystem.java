package threadstatemonitoringsystem;

import java.text.SimpleDateFormat;
import java.util.Date;

class TaskRunnerThread extends Thread{
	private Object lock;
	TaskRunnerThread(String name,Object lock){
		super(name);
		this.lock=lock;
	}
	@Override
	public void run() {
		try {
			Thread.sleep(500);
			synchronized (lock) {
				Thread.sleep(2000);
				lock.wait();
			}
			int sum=0;
			for(int i=0;i<10000;i++) {
				sum+=i;
			}
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
}
class StateMonitoringThread extends Thread{
	Thread threads[];
	public StateMonitoringThread(Thread[] threads) {
		this.threads=threads;
	}
	  @Override
	    public void run() {
	        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	        boolean allTerminated = false;

	        while (!allTerminated) {
	            allTerminated = true;
	            for (Thread t : threads) {
	                Thread.State state = t.getState();
	                String timestamp = sdf.format(new Date());
	                System.out.println("[Monitor] " + t.getName() + " is in " + state + " state at " + timestamp);

	                if (state != Thread.State.TERMINATED) {
	                    allTerminated = false;
	                }
	            }

	            try {
	                Thread.sleep(500); 
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
	    }

}

public class ThreadStateMontitoringSystem{
public static void main(String[] args) throws InterruptedException {
	 Object lock = new Object();

	TaskRunnerThread trt1 = new TaskRunnerThread("Task-1",lock);
	TaskRunnerThread trt2 = new TaskRunnerThread("Task-2",lock);
	TaskRunnerThread trt3 = new TaskRunnerThread("Task-3",lock);
	
	
	StateMonitoringThread smt = new StateMonitoringThread(new Thread[] {trt1,trt2,trt3});
	smt.start();

	
	  Thread.sleep(1000);

	  
	trt1.start();
	trt2.start();
	trt3.start();
	
	 Thread.sleep(3000);
	 
	 // Notify waiting threads so they can continue
     synchronized (lock) {
         lock.notifyAll();
     }

	
	try {
		trt1.join();
		trt2.join();
		trt3.join();
	}catch(InterruptedException e) {
		e.printStackTrace();
	}
}
}
