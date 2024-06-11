package it.unipv.ingsfw.gasCorpCinema.controller;

import java.io.IOException;

import it.unipv.ingsfw.gasCorpCinema.model.User;
import it.unipv.ingsfw.gasCorpCinema.model.authentication.Authentication;
import it.unipv.ingsfw.gasCorpCinema.view.FirstPageView;
import it.unipv.ingsfw.gasCorpCinema.view.UserRegistrationView;
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
                changeSceneUser("../view/SelectFilm.fxml", email);
            }
        } else {
            // Mostra un messaggio di errore se il login fallisce
            System.out.println("Login fallito. Controlla le tue credenziali oppure registrati!");
        }    
    }
    
    @FXML
    public void handleRegisterButtonAction() throws Exception {
        try {
        	Stage currentStage = (Stage) button_registrati.getScene().getWindow();
        	
        	stage = new Stage();
			UserRegistrationView u = new UserRegistrationView();
			u.start(stage);	
			
			currentStage.close();
			
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // Metodo per cambiare scena con email per l'utente
    public void changeSceneUser(String fxml,String email) throws IOException {
  	
    	FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
    	Parent pane = loader.load();
  	
   // Ottieni il controller associato
      if (fxml.contains("SelectFilmView")) {
          AdminViewController controller = loader.getController();
          // Passa la mail dell'admin al controller
          controller.setAdminEmail(email);
      }
      
      Stage stage = (Stage) button_login.getScene().getWindow();
      stage.setScene(new Scene(pane));
  }
    
    // Metodo per cambiare scena con email per l'admin
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
    }
        
}

