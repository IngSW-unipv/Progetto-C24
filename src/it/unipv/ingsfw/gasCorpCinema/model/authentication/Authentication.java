package it.unipv.ingsfw.gasCorpCinema.model.authentication;

import it.unipv.ingsfw.gasCorpCinema.model.PersistenceFacade;

public class Authentication {

	private PersistenceFacade persistence = PersistenceFacade.getInstance(); 
	
	public boolean registration(String email, String password) {
		return persistence.registration(email, password);
	}
	
	public String login(String email, String password) {
		return persistence.login(email, password);
	}
	
	public boolean emailExists(String email) {
		return persistence.emailExists(email);
	}
}
