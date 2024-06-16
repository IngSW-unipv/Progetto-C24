package it.unipv.ingsfw.gasCorpCinema.controller;

import java.io.IOException;
import java.io.FileInputStream;
import java.util.Properties;

import it.unipv.ingsfw.gasCorpCinema.model.User;
import it.unipv.ingsfw.gasCorpCinema.view.UserRegistrationView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class FirstPageController {
	
	@FXML
	private ImageView imageCinema;
	@FXML
    private TextField tf_username;
    @FXML
    private PasswordField field_password;
    @FXML
    private Button button_login;
    @FXML
    private Button button_registrati;
    
    private Stage stage;
	private User user = new User();
	
    @FXML
    public void loginButtonAction() throws Exception {
        
    	Alert alert = new Alert(AlertType.ERROR);
    	alert.setTitle("Errore");
		alert.setHeaderText("C'è stato un errore durante il login");
		alert.setContentText("Credenziali errate, prova ad inserirle nuovamente o se non l'hai già fatto registrati!");
    	
    	String email = tf_username.getText();
        String password = field_password.getText();
        String role = user.login(email, password);
        
        Properties p = new Properties(System.getProperties());
        
        p.load(new FileInputStream("Properties/Properties1"));
        
        if (role != null) {
        	
        	// Ottieni il percorso della vista associata al ruolo
            String viewPath = p.getProperty(role);
            
            if (viewPath != null) {
                changeScene(viewPath, email);
            }
        } else {
            // Mostra un messaggio di errore se il login fallisce
            alert.show();
        }
    }
    
    @FXML
    public void registerButtonAction() throws Exception {
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
    
    public void changeScene(String fxml,String email) throws Exception {
      	
    	FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
    	Parent pane = loader.load();

    	try {
    		
    		Object controller = loader.getController();
    		((AdminViewController)controller).setAdminEmail(email);
    		
    	}catch(ClassCastException e){
    	}
    	
    	try {
    		
    		Object controller = loader.getController();
        	((SelectFilmController)controller).setUserEmail(email);
        	
    	}catch(ClassCastException e){
    	}
    	
    	Stage stage = (Stage) button_login.getScene().getWindow();
    	stage.setScene(new Scene(pane));
    }
}