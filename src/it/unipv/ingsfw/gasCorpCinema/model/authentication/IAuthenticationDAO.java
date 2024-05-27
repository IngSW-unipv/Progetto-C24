package it.unipv.ingsfw.gasCorpCinema.model.authentication;

public interface IAuthenticationDAO {
	
	public boolean registration(String username, String email, String password);
	public boolean login(String email, String password);
//	public Authentication login(String email, String password, String ruolo);
}
