package com.TDD.atm.UI;

import com.TDD.atm.Domain.Account;
import com.TDD.atm.Service.WithdrawalService;

import java.util.Scanner;

public class ConsoleMenu {
    private final Scanner scanner;
    private final Account account;
    private final WithdrawalService withdrawalService;

    public ConsoleMenu(Account account, WithdrawalService withdrawalService) {
        this.account = account;
        this.withdrawalService = withdrawalService;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        boolean running = true;

        while (running) {
            printMenu();
            int choice = readChoice();

            switch (choice) {
                case 1 -> showBalance();
                case 2 -> makeDeposit();
                case 3 -> makeWithdrawal();
                case 4 -> {
                    System.out.println("Tack för att du använde ATM. Hej då!");
                    running = false;
                }
                default -> System.out.println("Ogiltigt val. Försök igen.");
            }
        }
    }

    private void printMenu() {
        System.out.println("\n--- ATM MENY ---");
        System.out.println("1. Visa saldo");
        System.out.println("2. Sätt in pengar");
        System.out.println("3. Ta ut pengar");
        System.out.println("4. Avsluta");
        System.out.print("Ditt val: ");
    }

    private int readChoice() {
        while (!scanner.hasNextInt()) {
            System.out.println("Ange en siffra.");
            scanner.next(); // rensar felaktig input
        }
        return scanner.nextInt();
    }

    private void showBalance() {
        System.out.printf("Ditt saldo är: %.2f kr%n", account.getBalance());
    }

    private void makeDeposit() {
        System.out.print("Ange belopp att sätta in: ");
        double amount = scanner.nextDouble();
        account.deposit(amount);
        System.out.printf("%.2f kr har satts in.%n", amount);
        // Visa den uppdaterade balansen efter insättningen
        showBalance();
    }

    private void makeWithdrawal() {
        System.out.print("Ange belopp att ta ut: ");
        double amount = scanner.nextDouble();
        try {
            withdrawalService.withdraw(account, amount);  // Här skickar vi in 'account'
            System.out.printf("%.2f kr har tagits ut.%n", amount);
            // Visa den uppdaterade balansen efter uttaget
            showBalance();
        } catch (IllegalArgumentException e) {
            System.out.println("Fel: " + e.getMessage());
        }
    }
}
