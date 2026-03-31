package restaurantorderprocessingsystem;

import java.util.Random;

public class Chef extends Thread {
int time;
String dish;
Random rand= new Random();
Chef(String name,String dish,int time){
	super(name);
	this.time=time;
	this.dish=dish;
}

	@Override
	public void run() {
		for(int i=0;i<=100;i+=25) {
			System.out.println("Preparing "+dish+" ->"+Thread.currentThread().getName()+"-@"+ i+"%");
			
			
			try {
				Thread.sleep(500 + time);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	public static void main(String[] args) {
	Chef chef1 = new Chef("chef-x","pasta",2);
	Chef chef2 = new Chef("chef-y","maggie",5);
	Chef chef3 = new Chef("chef-z","Omlette",10);
	Chef chef4 = new Chef("chef-A","Burger",15);
	chef1.start();
	chef2.start();
	chef3.start();
	chef4.start();
	
	try {
		chef1.join();
		chef2.join();
		chef3.join();
		chef4.join();
	}catch(InterruptedException e) {
		e.printStackTrace();
	}
	System.out.println("Kitchen closed - All orders completed");
	}
}
