package com.TDD.atm.Service;

import com.TDD.atm.Domain.Account;

public class DepositService {

    public void deposit(Account account, double amount) {
        if (account == null) {
            throw new IllegalArgumentException("Account cannot be null");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }

        account.deposit(amount);
    }
}
