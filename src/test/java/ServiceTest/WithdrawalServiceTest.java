package ServiceTest;

import com.TDD.atm.Domain.Account;
import com.TDD.atm.Domain.User;
import com.TDD.atm.Service.WithdrawalService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

//parprogrammeirng

import static org.junit.Assert.assertEquals;

public class WithdrawalServiceTest {
    private WithdrawalService withdrawalService ;
    private User user;
    @BeforeEach
    public void setUp() {
      user = new User("123456","1111",new Account(1000.0));
      withdrawalService  = new WithdrawalService();
    }
    @Test
    void  WithdrawAmountshoudwork(){
        withdrawalService.withdraw(user,200.0);
        Assertions.assertEquals(800.0, user.getAccount().getBalance());
    }
    @Test
    void withdrawShouldReduceBalanceByAmount() {
        withdrawalService.withdraw(user, 200.0);
        assertEquals(800.0, user.getAccount().getBalance(),0.001);
    }

    @Test
    void withdrawShouldFailIfNotEnoughMoney() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            withdrawalService.withdraw(user, 1200.0);
        });
        assertEquals("Insufficient funds", exception.getMessage());
    }

    @Test
    void withdrawShouldFailIfAmountIsNegative() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            withdrawalService.withdraw(user, -100.0);
        });
        assertEquals("Withdrawal amount must be positive", exception.getMessage());
    }

    @Test
    void withdrawShouldFailIfUserIsNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            withdrawalService.withdraw(null, 100.0);
        });
        assertEquals("User or account cannot be null", exception.getMessage());
    }

}
