package bankingsystemwithmultipletransactions;

import java.util.Date;
import java.text.SimpleDateFormat;

public class BankAccount {
	 private int balance = 10000;

	    public synchronized boolean withdraw(String customerName, int amount) {
	        System.out.println("[" + customerName + "] Attempting to withdraw " + amount);

	        if (balance >= amount) {
	            try {
	                Thread.sleep(500); 
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }

	            balance -= amount;
	            String timestamp = new SimpleDateFormat("HH:mm:ss").format(new Date());
	            System.out.println("Transaction successful: " + customerName +
	                               ", Amount: " + amount +
	                               ", Balance: " + balance +
	                               ", Time: " + timestamp);
	            return true;
	        } else {
	            String timestamp = new SimpleDateFormat("HH:mm:ss").format(new Date());
	            System.out.println("Transaction failed: " + customerName +
	                               ", Amount: " + amount +
	                               ", Balance: " + balance +
	                               ", Time: " + timestamp);
	            return false;
	        }
	    }

}
