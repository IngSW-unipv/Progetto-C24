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
	private ImageView openEyeImage1, openEyeImage2, closeEyeImage1, closeEyeImage2;
	
	@FXML
	private TextField tfEmail, tfPassword1, tfPassword2;
	
	@FXML
	private PasswordField pfPassword1, pfPassword2;
	
	@FXML
	private Label errorLabel;
	
	private String password1, password2;
	private Stage stage;
	private User user = new User();

	public  void initialize(){
	    tfPassword1.setVisible(false);
	    openEyeImage1.setVisible(false);
	    
	    tfPassword2.setVisible(false);
	    openEyeImage2.setVisible(false);
	}

	public void hidePasswordOnAction(KeyEvent keyEvent) {
	    password1=pfPassword1.getText();
	    tfPassword1.setText(password1);
	    
	    password2=pfPassword2.getText();
	    tfPassword2.setText(password2);
	}
	
	public void hidePasswordOnActionSecond(KeyEvent keyEvent) {
	    password1=pfPassword1.getText();
	    tfPassword1.setText(password1);
	    
	    password2=pfPassword2.getText();
	    tfPassword2.setText(password2);
	}

	public void showPasswordOnAction(KeyEvent keyEvent) {
	    password2=tfPassword2.getText();
	    pfPassword2.setText(password2);
	}
	
	public void showPasswordOnActionSecond(KeyEvent keyEvent) {  
	    password2=tfPassword2.getText();
	    pfPassword2.setText(password2);
	}

	public void firstOpenEyeClicked(MouseEvent mouseEvent) {
	    tfPassword1.setVisible(false);
	    openEyeImage1.setVisible(false);
	    closeEyeImage1.setVisible(true);
	    pfPassword1.setVisible(true);
	}
	
	public void secondOpenEyeClicked(MouseEvent mouseEvent) {	    
	    tfPassword2.setVisible(false);
	    openEyeImage2.setVisible(false);
	    closeEyeImage2.setVisible(true);
	    pfPassword2.setVisible(true);

	}

	public void firstCloseEyeClicked(MouseEvent mouseEvent) {
	    tfPassword1.setVisible(true);
	    openEyeImage1.setVisible(true);
	    closeEyeImage1.setVisible(false);
	    pfPassword1.setVisible(false);
	}
	
	public void secondCloseEyeClicked(MouseEvent mouseEvent) {
	    tfPassword2.setVisible(true);
	    openEyeImage2.setVisible(true);
	    closeEyeImage2.setVisible(false);
	    pfPassword2.setVisible(false);
	}
	
	@FXML
    public void registerButtonAction() {
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Messaggio di informazione");
		alert.setHeaderText("Registrazione avvenuta con successo");
		alert.setContentText("Clicca il pulsante 'login' qui sotto per poter tornare alla homepage!");
		
        String email = tfEmail.getText();
        String password = pfPassword1.getText();
        
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

