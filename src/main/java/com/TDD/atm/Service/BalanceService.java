package com.TDD.atm.Service;

import com.TDD.atm.Domain.User;

/**
 * BalanceService ansvarar för att tillhandahålla kontobalans för en användare.
 * Följer SRP (Single Responsibility Principle) genom att endast hantera balanslogik.
 */
public class BalanceService {

    /**
     * Hämtar kontosaldot för en given användare.
     * Här hämtar vi det första kontot i listan för användaren.
     * För att stödja flera konton kan du lägga till logik för att välja ett specifikt konto om användaren har mer än ett.
     */
    public double getBalance(User user) {
        if (user == null || user.getAccounts() == null || user.getAccounts().isEmpty()) {
            throw new IllegalArgumentException("User or accounts must not be null or empty");
        }

        // Här hämtar vi det första kontot i listan för nuvarande användare.
        // Du kan välja att hantera flera konton om det behövs.
        return user.getAccounts().get(0).getBalance(); // Hämta saldot från det första kontot
    }
}
