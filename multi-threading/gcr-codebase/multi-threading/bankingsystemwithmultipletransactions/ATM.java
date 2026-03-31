package bankingsystemwithmultipletransactions;

public class ATM {
	public static void main(String[] args) {
        BankAccount account = new BankAccount();

        Thread t1 = new Thread(new Transaction(account, "Customer-1", 3000), "Thread-1");
        Thread t2 = new Thread(new Transaction(account, "Customer-2", 4000), "Thread-2");
        Thread t3 = new Thread(new Transaction(account, "Customer-3", 2000), "Thread-3");
        Thread t4 = new Thread(new Transaction(account, "Customer-4", 5000), "Thread-4");
        Thread t5 = new Thread(new Transaction(account, "Customer-5", 1500), "Thread-5");

  
        System.out.println(t1.getName() + " State: " + t1.getState());
        System.out.println(t2.getName() + " State: " + t2.getState());
        System.out.println(t3.getName() + " State: " + t3.getState());
        System.out.println(t4.getName() + " State: " + t4.getState());
        System.out.println(t5.getName() + " State: " + t5.getState());


        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();

        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
            t5.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("All transactions processed!");
    }

}
