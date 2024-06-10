package it.unipv.ingsfw.gasCorpCinema.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import it.unipv.ingsfw.gasCorpCinema.model.Admin;
import it.unipv.ingsfw.gasCorpCinema.model.User;
import it.unipv.ingsfw.gasCorpCinema.model.authentication.AuthenticationDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
	
	private User user = new User();

//Voglio fare che quando la registrazione avviene con successo faccio comparire un alert
//a schermo, mentre nei casi in cui sia andato male qualcosa usare il displayError

	@FXML
    private void handleRegisterButtonAction() {
		
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
    public void handleLoginButtonAction() {  
        try {
            changeScene("../view/FirstPage.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void changeScene(String fxml) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        Stage stage = (Stage) button_register.getScene().getWindow();
        stage.setScene(new Scene(pane));
    }
    
    public void displayError(String errormessage) {
    	errorLabel.setText(errormessage);
    }
}
