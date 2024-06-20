package it.unipv.ingsfw.gasCorpCinema.controller;

import it.unipv.ingsfw.gasCorpCinema.view.addMovieView.AddMovieView;
import it.unipv.ingsfw.gasCorpCinema.view.addProjectionView.AddProjectionView;
import it.unipv.ingsfw.gasCorpCinema.view.allMoviesView.AllMoviesView;
import it.unipv.ingsfw.gasCorpCinema.view.allProjectionsView.AllProjectionsView;
import it.unipv.ingsfw.gasCorpCinema.view.firstPageView.FirstPageView;
import it.unipv.ingsfw.gasCorpCinema.view.removeMovieView.RemoveMovieView;
import it.unipv.ingsfw.gasCorpCinema.view.removeProjectionView.RemoveProjectionView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class AdminViewController {	
	
	@FXML
    private Label emailLabel;
	@FXML
	private Button myButton;
	
	private Stage stage;

    private String adminEmail;

    public void setAdminEmail(String email) {
        this.adminEmail = email;
        emailLabel.setText(email);
    }
	
    public void logout() throws Exception {
    	
    	Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Logout");
		alert.setHeaderText("Stai per effettuare il logout");
		alert.setContentText("Dopo aver fatto il logout verrai riportato alla homepage.");
		
		if(alert.showAndWait().get()== ButtonType.OK) {
			
			Stage currentStage = (Stage) myButton.getScene().getWindow();
			
			stage = new Stage();
			FirstPageView v = new FirstPageView();
			v.start(stage);	
			
			currentStage.close();
		}
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
