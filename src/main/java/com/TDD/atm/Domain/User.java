package com.TDD.atm.Domain;

import java.util.List;

public class User {
    private final String cardNumber;
    private final String pinCode;
    private final List<Account> accounts; // Lista för flera konton

    public User(String cardNumber, String pinCode, List<Account> accounts) {
        this.cardNumber = cardNumber;
        this.pinCode = pinCode;
        this.accounts = accounts;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getPinCode() {
        return pinCode;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public Account getAccount() {
        return accounts.getFirst();
    }
}
