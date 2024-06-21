package it.unipv.ingsfw.gasCorpCinema.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import it.unipv.ingsfw.gasCorpCinema.model.authentication.AuthenticationDAO;
import it.unipv.ingsfw.gasCorpCinema.model.authentication.IAuthenticationDAO;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class User {
	
	private IAuthenticationDAO authenticationDAO;
	
	public User() {
		authenticationDAO = new AuthenticationDAO();
	}
	
	public boolean registration(String email, String password) {
		return authenticationDAO.registration(email, password);
	}
	
	public String login(String email, String password) {
		return authenticationDAO.login(email, password);
	}
	
	public boolean emailExists(String email) {
		return authenticationDAO.emailExists(email);
	}
	
	public boolean emailValidate(String email) {
		
    	Pattern p = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
        Matcher m = p.matcher(email);

        if (m.matches()) {
            return true;
        } else {
//            Alert alert = new Alert(AlertType.WARNING);
//            alert.setTitle("Email non valida");
//            alert.setHeaderText("Email non valida");
//            alert.setContentText("Per favore, inserisci un'email valida.");
//            alert.showAndWait();
            return false;
		}
	}
	
	public boolean passwordValidate(String password) {
	    Pattern p = Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,18}$");
	    Matcher m = p.matcher(password);
	    
	    if (m.matches()) {
	        return true;
	        
	    } else {
	        Alert alert = new Alert(AlertType.WARNING);
	        alert.setTitle("La password deve contenere almeno:");
	        alert.setHeaderText(null);
	        alert.setContentText("Un numero, una lettera minuscola e una maiuscola,"
	        		+ " un carattere speciale (@#$%) e deve essere lunga tra 6 e 18 caratteri.");
	        alert.showAndWait();
	        return false;
	    }
	}
}
