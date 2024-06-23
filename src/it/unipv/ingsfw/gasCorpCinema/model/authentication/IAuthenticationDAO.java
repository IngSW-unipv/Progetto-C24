package it.unipv.ingsfw.gasCorpCinema.model.authentication;

public interface IAuthenticationDAO {
	
	public boolean registration(String email, String password);
	public String login(String email, String password);
	public boolean emailExists(String email);
	public String getRoleByEmail(String email);
}
