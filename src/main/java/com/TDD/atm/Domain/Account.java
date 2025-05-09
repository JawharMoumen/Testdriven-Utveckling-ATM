package com.TDD.atm.Domain;

public class Account {
    private double balance;

    public Account(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    // valfri metod för insättning/uttag om ni vill bygga vidare
    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) {
        if (amount > balance) {
            throw new IllegalArgumentException("Otillräckligt saldo");
        }
        balance -= amount;
    }
}
