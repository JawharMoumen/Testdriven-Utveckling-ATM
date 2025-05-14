package com.TDD.atm.Tests;

import com.TDD.atm.Domain.Account;
import com.TDD.atm.Service.DepositService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DepositServiceTest {

    @Test
    void depositIncreaseBalance() {
        Account account = new Account(1000);
        DepositService service = new DepositService();

        service.deposit(account, 500);

        assertEquals(1500, account.getBalance());
    }

    @Test
    void ThrowExceptionForNegativeAmount() {
        Account account = new Account(1000);
        DepositService service = new DepositService();

        assertThrows(IllegalArgumentException.class, () -> service.deposit(account, -100));
    }

    @Test
    void ThrowExceptionIfAccountIsNull() {
        DepositService service = new DepositService();

        assertThrows(IllegalArgumentException.class, () -> service.deposit(null, 100));
    }
}
