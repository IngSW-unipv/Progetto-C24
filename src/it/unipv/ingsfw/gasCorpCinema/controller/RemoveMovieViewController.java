package it.unipv.ingsfw.gasCorpCinema.controller;

import java.net.URL;
import java.util.ResourceBundle;

import it.unipv.ingsfw.gasCorpCinema.model.movie.IMovieDAO;
import it.unipv.ingsfw.gasCorpCinema.model.movie.Movie;
import it.unipv.ingsfw.gasCorpCinema.model.movie.MovieDAO;
import it.unipv.ingsfw.gasCorpCinema.model.role.Admin;
import it.unipv.ingsfw.gasCorpCinema.utils.AlertUtils;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;


public class RemoveMovieViewController implements Initializable {

	@FXML
	private ListView<Movie> myListView;
	@FXML
	private Label myLabel;
	@FXML
	private Button myButton;

	Movie selectedMovie;
	
	private IMovieDAO movieDAO = new MovieDAO();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		myListView.getItems().addAll(movieDAO.getAllMovies());

		if (myListView.getItems().isEmpty()) {
			myLabel.setText("Nessun film disponibile");
		}

		myListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Movie>(){

			@Override
			public void changed(ObservableValue<? extends Movie> arg0, Movie arg1, Movie arg2) {
				// TODO Auto-generated method stub

				if (myListView.getItems().isEmpty()) {
					myLabel.setText("Nessun film disponibile");
				} else {
					selectedMovie = myListView.getSelectionModel().getSelectedItem();
					myLabel.setText(String.valueOf(selectedMovie));
				}
			}
		});
	}

	public void removeMovie() {

		if(selectedMovie != null) {
			movieDAO.deleteMovie(selectedMovie);
			myListView.getItems().remove(selectedMovie);
		}else {
			AlertUtils.showAlert(AlertType.ERROR,"Errore","C'è stato un errore durante la rimozione del film",
					"Seleziona un film da eliminare prima di confermare!");		}
	}
}
