package ServiceTest;

import com.TDD.atm.Domain.Account;
import com.TDD.atm.Domain.User;
import com.TDD.atm.Service.BalanceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BalanceServiceTest {

    private BalanceService balanceService;
    private Account testAccount;
    private User testUser;

    // Den här metoden körs innan varje test
    @BeforeEach
    public void setUp() {
        balanceService = new BalanceService();                  // Skapar ny instans av tjänsten
        testAccount = new Account(1000.0);                      // Skapar konto med startsaldo
        testUser = new User("12345678", "1234", testAccount);   // Skapar användare kopplad till kontot
    }

    @Test
    public void shouldReturnCorrectBalanceForValidUser() {
        double balance = balanceService.getBalance(testUser);   // Hämtar saldo för giltig användare
        assertEquals(1000.0, balance);                          // Verifierar att saldot stämmer
    }

    @Test
    public void shouldThrowExceptionWhenUserIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            balanceService.getBalance(null);                    // Förväntar att undantag kastas
        });
    }

    @Test
    public void shouldThrowExceptionWhenAccountIsNull() {
        User userWithNoAccount = new User("12345678", "1234", null); // Skapar användare utan konto
        assertThrows(IllegalArgumentException.class, () -> {
            balanceService.getBalance(userWithNoAccount);       // Förväntar att undantag kastas
        });
    }
}
