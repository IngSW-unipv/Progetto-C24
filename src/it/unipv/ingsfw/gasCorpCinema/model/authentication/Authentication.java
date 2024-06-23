package it.unipv.ingsfw.gasCorpCinema.model.authentication;

public class Authentication {

	private IAuthenticationDAO authenticationDAO;
	
	public boolean registration(String email, String password) {
		return authenticationDAO.registration(email, password);
	}
	
	public String login(String email, String password) {
		return authenticationDAO.login(email, password);
	}
	
	public boolean emailExists(String email) {
		return authenticationDAO.emailExists(email);
	}

	
}
