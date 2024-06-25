package it.unipv.ingsfw.gasCorpCinema.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import it.unipv.ingsfw.gasCorpCinema.model.Validation;

public class ValidationTest {


	@Before
    public void setUp() {
    }

    @Test
    public void testValidEmails() {
        assertTrue(Validation.emailValidate("email@example.com"));
        assertTrue(Validation.emailValidate("firstname.lastname@example.com"));
        assertTrue(Validation.emailValidate("12345+@example.com"));
    }
    
    @Test
    public void testInvalidEmails() {
        assertFalse(Validation.emailValidate("plainaddress")); 
        assertFalse(Validation.emailValidate("email@@invalid")); 
        assertFalse(Validation.emailValidate(" ")); 
    }
    
    @Test
    public void testValidName() {
        assertTrue(Validation.nameValidate("Mario"));
    }

    @Test
    public void testInvalidName() {
        assertFalse(Validation.nameValidate("12345678"));
        assertFalse(Validation.nameValidate("@@Mario"));
    }
    
    @Test
    public void testValidCardNumber() {
        assertTrue(Validation.cardNumberValidate("1234567812345678"));
    }

    @Test
    public void testInvalidCardNumber() {
        assertFalse(Validation.cardNumberValidate("12345678"));
        assertFalse(Validation.cardNumberValidate("abcdefgh12345678"));
    }
    
    @Test
    public void testValidCvv() {
        assertTrue(Validation.cvvValidate("123"));
    }

    @Test
    public void testInvalidCvv() {
        assertFalse(Validation.cvvValidate("1234"));
        assertFalse(Validation.cvvValidate("abcd"));
    }
    
}
