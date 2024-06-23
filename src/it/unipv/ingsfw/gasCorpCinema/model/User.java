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
	
	//****************
	public String getRoleByEmail(String email) {
		return authenticationDAO.getRoleByEmail(email);
	}
	
	public boolean emailExists(String email) {
		return authenticationDAO.emailExists(email);
	}
	
public boolean emailValidate(String email) {
		
    	Pattern p = 
    			Pattern.compile("^[\\w!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
       //il regex(o espressione regolare) ammette caratteri alfa numerici e un qualsisi simbolo speciale
    	//seguito da @ e altri caratteri alfanumerici e il simbolo -, inoltre la regexc deve terminare con 
    	//un punto seguito da almeno 2 lettere
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
