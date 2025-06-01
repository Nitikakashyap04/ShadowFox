import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class BankAccountTest {
    private BankAccount account;

    @BeforeEach
    public void setUp() {
        account = new BankAccount("John Doe");
    }

    @Test
    public void testInitialBalanceIsZero() {
        assertEquals(0.0, account.getBalance());
    }

    @Test
    public void testDeposit() {
        account.deposit(1000);
        assertEquals(1000, account.getBalance());
    }

    @Test
    public void testWithdrawSuccess() {
        account.deposit(1000);
        assertTrue(account.withdraw(500));
        assertEquals(500, account.getBalance());
    }

    @Test
    public void testWithdrawFailure() {
        assertFalse(account.withdraw(100));
    }

    @Test
    public void testTransactionHistory() {
        account.deposit(500);
        account.withdraw(200);
        List<String> history = account.getTransactionHistory();
        assertEquals(2, history.size());
        assertEquals("Deposited: 500.0", history.get(0));
        assertEquals("Withdrew: 200.0", history.get(1));
    }
}
