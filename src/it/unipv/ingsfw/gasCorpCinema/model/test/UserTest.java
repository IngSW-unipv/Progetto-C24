package it.unipv.ingsfw.gasCorpCinema.model.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.unipv.ingsfw.gasCorpCinema.model.authentication.AuthenticationSingleton;

public class UserTest {
	private AuthenticationSingleton authentication;
	
	@BeforeEach
    public void setUp() {

        authentication=AuthenticationSingleton.getInstance();
    }

    @Test
    public void testValidEmails() {
        assertTrue(authentication.emailValidate("email@example.com"));
        assertTrue(authentication.emailValidate("firstname.lastname@example.com"));
        assertTrue(authentication.emailValidate("12345+@example.com"));
    }
    
    @Test
    public void testInvalidEmails() {
        assertFalse(authentication.emailValidate("plainaddress")); 
        assertFalse(authentication.emailValidate("email@@invalid")); 
        assertFalse(authentication.emailValidate(" ")); 
    
    }
}
