package it.unipv.ingsfw.gasCorpCinema.controller;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import it.unipv.ingsfw.gasCorpCinema.model.User;
import it.unipv.ingsfw.gasCorpCinema.view.FirstPageView;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class UserRegistrationController {
	@FXML
	private Button buttonRegistrati;
	
	@FXML 
	private Button buttonLogin;
	
	@FXML
	private ImageView openEyeImage1, openEyeImage2, closeEyeImage1, closeEyeImage2;
	
	@FXML
	private TextField tfEmail, tfPassword, tfConfirmPassword;
	
	@FXML
	private PasswordField pfPassword, pfConfirmPassword;
	
	@FXML
	private Label errorLabel;
	
	private String password, confirmPassword;
	private Stage stage;
	private User user = new User();

	public  void initialize(){
	    tfPassword.setVisible(false);
	    openEyeImage1.setVisible(false);
	    
	    tfConfirmPassword.setVisible(false);
	    openEyeImage2.setVisible(false);
	}

	public void hidePasswordOnAction(KeyEvent keyEvent) {
	    password=pfPassword.getText();
	    tfPassword.setText(password);
	}
	
	public void hidePasswordOnActionSecond(KeyEvent keyEvent) {   
	    confirmPassword=pfConfirmPassword.getText();
	    tfConfirmPassword.setText(confirmPassword);
	}

	public void showPasswordOnAction(KeyEvent keyEvent) {
	    password=tfPassword.getText();
	    pfPassword.setText(password);
	}
	
	public void showPasswordOnActionSecond(KeyEvent keyEvent) {  
	    confirmPassword=tfConfirmPassword.getText();
	    pfConfirmPassword.setText(confirmPassword);
	}

	public void firstOpenEyeClicked(MouseEvent mouseEvent) {
	    tfPassword.setVisible(false);
	    openEyeImage1.setVisible(false);
	    closeEyeImage1.setVisible(true);
	    pfPassword.setVisible(true);
	}
	
	public void secondOpenEyeClicked(MouseEvent mouseEvent) {	    
	    tfConfirmPassword.setVisible(false);
	    openEyeImage2.setVisible(false);
	    closeEyeImage2.setVisible(true);
	    pfConfirmPassword.setVisible(true);

	}

	public void firstCloseEyeClicked(MouseEvent mouseEvent) {
	    tfPassword.setVisible(true);
	    openEyeImage1.setVisible(true);
	    closeEyeImage1.setVisible(false);
	    pfPassword.setVisible(false);
	}
	
	public void secondCloseEyeClicked(MouseEvent mouseEvent) {
	    tfConfirmPassword.setVisible(true);
	    openEyeImage2.setVisible(true);
	    closeEyeImage2.setVisible(false);
	    pfConfirmPassword.setVisible(false);
	}
	
	@FXML
    public void registerButtonAction() {
		
		try {
	
			String email = tfEmail.getText();
			String password = pfPassword.getText();
			String confirmPassword = pfConfirmPassword.getText();
        
			if (!user.emailValidate(email)) {
				displayError("Inserisci un email valida.");
		        return;
		    }
			
			if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
				displayError("Tutti i campi sono obbligatori.");
				return;
			}
			
			if (email.length() > 30) {
	            displayError("L'email è troppo lunga. Deve essere al massimo di 30 caratteri.");
	            return;
	        }

	        if (password.length() > 12) {
	            displayError("La password è troppo lunga. Deve essere al massimo di 12 caratteri.");
	            return;
	        }

			// Controllo se l'email esiste già nel database
			if (user.emailExists(email)) {
				displayError("Email già esistente. Usa un'altra email.");
            	return;
			}
        
			// Controlla se le password coincidono
			if (!password.equals(confirmPassword)) {
				displayError("Le password che hai inserito non coincidono");
				return;
			}

			if (user.registration(email, password)) {
				showAlertSuccess("Messaggio di informazione", "Registrazione avvenuta con successo", "Clicca il pulsante 'login' qui sotto per poter tornare alla homepage!");
				// Puoi anche mostrare un messaggio all'utente o cambiare scena
			} else {
				displayError("Registrazione fallita.");
			}
	    } catch (Exception e) {
	        displayError("Registrazione fallita.");
	        e.printStackTrace(); // Stampa lo stack trace per il debug
	    }
	}
	
    @FXML
    public void loginButtonAction() throws Exception {  
    	try {
        	Stage currentStage = (Stage) buttonLogin.getScene().getWindow();
        	
        	stage = new Stage();
			FirstPageView f = new FirstPageView();
			f.start(stage);	
			
			currentStage.close();
			
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void displayError(String errormessage) {
    	errorLabel.setText(errormessage);
    }
    
    private void showAlertSuccess(String title, String header, String content) {
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
        alert.show();
    }
}

