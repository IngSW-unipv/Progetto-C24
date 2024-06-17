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
	private ImageView openEyeImage, closeEyeImage;
	
	@FXML
	private TextField tfEmail, tfPassword;
	
	@FXML
	private PasswordField pfPassword;
	
	@FXML
	private Label errorLabel;
	
	private String password;
	private Stage stage;
	private User user = new User();

	public  void initialize(){
	    tfPassword.setVisible(false);
	    openEyeImage.setVisible(false);
	}

	public void hidePasswordOnAction(KeyEvent keyEvent) {
	    password=pfPassword.getText();
	    tfPassword.setText(password);

	}

	public void showPasswordOnAction(KeyEvent keyEvent) {
	    password=tfPassword.getText();
	    pfPassword.setText(password);
	}

	public void openEyeClicked(MouseEvent mouseEvent) {
	    tfPassword.setVisible(false);
	    openEyeImage.setVisible(false);
	    closeEyeImage.setVisible(true);
	    pfPassword.setVisible(true);

	}

	public void closeEyeClicked(MouseEvent mouseEvent) {
	    tfPassword.setVisible(true);
	    openEyeImage.setVisible(true);
	    closeEyeImage.setVisible(false);
	    pfPassword.setVisible(false);
	}
	@FXML
    public void registerButtonAction() {
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Messaggio di informazione");
		alert.setHeaderText("Registrazione avvenuta con successo");
		alert.setContentText("Clicca il pulsante 'login' qui sotto per poter tornare alla homepage!");
		
        String email = tfEmail.getText();
        String password = pfPassword.getText();
        
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
}

