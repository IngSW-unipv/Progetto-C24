package it.unipv.ingsfw.gasCorpCinema.controller;

import it.unipv.ingsfw.gasCorpCinema.view.AddMovieView;
import it.unipv.ingsfw.gasCorpCinema.view.AddProjectionView;
import it.unipv.ingsfw.gasCorpCinema.view.AllMoviesView;
import it.unipv.ingsfw.gasCorpCinema.view.AllProjectionsView;
import it.unipv.ingsfw.gasCorpCinema.view.RemoveMovieView;
import it.unipv.ingsfw.gasCorpCinema.view.RemoveProjectionView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;


public class AdminViewController {	
	
	@FXML
    private Label emailLabel;
	
	private Stage stage;

    private String adminEmail;

    public void setAdminEmail(String email) {
        this.adminEmail = email;
        emailLabel.setText(email);
    }
	
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
	
	public void switchToRemoveProjectionView(ActionEvent event) throws Exception {
		
		stage = new Stage();
		RemoveProjectionView v = new RemoveProjectionView();
		v.start(stage);
	}
	
	public void switchToAllProjectionsView(ActionEvent event) throws Exception {
		
		stage = new Stage();
		AllProjectionsView v = new AllProjectionsView();
		v.start(stage);
	}
}
