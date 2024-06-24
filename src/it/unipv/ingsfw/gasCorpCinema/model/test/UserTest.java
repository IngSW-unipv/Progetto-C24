package it.unipv.ingsfw.gasCorpCinema.model.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unipv.ingsfw.gasCorpCinema.model.role.User;
import it.unipv.ingsfw.gasCorpCinema.model.Validation;

public class UserTest {

	private Validation validation;
	@BeforeEach
    public void setUp() {

    }

    @Test
    public void testValidEmails() {
        assertTrue(validation.emailValidate("email@example.com"));
        assertTrue(validation.emailValidate("firstname.lastname@example.com"));
        assertTrue(validation.emailValidate("12345+@example.com"));
    }
    
    @Test
    public void testInvalidEmails() {
        assertFalse(validation.emailValidate("plainaddress")); 
        assertFalse(validation.emailValidate("email@@invalid")); 
        assertFalse(validation.emailValidate(" ")); 
    
    }
}
