package it.unipv.ingsfw.gasCorpCinema.controller;

import java.net.URL;
import java.util.ResourceBundle;
import it.unipv.ingsfw.gasCorpCinema.model.Admin;
import it.unipv.ingsfw.gasCorpCinema.model.movie.Movie;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;


public class RemoveMovieViewController implements Initializable {
	
	@FXML
	private ChoiceBox<Movie> myChoiceBox;
	
	private Admin admin = new Admin();
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		myChoiceBox.getItems().addAll(admin.getAllMovies());
		myChoiceBox.setOnAction(this::DeleteMovie);
	}
	
	public void DeleteMovie(ActionEvent event) {
		Movie myMovie = myChoiceBox.getValue();
		admin.deleteMovie(myMovie);
	}
	
}
