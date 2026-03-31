package testcase;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UnitTest {
BankAccount bank = new BankAccount(0);
	@Test
	public void Test_Deposit_ValidAmount() {
		bank.deposit(500);
		assertEquals(500d, bank.balance);
	}
	@Test
	public void Test_Deposit_NegativeAmount() {
		Exception ex = assertThrows(IllegalArgumentException.class, () -> bank.deposit(-200));
	    assertEquals("Deposit cannot be negative", ex.getMessage());
	}
  @Test
  public void Test_Withdraw_ValidAmount() {
	  BankAccount bank = new BankAccount(500); 
	  bank.withdraw(200);
	  assertEquals(300,bank.balance);
  }
@Test
public void Test_Withdraw_InsufficientFunds() {
//	bank.withdraw(10);
	Exception ex = assertThrows(IllegalArgumentException.class, () -> bank.withdraw(10));
    assertEquals("Insufficient Funds", ex.getMessage());

}

}
