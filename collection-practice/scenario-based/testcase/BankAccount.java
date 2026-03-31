package testcase;

public class BankAccount {
double balance;
BankAccount(double balance){
	this.balance=balance;
}
void deposit(double amount) {
	if(amount < 0) throw new IllegalArgumentException("Deposit cannot be negative");
	else {
		balance += amount;
	}
}
void withdraw(double amount) {
	if(amount > balance) throw new IllegalArgumentException("Insufficient Funds");
	else {
		balance-=amount;
	}
}

}
