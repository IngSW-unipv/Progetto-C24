package it.unipv.ingsfw.gasCorpCinema.controller;

import java.io.IOException;
import it.unipv.ingsfw.gasCorpCinema.model.User;
import it.unipv.ingsfw.gasCorpCinema.view.FirstPageView;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UserRegistrationController {
	@FXML
	private Button button_register;
	
	@FXML 
	private Button button_log_in;
	
	@FXML
	private TextField tf_username, tf_email;
	
	@FXML
	private PasswordField pf_password;
	
	@FXML
	private Label errorLabel;
	
	private Stage stage;
	private User user = new User();

	@FXML
    public void registerButtonAction() {
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Messaggio di informazione");
		alert.setHeaderText("Registrazione avvenuta con successo");
		alert.setContentText("Clicca il pulsante 'login' qui sotto per poter tornare alla homepage!");
		
        String email = tf_email.getText();
        String password = pf_password.getText();
        
        if (email.isEmpty() || password.isEmpty()) {
            displayError("Tutti i campi sono obbligatori.");
            return;
        }

        // Controllo se l'email esiste già nel database
        if (user.emailExists(email)) {
            displayError("Email già esistente. Usa un'altra email.");
            return;
        }

        if (user.registration(email, password)) {
            alert.show();
            // Puoi anche mostrare un messaggio all'utente o cambiare scena
        } else {
            displayError("Registrazione fallita.");
        }
    }

    @FXML
    public void loginButtonAction() throws Exception {  
    	try {
        	Stage currentStage = (Stage) button_log_in.getScene().getWindow();
        	
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
}
