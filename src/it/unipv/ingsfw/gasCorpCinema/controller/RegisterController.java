package it.unipv.ingsfw.gasCorpCinema.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import it.unipv.ingsfw.gasCorpCinema.model.Admin;
import it.unipv.ingsfw.gasCorpCinema.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegisterController {
	@FXML
	private Button button_register;
	
	@FXML 
	private Button button_log_in;
	
	@FXML
	private TextField tf_username, tf_email;
	
	@FXML
	private PasswordField pf_password;
	
	private User user = new User();

	@FXML
    private void handleRegisterButtonAction() {
		String username = tf_username.getText();
        String email = tf_email.getText();
        String password = pf_password.getText();

        if (user.registration(username, email, password)) {
            System.out.println("Registrazione avvenuta con successo.");
            // Puoi anche mostrare un messaggio all'utente o cambiare scena
        } else {
            System.out.println("Registrazione fallita.");
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

    public boolean registerUser(String username, String email, String password) {
        String url = "jdbc:mysql://localhost:3306/cinema";
        String user = "root";
        String pass = "password";
        String query = "INSERT INTO autenticazione (email, password, ruolo) VALUES (?, ?, 'utente')";
        
        try (Connection conn = DriverManager.getConnection(url, user, pass);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, email);
            stmt.setString(2, password);
            int rowsInserted = stmt.executeUpdate();
            
            return rowsInserted > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private void changeScene(String fxml) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        Stage stage = (Stage) button_register.getScene().getWindow();
        stage.setScene(new Scene(pane));
    }
}
