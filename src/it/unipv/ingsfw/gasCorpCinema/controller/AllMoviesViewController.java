package it.unipv.ingsfw.gasCorpCinema.controller;

import java.net.URL;
import java.util.ResourceBundle;
import it.unipv.ingsfw.gasCorpCinema.model.movie.Movie;
import it.unipv.ingsfw.gasCorpCinema.model.movie.MovieDAO;
import it.unipv.ingsfw.gasCorpCinema.model.movie.IMovieDAO;
import it.unipv.ingsfw.gasCorpCinema.model.role.Admin;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;


public class AllMoviesViewController implements Initializable {
	
	@FXML
	private ListView<Movie> myListView;
	@FXML
	private Label myLabel;
	
	private IMovieDAO movieDAO = new MovieDAO();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		myListView.getItems().addAll(movieDAO.getAllMovies());
		
		if (myListView.getItems().isEmpty()) {
            myLabel.setText("Nessun film disponibile");
		}
	}

}
