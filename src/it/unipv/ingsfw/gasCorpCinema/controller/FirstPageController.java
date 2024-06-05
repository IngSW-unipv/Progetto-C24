package it.unipv.ingsfw.gasCorpCinema.controller;

import java.io.IOException;

import it.unipv.ingsfw.gasCorpCinema.model.User;
import it.unipv.ingsfw.gasCorpCinema.model.authentication.Authentication;
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
	private User user = new User();
	private Authentication authentication;

    @FXML
    public void handleLoginButtonAction() throws IOException {
        
    	String email = tf_username.getText();
        String password = field_password.getText();
        String role = user.login(email, password);

        if (role != null) {
            if (role.equals("admin")) {
                // Se l'utente è un admin, cambia la scena alla pagina dell'admin
                changeScene("../view/AdminView.fxml",email);
            } else {
                // Altrimenti, cambia la scena alla pagina dell'utente normale
                changeScene("../view/SelectFilm.fxml");
            }
        } else {
            // Mostra un messaggio di errore se il login fallisce
            System.out.println("Login fallito. Controlla le tue credenziali oppure registrati!");
        }    
    }
    
    @FXML
    public void handleRegisterButtonAction() {
        try {
            changeScene("../view/UserRegistration.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // Metodo per cambiare scena senza email
    public void changeScene(String fxml) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        Scene scene = button_login.getScene(); // Riutilizza la scena esistente
        scene.setRoot(pane);
    }
    
    // Metodo per cambiare scena con email
    public void changeScene(String fxml,String email) throws IOException {
//        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
    	 
    	FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
    	Parent pane = loader.load();
    	
     // Ottieni il controller associato
        if (fxml.contains("AdminView")) {
            AdminViewController controller = loader.getController();
            // Passa la mail dell'admin al controller
            controller.setAdminEmail(email);
        }
        
        Stage stage = (Stage) button_login.getScene().getWindow();
        stage.setScene(new Scene(pane));
        
//        Scene scene = button_login.getScene(); // Riutilizza la scena esistente
//        scene.setRoot(pane);
    }
        
}

