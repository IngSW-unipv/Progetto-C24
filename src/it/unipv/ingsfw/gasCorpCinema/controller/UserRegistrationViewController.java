package it.unipv.ingsfw.gasCorpCinema.controller;

import it.unipv.ingsfw.gasCorpCinema.model.Validation;
import it.unipv.ingsfw.gasCorpCinema.model.authentication.Authentication;
import it.unipv.ingsfw.gasCorpCinema.utils.AlertUtils;
import it.unipv.ingsfw.gasCorpCinema.utils.StringUtils;
import it.unipv.ingsfw.gasCorpCinema.view.homePage.HomePageView;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class UserRegistrationViewController {
	@FXML
	private Button buttonRegistrati, buttonLogin;

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
	private Authentication authentication;
	private Validation validation;

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

			if (!validation.emailValidate(email)) {
				displayError("Inserisci un email valida.");
				return;
			}

			if(!validation.passwordValidate(password)) {
				return;
			}
			
			if (StringUtils.isFieldEmpty(tfEmail) || StringUtils.isFieldEmpty(pfPassword) || StringUtils.isFieldEmpty(pfConfirmPassword)) {
				displayError("Tutti i campi sono obbligatori.");
				return;
			}			
			
			// Controllo se l'email esiste già nel database
			if (authentication.emailExists(email)) {
				displayError("Email già esistente. Usa un'altra email.");
				return;
			}

			// Controlla se le password coincidono
			if (!password.equals(confirmPassword)) {
				displayError("Le password che hai inserito non coincidono");
				return;
			}

			if (authentication.registration(email, password)) {
				AlertUtils.showAlert(AlertType.CONFIRMATION,"Messaggio di informazione", 
						"Registrazione avvenuta con successo", 
						"Clicca il pulsante 'login' qui sotto per poter tornare alla homepage!");
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
		Stage currentStage = (Stage) buttonLogin.getScene().getWindow();

		stage = new Stage();
		HomePageView f = new HomePageView();
		f.start(stage);	

		currentStage.close();
	}

	public void displayError(String errormessage) {
		errorLabel.setText(errormessage);
	}
}