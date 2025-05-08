package com.TDD.atm.Repository;
import com.TDD.atm.Domain.User;

/**
 * UserRepository är ett gränssnitt (interface) som definierar kontraktet
 * för hur användardata ska hämtas och sparas.

 * Det möjliggör flera olika implementationer (t.ex. in-memory, databas, fil),
 * vilket följer SOLID-principerna:

 * - DIP (Dependency Inversion Principle): Tjänster som AuthService är beroende av detta gränssnitt,
 *   inte av en konkret implementation.

 * - ISP (Interface Segregation Principle): Gränssnittet är smalt och fokuserat endast på användardata.
 */
public interface UserRepository {

    /**
     * Hämtar en användare baserat på kortnummer.
     * @param cardNumber Kortnummer som identifierar användaren.
     * @return User-objektet om det finns, annars null.
     */
    User findByCardNumber(String cardNumber);

    /**
     * Sparar en användare i lagringen.
     * @param user Användaren som ska sparas.
     */
    void save(User user);
}
