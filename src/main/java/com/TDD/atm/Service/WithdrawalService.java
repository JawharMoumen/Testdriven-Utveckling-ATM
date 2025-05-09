package com.TDD.atm.Service;

import com.TDD.atm.Domain.User;

public class WithdrawalService {

    public void withdraw(User user, double amount) {
        if (user == null || user.getAccount() == null) {
            throw new IllegalArgumentException("User or account cannot be null");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }
        if (user.getAccount().getBalance() < amount) {
            throw new IllegalArgumentException("Insufficient funds");
        }

        // Annars, gör uttaget
        user.getAccount().withdraw(amount);
    }
}
