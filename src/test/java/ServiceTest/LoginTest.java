package ServiceTest;

import com.TDD.atm.Service.AuthService;
import com.TDD.atm.Repository.InMemoryUserRepository;
import com.TDD.atm.Domain.User;
import com.TDD.atm.Repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

// Testklass för att verifiera inloggningslogik i AuthService
class AuthServiceTest {

    private AuthService authService;
    private UserRepository userRepository;

    // Denna metod körs innan varje test
    @BeforeEach
    void setUp() {
        // Skapar en test-databas (i minnet)
        userRepository = new InMemoryUserRepository();

        // Skapar autentiseringstjänst med test-databasen
        authService = new AuthService(userRepository);

        // Lägger till en testanvändare med kortnummer och PIN-kod
        userRepository.save(new User("1234567890", "1234"));
    }

    // Testar att inloggning fungerar med korrekt kortnummer och PIN-kod
    @Test
    void shouldLoginSuccessfullyWithValidCardNumberAndPin() {
        boolean result = authService.login("1234567890", "1234");
        assertTrue(result); // Förväntar att inloggning ska lyckas
    }

    // Testar att inloggning misslyckas om PIN-koden är fel
    @Test
    void shouldFailLoginWithInvalidPin() {
        boolean result = authService.login("1234567890", "9999");
        assertFalse(result); // Förväntar att inloggning ska misslyckas
    }

    // Testar att inloggning misslyckas om kortnumret är fel
    @Test
    void shouldFailLoginWithInvalidCardNumber() {
        boolean result = authService.login("0000000000", "1234");
        assertFalse(result); // Förväntar att inloggning ska misslyckas
    }
}
