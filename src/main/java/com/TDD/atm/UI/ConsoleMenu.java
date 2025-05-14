package com.TDD.atm.UI;

import com.TDD.atm.Domain.User;
import com.TDD.atm.Service.DepositService;
import com.TDD.atm.Service.WithdrawalService;
import com.TDD.atm.Service.BalanceService;
import com.TDD.atm.Service.AuthService;
import com.TDD.atm.Repository.UserRepository;

import java.util.Scanner;

public class ConsoleMenu {
    private final AuthService authService;
    private final DepositService depositService;
    private final WithdrawalService withdrawalService;
    private final BalanceService balanceService;
    private final UserRepository userRepository;
    private final Scanner scanner = new Scanner(System.in);

    public ConsoleMenu(AuthService authService, DepositService depositService, WithdrawalService withdrawalService,
                       BalanceService balanceService, UserRepository userRepository) {
        this.authService = authService;
        this.depositService = depositService;
        this.withdrawalService = withdrawalService;
        this.balanceService = balanceService;
        this.userRepository = userRepository;
    }

    public void start() {
        boolean loggedIn = false;
        int attempts = 0; // Håller koll på antalet försök
        final int maxAttempts = 3;

        while (!loggedIn && attempts < maxAttempts) {
            System.out.println("Välkommen till ATM.");
            System.out.print("Ange kortnummer: ");
            String cardNumber = scanner.nextLine();
            System.out.print("Ange PIN-kod: ");
            String pinCode = scanner.nextLine();

            // Försök logga in
            if (authService.login(cardNumber, pinCode)) {
                loggedIn = true;
                System.out.println("Inloggning lyckades!");
                User user = userRepository.findByCardNumber(cardNumber); // Hämta användaren
                showAccountMenu(user); // Visa huvudmenyn
            } else {
                attempts++;
                if (attempts < maxAttempts) {
                    System.out.println("Felaktigt kortnummer eller PIN. Försök igen.");
                }
            }
        }

        if (!loggedIn) {
            System.out.println("Felaktigt kortnummer eller PIN. Programmet avslutas.");
            System.exit(0); // Avslutar programmet efter max antal försök
        }
    }

    private void showAccountMenu(User user) {
        boolean running = true;
        while (true) {
            System.out.println("\nVälj konto:");
            System.out.println("1. tryck in 1 för Konto 1 " );
            // Här kan du lägga till fler konton om du har flera konton
            System.out.print("Ange kontonummer för att välja ett konto: ");
            int accountChoice = scanner.nextInt();
            scanner.nextLine(); // Rensar buffert

            if (accountChoice != 1) {
                System.out.println("Ogiltigt kontonummer.");
                continue;
            }

            // Visa kontoåtgärder efter att användaren har valt konto
            showAccountActions(user);
        }
    }

    private void showAccountActions(User user) {
        boolean running = true;
        while (running) {
            System.out.println("\nVälj åtgärd:");
            System.out.println("1. Visa saldo");
            System.out.println("2. Sätt in pengar");
            System.out.println("3. Ta ut pengar");
            System.out.println("4. Logga ut");

            int action = scanner.nextInt();
            scanner.nextLine(); // Rensar buffert

            switch (action) {
                case 1:
                    // Visa saldo
                    System.out.println("Ditt saldo är: " + user.getAccount().getBalance());
                    break;
                case 2:
                    // Sätt in pengar
                    System.out.println("Ange belopp att sätta in:");
                    double depositAmount = scanner.nextDouble();
                    scanner.nextLine(); // Rensar buffert
                    depositService.deposit(user.getAccount(), depositAmount);
                    System.out.println("Insättning lyckades. Ditt nya saldo är: " + user.getAccount().getBalance());
                    break;
                case 3:
                    // Ta ut pengar
                    System.out.println("Ange belopp att ta ut:");
                    double withdrawalAmount = scanner.nextDouble();
                    scanner.nextLine(); // Rensar buffert
                    try {
                        withdrawalService.withdraw(user.getAccount(), withdrawalAmount);
                        System.out.println("Uttag lyckades. Ditt nya saldo är: " + user.getAccount().getBalance());
                    } catch (IllegalArgumentException e) {
                        System.out.println("Fel: " + e.getMessage());
                    }
                    break;
                case 4:
                    // Logga ut och avsluta programmet
                    System.out.println("Du har loggats ut.");
                    running = false;// Stänger ner loopen här
                    System.exit(0); // Stänger av programmet
                    break; // Avsluta medan du håller användaren på huvudmenyn
                default:
                    System.out.println("Ogiltigt val, försök igen.");
            }
        }
    }
}
