package it.unipv.ingsfw.gasCorpCinema.controller;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Properties;
import it.unipv.ingsfw.gasCorpCinema.model.User;
import it.unipv.ingsfw.gasCorpCinema.utils.AlertUtils;
import it.unipv.ingsfw.gasCorpCinema.view.userRegistration.UserRegistrationView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class HomePageViewController {

	@FXML
	private ImageView imageCinema;
	@FXML
	private TextField tfUsername;
	@FXML
	private PasswordField fieldPassword;
	@FXML
	private Button buttonLogin;
	@FXML
	private Button buttonRegistrati;

	private User user = new User();

	@FXML
	public void loginButtonAction() throws Exception {

		String email = tfUsername.getText();
		String password = fieldPassword.getText();
		String role = user.login(email, password);

		Properties p = new Properties(System.getProperties());

		p.load(new FileInputStream("Properties/Strings"));

		if (role != null) {

			// Ottieni il percorso della vista associata al ruolo
			String viewPath = p.getProperty(role.toUpperCase());

			if (viewPath != null) {
				
				File fxmlFile = new File(viewPath);
				URL fxmlResource = fxmlFile.toURI().toURL();
				changeScene(fxmlResource, email);
			}
		} else {
			// Mostra un messaggio di errore se il login fallisce
			AlertUtils.showAlert(AlertType.ERROR,"Errore","C'è stato un errore durante il login",
			"Credenziali errate, prova ad inserirle nuovamente o se non l'hai già fatto registrati!");        
		}
	}

	@FXML
	public void registerButtonAction() throws Exception {
		Stage currentStage = (Stage) buttonRegistrati.getScene().getWindow();

		Stage stage = new Stage();
		UserRegistrationView u = new UserRegistrationView();
		u.start(stage);	

		currentStage.close();
	}

	public void changeScene(URL fxml,String email) throws Exception {

		FXMLLoader loader = new FXMLLoader(fxml);
        Parent root = loader.load();
        Object controller = loader.getController();
		
		try {

			((AdminViewController)controller).setAdminEmail(email);

		}catch(ClassCastException e){
		}

		try {

			((SelectFilmViewController)controller).setUserEmail(email);

		}catch(ClassCastException e){
		}

		Scene scene = new Scene(root);
		Stage stage = new Stage();
		
		stage.setScene(scene);
		stage.show();
	}
}