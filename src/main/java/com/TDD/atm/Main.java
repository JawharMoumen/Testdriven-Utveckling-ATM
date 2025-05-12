package com.TDD.atm;

import com.TDD.atm.Domain.Account;
import com.TDD.atm.Service.WithdrawalService;
import com.TDD.atm.UI.ConsoleMenu;

public class Main {
    public static void main(String[] args) {

        Account account = new Account(1000.0);

        WithdrawalService withdrawalService = new WithdrawalService();
        
        ConsoleMenu menu = new ConsoleMenu(account, withdrawalService);
        menu.start();;
    }
}
