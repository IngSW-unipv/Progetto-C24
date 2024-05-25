package it.unipv.ingsfw.gasCorpCinema.controller;

import java.net.URL;
import java.util.ResourceBundle;
import it.unipv.ingsfw.gasCorpCinema.model.Admin;
import it.unipv.ingsfw.gasCorpCinema.model.movie.Movie;
import it.unipv.ingsfw.gasCorpCinema.model.projection.Projection;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;


public class RemoveMovieViewController implements Initializable {
	
	@FXML
	private ListView<Movie> myListView;
	@FXML
	private Label myLabel;
	@FXML
	private Button myButton;
	
	Movie selectedMovie;
	Admin admin = new Admin();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		myListView.getItems().addAll(admin.getAllMovies());
		
		myListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Movie>(){

			@Override
			public void changed(ObservableValue<? extends Movie> arg0, Movie arg1, Movie arg2) {
				// TODO Auto-generated method stub
				
				selectedMovie = myListView.getSelectionModel().getSelectedItem();
				myLabel.setText(String.valueOf(selectedMovie));
			}
		});
	}
	
	public void removeMovie() {
		admin.deleteMovie(selectedMovie);
	}
	
}
