package loggingtransactions;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class Transaction {
	public static void main(String[] args) {
        List<String> transactionIds = Arrays.asList(
            "TXN1001",
            "TXN1002",
            "TXN1003",
            "TXN1004",
            "TXN1005",
            "TXN1006"
        );
transactionIds.forEach(id -> System.out.println(LocalDateTime.now()+" -> " +id));
}
}
