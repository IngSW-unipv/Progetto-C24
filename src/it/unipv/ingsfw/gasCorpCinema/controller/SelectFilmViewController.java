package it.unipv.ingsfw.gasCorpCinema.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import it.unipv.ingsfw.gasCorpCinema.model.Admin;
import it.unipv.ingsfw.gasCorpCinema.model.SaleProcess;
import it.unipv.ingsfw.gasCorpCinema.model.movie.Movie;
import it.unipv.ingsfw.gasCorpCinema.utils.AlertUtils;
import it.unipv.ingsfw.gasCorpCinema.view.homePage.HomePageView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


public class SelectFilmViewController implements Initializable {

	@FXML
	private ListView<Movie> myListView;
	@FXML
	private Button buttonConfirm;
	@FXML
	private Label myLabel;
	@FXML
	private ImageView userImageView;
	private Movie selectedMovie;
	private Admin admin = new Admin();
	private Stage stage;
	private SaleProcess saleProcess;


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		saleProcess=SaleProcess.getInstance();
		myListView.getItems().addAll(admin.getAllMovies());
		
		if (myListView.getItems().isEmpty()) {
			myLabel.setText("Nessun film disponibile");
		}
	}

	public void pressButton() throws Exception {
				
		selectedMovie = myListView.getSelectionModel().getSelectedItem();
		if(selectedMovie!=null) {

			Properties p = new Properties(System.getProperties());
			p.load(new FileInputStream("Properties/Strings"));

			String viewPath = p.getProperty("SELECT_PROJECTION_FXML");
			File fxmlFile = new File(viewPath);
			URL fxmlResource = fxmlFile.toURI().toURL();
			
			changeScene(fxmlResource);
		}else {
			myLabel.setText("SELECT A FILM!");
		}
		//alla pressione del bottone se è stato selezionato un film cambia la vista per
		//scegliere la proiezione, altrimenti visualizza il messaggio SELECT A FILM

		Stage currentStage = (Stage) buttonConfirm.getScene().getWindow();
		currentStage.close();

	}

	public void changeScene(URL fxml) throws IOException {

		saleProcess.setMovie(selectedMovie);

		FXMLLoader loader = new FXMLLoader(fxml);
		Parent root = loader.load();	

		Scene scene = new Scene(root);
		stage = new Stage();
		stage.setScene(scene);
		stage.show();
	}

	public void logout() throws Exception {
		boolean alert = AlertUtils.showAlertAndWait(AlertType.CONFIRMATION,"Logout","Stai per effettuare il logout",
				"Dopo aver fatto il logout verrai riportato alla homepage.");

		if(alert) {
			Stage currentStage = (Stage) userImageView.getScene().getWindow();

			stage = new Stage();
			HomePageView v = new HomePageView();
			v.start(stage);	

			currentStage.close();
		}
	}
	
	
}





