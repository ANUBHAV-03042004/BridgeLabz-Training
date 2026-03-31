package invokeobjectcreation;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Transaction{
	int id;
	Transaction(int id){
		this.id=id;
	}
	@Override
	public String toString() {
		return "Transaction with ID:"+ id;
	}
}
public class InvokeObjectCreation {
public static void main(String[] args) {
	 List<Integer> transactionIds = Arrays.asList(1001, 1002, 1003);
     List<Transaction> txn= transactionIds.stream().map(Transaction::new).collect(Collectors.toList());
     txn.forEach(System.out::println);
}
}
