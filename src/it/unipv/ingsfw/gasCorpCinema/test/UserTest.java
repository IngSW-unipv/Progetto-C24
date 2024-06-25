package it.unipv.ingsfw.gasCorpCinema.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import it.unipv.ingsfw.gasCorpCinema.model.Validation;

public class UserTest {

	private Validation validation;
	@Before
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
