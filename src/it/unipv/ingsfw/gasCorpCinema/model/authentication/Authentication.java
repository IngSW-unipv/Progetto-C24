package it.unipv.ingsfw.gasCorpCinema.model.authentication;

public class Authentication {

	private String email;
	private String password;
	private String ruolo;
	
	public Authentication(String email, String password, String ruolo) {
		this.email = email;
		this.password = password;
		this.ruolo = ruolo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRuolo() {
		return ruolo;
	}

	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}
}
