package it.unipv.ingsfw.gasCorpCinema.model.authentication;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AuthenticationSingleton {

	private static AuthenticationSingleton instance;	
	private String email;
	private String role;
//	private Authentication() {
//		register=Register.getInstance();
//	}
	
	private AuthenticationSingleton() {
		super();
	}
	
	public static AuthenticationSingleton getInstance() {
		if (instance==null) {
			instance = new AuthenticationSingleton();
		}
		return instance;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
public boolean emailValidate(String email) {
		
    	Pattern p = 
    			Pattern.compile("^[\\w!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
       //il regex(o espressione regolare) ammette caratteri alfa numerici e un qualsisi simbolo speciale
    	//seguito da @ e altri caratteri alfanumerici e il simbolo -, inoltre la regexc deve terminare con 
    	//un punto seguito da almeno 2 lettere
    	Matcher m = p.matcher(email);
    	return m.matches();
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
	
	public boolean nameValidate(String input) {
		String regex = "^[A-Z][a-zA-Z' -]*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
	}
	
	public boolean cardNumberValidate(String input) {
		String regex = "^\\d{16}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
	}
	
	public boolean dateValidate(LocalDate expirationDate) {
		
		if(expirationDate==null) {
			return false;
		}else {
			LocalDate today= LocalDate.now();
			boolean b = expirationDate.isAfter(today);
			return b;
		}
	}
	
	public boolean cvvValidate(String input) {
		String regex = "^\\d{3}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
	}
	
}
