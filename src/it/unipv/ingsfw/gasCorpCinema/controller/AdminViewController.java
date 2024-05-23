package it.unipv.ingsfw.gasCorpCinema.controller;

import it.unipv.ingsfw.gasCorpCinema.view.AddMovieView;
import it.unipv.ingsfw.gasCorpCinema.view.AddProjectionView;
import it.unipv.ingsfw.gasCorpCinema.view.AllMoviesView;
import it.unipv.ingsfw.gasCorpCinema.view.RemoveMovieView;
import javafx.event.ActionEvent;
import javafx.stage.Stage;


public class AdminViewController {	
	
	private Stage stage;
	
	public void swtichToAddFilmView(ActionEvent event) throws Exception {
		
		stage = new Stage();
		AddMovieView v = new AddMovieView();
		v.start(stage);
	}
	
	public void swtichToRemoveFilmView(ActionEvent event) throws Exception {
		
		stage = new Stage();
		RemoveMovieView v = new RemoveMovieView();
		v.start(stage);
	}
	
	public void switchToAllFilmView(ActionEvent event) throws Exception {
		
		stage = new Stage();
		AllMoviesView v = new AllMoviesView();
		v.start(stage);
	}
	
	public void switchToAddProjectionView(ActionEvent event) throws Exception {
		
		stage = new Stage();
		AddProjectionView v = new AddProjectionView();
		v.start(stage);
	}
}
