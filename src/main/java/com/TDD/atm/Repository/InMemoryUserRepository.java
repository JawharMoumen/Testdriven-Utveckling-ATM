package com.TDD.atm.Repository;

import com.TDD.atm.Domain.User;

import java.util.HashMap;
import java.util.Map;

/**
 * InMemoryUserRepository är en enkel implementation av UserRepository.
 * Den använder en HashMap för att lagra användare i minnet under programmets livstid.

 * Denna klass används främst för testning eller enklare tillämpningar.
 * Enligt SOLID följer den:
 * - SRP: Hanterar endast användarlagring.
 * - DIP: Implementerar ett abstrakt gränssnitt (UserRepository).
 */
public class InMemoryUserRepository implements UserRepository {

    // Lagrar användare med kortnummer som nyckel
    private final Map<String, User> users = new HashMap<>();

    /**
     * Hämtar en användare baserat på kortnummer.
     * @param cardNumber Kortnummer som identifierar användaren.
     * @return User-objektet om det finns, annars null.
     */
    @Override
    public User findByCardNumber(String cardNumber) {
        return users.get(cardNumber);
    }

    /**
     * Sparar en användare i minneslagringen.
     * Om kortnumret redan finns, skrivs det över.
     * @param user Användaren som ska sparas.
     */
    @Override
    public void save(User user) {
        users.put(user.getCardNumber(), user);
    }
}
