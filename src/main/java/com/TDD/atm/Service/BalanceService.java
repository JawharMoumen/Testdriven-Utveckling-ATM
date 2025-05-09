package com.TDD.atm.Service;

import com.TDD.atm.Domain.User;

/**
 * BalanceService ansvarar för att tillhandahålla kontobalans för en användare.
 * Följer SRP (Single Responsibility Principle) genom att endast hantera balanslogik.
 */
public class BalanceService {

    /**
     * Hämtar kontosaldot för en given användare.
     */
    public double getBalance(User user) {
        if (user == null || user.getAccount() == null) {
            throw new IllegalArgumentException("User or account must not be null");
        }

        return user.getAccount().getBalance();
    }
}
