package it.unipv.ingsfw.gasCorpCinema.controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FirstPageController {
	@FXML
    private TextField tf_username;

    @FXML
    private PasswordField field_password;

    @FXML
    private Button button_login;

    @FXML
    private Button button_registrati;
    
    private Stage stage;
	private Scene scene;
	private Parent root;

    @FXML
    public void handleLoginButtonAction() throws IOException {
        
    	String email = tf_username.getText();
        String password = field_password.getText();

        if (authenticate(email, password)) {
            // Login riuscito, puoi fare qualcosa come cambiare la scena o mostrare un messaggio
            System.out.println("Login riuscito. Benvenuto!");
        } else {
            // Mostra un messaggio di errore
            System.out.println("Login fallito. Controlla le tue credenziali.");
        }
    }

    @FXML
    public void handleRegisterButtonAction() {
        try {
            changeScene("/View/Register.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

 //   public boolean authenticate(String email, String password) {
 //      
 //   }

    public void changeScene(String fxml) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        Stage stage = (Stage) button_login.getScene().getWindow();
        stage.setScene(new Scene(pane));
    }
}

