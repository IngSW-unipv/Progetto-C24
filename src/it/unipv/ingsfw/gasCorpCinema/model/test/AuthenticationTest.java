package it.unipv.ingsfw.gasCorpCinema.model.test;

import static org.junit.Assert.*;
import org.junit.Test;
import it.unipv.ingsfw.gasCorpCinema.model.authentication.AuthenticationDAO;


public class AuthenticationTest {

	//Attributes
    private AuthenticationDAO a;


    @Test
    public void testRegistration() {

        //Registriamo manualmente
        a = new AuthenticationDAO();
        a.registration("soufian@gmail.com", "Soufian1");
        
        boolean result = true;
        
        a.registration("giovanni@gmail.com", "Giovanni1@");

        assertTrue(result);
    }
    
    @Test
    public void testEmailExists() {

        a = new AuthenticationDAO();
        a.registration("giovanni@gmail.com", "Giovanni1@");

        boolean result = a.registration("giovanni@gmail.com", "Giovanni1@");
        assertFalse(result);

        // Verifica finale
        assertTrue(a.emailExists("giovanni@gmail.com"));
    }
    
    @Test
    public void testLogin() {

        a = new AuthenticationDAO();
        a.registration("giovanni@gmail.com", "Giovanni1@");
        String expectedRole = "user"; // Supponendo che il ruolo atteso sia "user"
        
        String actualRole = a.login("giovanni@gmail.com","Giovanni1@");

        // Verifica che il login abbia restituito il ruolo atteso
        assertEquals(expectedRole, actualRole);
    }

}
