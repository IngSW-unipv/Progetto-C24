package it.unipv.ingsfw.gasCorpCinema.model.authentication;

public class User {
	
	private IAuthenticationDAO authenticationDAO;
	
	public User() {
		authenticationDAO = new AuthenticationDAO();
	}
	
	public boolean registration(String username, String email, String password) {
		return authenticationDAO.registration(username, email, password);
	}
	
	public boolean login(String email, String password) {
		return authenticationDAO.login(email, password);
	}
}
