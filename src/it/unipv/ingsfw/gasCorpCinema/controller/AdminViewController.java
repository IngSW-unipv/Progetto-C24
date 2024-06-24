package it.unipv.ingsfw.gasCorpCinema.controller;

import it.unipv.ingsfw.gasCorpCinema.model.role.RoleType;
import it.unipv.ingsfw.gasCorpCinema.utils.AlertUtils;
import it.unipv.ingsfw.gasCorpCinema.utils.StageUtils;
import it.unipv.ingsfw.gasCorpCinema.view.addMovie.AddMovieView;
import it.unipv.ingsfw.gasCorpCinema.view.addProjection.AddProjectionView;
import it.unipv.ingsfw.gasCorpCinema.view.allMovies.AllMoviesView;
import it.unipv.ingsfw.gasCorpCinema.view.allProjections.AllProjectionsView;
import it.unipv.ingsfw.gasCorpCinema.view.homePage.HomePageView;
import it.unipv.ingsfw.gasCorpCinema.view.removeMovie.RemoveMovieView;
import it.unipv.ingsfw.gasCorpCinema.view.removeProjection.RemoveProjectionView;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class AdminViewController {	

	@FXML
	private Label emailLabel;
	@FXML
	private Button myButton;

	public void logout() throws Exception {

		boolean alert = AlertUtils.showAlertAndWait(AlertType.CONFIRMATION,"Logout","Stai per effettuare il logout",
				"Dopo aver fatto il logout verrai riportato alla homepage.");

		if(alert) {

			Stage currentStage = (Stage) myButton.getScene().getWindow();

			HomePageView view = new HomePageView();
			StageUtils.addToStageAndStart((Application)view);	

			currentStage.close();
		}
	}

	public void swtichToAddFilmView(ActionEvent event) throws Exception {

		AddMovieView view = new AddMovieView();
		StageUtils.addToStageAndStart((Application)view);		

	}

	public void swtichToRemoveFilmView(ActionEvent event) throws Exception {

		RemoveMovieView view = new RemoveMovieView();
		StageUtils.addToStageAndStart((Application)view);	
	}

	public void switchToAllFilmView(ActionEvent event) throws Exception {
		AllMoviesView view = new AllMoviesView();
		StageUtils.addToStageAndStart((Application)view);
	}

	public void switchToAddProjectionView(ActionEvent event) throws Exception {

		AddProjectionView view = new AddProjectionView();
		StageUtils.addToStageAndStart((Application)view);
	}

	public void switchToRemoveProjectionView(ActionEvent event) throws Exception {

		RemoveProjectionView view = new RemoveProjectionView();
		StageUtils.addToStageAndStart((Application)view);
	}

	public void switchToAllProjectionsView(ActionEvent event) throws Exception {

		AllProjectionsView view = new AllProjectionsView();
		StageUtils.addToStageAndStart((Application)view);
	}
}
