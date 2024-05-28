package it.unipv.ingsfw.gasCorpCinema.model;

import it.unipv.ingsfw.gasCorpCinema.model.authentication.AuthenticationDAO;
import it.unipv.ingsfw.gasCorpCinema.model.authentication.IAuthenticationDAO;

public class User {
	
	private IAuthenticationDAO authenticationDAO;
	
	public User() {
		authenticationDAO = new AuthenticationDAO();
	}
	
	public boolean registration(String username, String email, String password) {
		return authenticationDAO.registration(username, email, password);
	}
	
	public String login(String email, String password) {
		return authenticationDAO.login(email, password);
	}
}
