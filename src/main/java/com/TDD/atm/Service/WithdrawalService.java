package com.TDD.atm.Service;

import com.TDD.atm.Domain.Account;
import com.TDD.atm.Domain.User;

public class WithdrawalService {

    public void withdraw(Account account, double amount) {
        if (account == null) {
            throw new IllegalArgumentException("Account cannot be null");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }
        if (account.getBalance() < amount) {
            throw new IllegalArgumentException("Insufficient funds, Try again");
        }

        account.withdraw(amount);
    }
}
