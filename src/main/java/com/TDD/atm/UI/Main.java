package com.TDD.atm.UI;

import com.TDD.atm.Domain.Account;
import com.TDD.atm.Domain.User;
import com.TDD.atm.Repository.InMemoryUserRepository;
import com.TDD.atm.Repository.UserRepository;
import com.TDD.atm.Service.*;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        // Skapa konton
        Account account1 = new Account(10000.0);
        Account account2 = new Account(13000.0);

        // Skapa användare och tilldela konton
        User user1 = new User("11111", "2222", Arrays.asList(account1)); // En användare med ett konto
        User user2 = new User("00000", "1111", Arrays.asList(account2)); // En annan användare med ett konto

        // Spara användarna i repository
        UserRepository userRepository = new InMemoryUserRepository();
        userRepository.save(user1);
        userRepository.save(user2);

        // Tjänster
        AuthService authService = new AuthService(userRepository);
        DepositService depositService = new DepositService();
        WithdrawalService withdrawalService = new WithdrawalService();
        BalanceService balanceService = new BalanceService();

        // Starta meny
        ConsoleMenu menu = new ConsoleMenu(
                authService,
                depositService,
                withdrawalService,
                balanceService,
                userRepository // Skicka in userRepository här
        );
        menu.start();
    }
}
