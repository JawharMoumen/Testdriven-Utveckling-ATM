package ServiceTest;

import com.TDD.atm.Domain.Account;
import com.TDD.atm.Domain.User;
import com.TDD.atm.Service.WithdrawalService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.Assert.assertEquals;

public class WithdrawalServiceTest {
    private WithdrawalService withdrawalService;
    private User user;

    @BeforeEach
    public void setUp() {
        user = new User("123456", "1111", Collections.singletonList(new Account(1000.0)));
        // Skapar en User med ett konto
        withdrawalService = new WithdrawalService();
    }

    @Test
    void WithdrawAmountshouldwork() {
        // Använd konto från användaren (user.getAccount())
        withdrawalService.withdraw(user.getAccount(), 200.0);
        Assertions.assertEquals(800.0, user.getAccount().getBalance());
    }

    @Test
    void withdrawShouldReduceBalanceByAmount() {
        // Använd konto från användaren (user.getAccount())
        withdrawalService.withdraw(user.getAccount(), 200.0);
        assertEquals(800.0, user.getAccount().getBalance(), 0.001);
    }

    @Test
    void withdrawShouldFailIfNotEnoughMoney() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            // Här försöker vi ta ut mer pengar än vad som finns på kontot
            withdrawalService.withdraw(user.getAccount(), 1200.0);
        });
        assertEquals("Insufficient funds, Try again", exception.getMessage());
    }

    @Test
    void withdrawShouldFailIfAmountIsNegative() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            // Försök att ta ut ett negativt belopp
            withdrawalService.withdraw(user.getAccount(), -100.0);
        });
        assertEquals("Withdrawal amount must be positive", exception.getMessage());
    }

    @Test
    void withdrawShouldFailIfUserIsNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            // Försök att göra ett uttag utan användare
            withdrawalService.withdraw(null, 100.0);
        });
        assertEquals("Account cannot be null", exception.getMessage());
    }
}
