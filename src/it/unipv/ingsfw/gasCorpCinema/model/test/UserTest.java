package it.unipv.ingsfw.gasCorpCinema.model.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.unipv.ingsfw.gasCorpCinema.model.User;

public class UserTest {
	private User user;
	
	@BeforeEach
    public void setUp() {
        user = new User();
    }

    @Test
    public void testValidEmails() {
        assertTrue(user.emailValidate("email@example.com"));
        assertTrue(user.emailValidate("firstname.lastname@example.com"));
        assertTrue(user.emailValidate("12345+@example.com"));
    }
    
    @Test
    public void testInvalidEmails() {
        assertFalse(user.emailValidate("plainaddress")); 
        assertFalse(user.emailValidate("email@@invalid")); 
        assertFalse(user.emailValidate(" ")); 
    
    }
}
