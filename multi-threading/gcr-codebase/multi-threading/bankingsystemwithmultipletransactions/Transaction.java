package bankingsystemwithmultipletransactions;

public class Transaction implements Runnable{
	  private BankAccount account;
	    private String customerName;
	    private int amount;

	    public Transaction(BankAccount account, String customerName, int amount) {
	        this.account = account;
	        this.customerName = customerName;
	        this.amount = amount;
	    }

	    @Override
	    public void run() {
	        account.withdraw(customerName, amount);
	    }
}
