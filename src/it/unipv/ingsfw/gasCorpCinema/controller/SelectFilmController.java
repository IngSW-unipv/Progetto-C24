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
import javafx.scene.control.ListView;
import javafx.stage.Stage;


public class SelectFilmController implements Initializable {

	//controller che gestisce le seguenti situazioni:
		//1 setta il titolo dei vari eventi
		//2 click sul bottone pay
		//3 salva titolo e numero ticket così che possano essere decremetnati i posti disponibili nella sala
		
		//un primo problema da risolvere è: a chi chiedere i film salvati nel DB? ci sono 2 soluzioni:
		//chiedere ad admin che per information expert conosce i film salvati (ha già il metodo pronto)
		//accedere direttamente nel DB ed estrarli
		//forse chiedere ad admin covniene dato che cmq admin non fa tante cose e non andiamo a creare tanto accoppiamento 
	
	@FXML
	private ListView<Movie> myListView;
	private Movie movie;
	private Admin admin = new Admin();
	private Stage stage;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		myListView.getItems().addAll(admin.getAllMovies());
		
		myListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener <Movie> () {

			@Override
			public void changed(ObservableValue<? extends Movie> observable, Movie oldValue, Movie newValue) {
				movie = myListView.getSelectionModel().getSelectedItem();
				//movie = film scelto (NB è di tipo movie)
				stage = new Stage();
				SelectProjectionView v = new SelectProjectionView();
				try {
					v.start(stage);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

	public Movie getMovie() {
		return movie;
	}
	//serve per far si che nella view SelectDateTime view si sappia di che fil si tratta
	
	
	
}

	
	


