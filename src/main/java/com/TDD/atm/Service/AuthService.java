package com.TDD.atm.Service;

import com.TDD.atm.Domain.User;
import com.TDD.atm.Repository.UserRepository;

/**
 * AuthService ansvarar för autentisering av användare.
 * Den kontrollerar om ett kortnummer och en PIN-kod matchar en registrerad användare.
 *
 * Enligt SOLID-principerna:
 * - SRP (Single Responsibility Principle): Klassen ansvarar endast för inloggningslogik.
 * - DIP (Dependency Inversion Principle): Beroendet till UserRepository är abstrakt,
 *   vilket gör koden flexibel och lätt att testa.
 */
public class AuthService {

    // Abstrakt beroende till ett användarrepository
    private final UserRepository userRepository;

    /**
     * Konstruktor som tar in ett UserRepository.
     * Detta möjliggör lös koppling och enkel testning (t.ex. med InMemoryUserRepository).
     *
     * @param userRepository Repository som hanterar användarhämtning
     */
    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Försöker logga in en användare baserat på kortnummer och PIN-kod.
     *
     * @param cardNumber Kortnummer som identifierar användaren
     * @param pinCode    PIN-kod som ska verifieras
     * @return true om inloggning lyckas, annars false
     */
    public boolean login(String cardNumber, String pinCode) {
        User user = userRepository.findByCardNumber(cardNumber);

        // Om användaren inte finns, returnera false
        if (user == null) {
            return false;
        }

        // Kontrollera om PIN-koden matchar
        return user.getPinCode().equals(pinCode);
    }
}
