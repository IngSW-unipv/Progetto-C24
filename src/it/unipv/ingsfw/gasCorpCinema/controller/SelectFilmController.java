package it.unipv.ingsfw.gasCorpCinema.controller;

import java.net.URL;
import java.util.ResourceBundle;

import it.unipv.ingsfw.gasCorpCinema.model.Admin;
import it.unipv.ingsfw.gasCorpCinema.model.movie.Movie;
import it.unipv.ingsfw.gasCorpCinema.view.AddMovieView;
import it.unipv.ingsfw.gasCorpCinema.view.SelectProjectionView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.stage.Stage;


public class SelectFilmController implements Initializable {
		//un primo problema da risolvere è: a chi chiedere i film salvati nel DB? 
		//chiedere ad admin che per information expert conosce i film salvati (ha già il metodo pronto) 
		//con un'accoppiamento ancora esiguo
	
	@FXML
	private ListView<Movie> myListView;
	@FXML
	private Button buttonConfirm;
	@FXML
	private Label myLabel;
	@FXML
	private Button logoutButton;
	
	private Movie movie;
	private Admin admin = new Admin();
	private Stage stage;
	
	 
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		myListView.getItems().addAll(admin.getAllMovies());
	}
	//ListView=lista di film disponibili al cinema 

	
	
	
	public Movie getMovie() {
		return myListView.getSelectionModel().getSelectedItem();
	}
	//serve per far si che nel SelectProjectionController si sappia di che film si tratta	
	
	public void pressButton() throws Exception {
		movie = myListView.getSelectionModel().getSelectedItem();
		//movie = film selezionato
		stage = new Stage();
		SelectProjectionView v = new SelectProjectionView();
		
		if(movie!=null) {
			v.start(stage);
		}else {
			myLabel.setText("SELECT A FILM!");
		}
		//alla pressione del bottone se è stato selezionato un film cambia la vista per scegliere la proiezione
		//altrimenit visutlaizza il messaggio SELECT A FILM		
		
	}
	
	public void logout() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Logout");
		alert.setHeaderText("Stai per effettuare il logout");
		alert.setContentText("Dopo aver fatto il logout verrai riportato alla homepage.");
		
		if(alert.showAndWait().get()== ButtonType.OK) {
			//ccambia pagina
		}
	}
	
}

	
	


