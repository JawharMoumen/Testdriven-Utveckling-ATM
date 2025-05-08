package com.TDD.atm.Domain;
/**
 * User är en enkel domänklass (modell) som representerar en bankanvändare.
 * Klassen ansvarar endast för att hålla data – kortnummer och PIN-kod.
 * Enligt SOLID har klassen ett enda syfte.
 */
public class User {
    // Kortnummer kopplat till användaren (ex. ett bankkort)
    private final String cardNumber;

    // Användarens PIN-kod
    private final String pinCode;

    /**
     * Konstruktor som skapar en användare med ett kortnummer och en PIN-kod.
     * @param cardNumber användarens kortnummer
     * @param pinCode användarens PIN-kod
     */
    public User(String cardNumber, String pinCode) {
        this.cardNumber = cardNumber;
        this.pinCode = pinCode;
    }

    /**
     * Hämtar kortnumret för användaren.
     * @return kortnummer som sträng
     */
    public String getCardNumber() {
        return cardNumber;
    }

    /**
     * Hämtar användarens PIN-kod.
     * @return PIN-kod som sträng
     */
    public String getPinCode() {
        return pinCode;
    }
}
