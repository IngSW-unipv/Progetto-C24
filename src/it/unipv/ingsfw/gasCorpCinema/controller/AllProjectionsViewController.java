package it.unipv.ingsfw.gasCorpCinema.controller;

import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;

import it.unipv.ingsfw.gasCorpCinema.model.cinemaHall.CinemaHall;
import it.unipv.ingsfw.gasCorpCinema.model.cinemaHall.CinemaHallDAO;
import it.unipv.ingsfw.gasCorpCinema.model.cinemaHall.ICinemaHallDAO;
import it.unipv.ingsfw.gasCorpCinema.model.movie.Movie;
import it.unipv.ingsfw.gasCorpCinema.model.movie.MovieDAO;
import it.unipv.ingsfw.gasCorpCinema.model.movie.IMovieDAO;
import it.unipv.ingsfw.gasCorpCinema.model.projection.Projection;
import it.unipv.ingsfw.gasCorpCinema.model.projection.ProjectionDAO;
import it.unipv.ingsfw.gasCorpCinema.model.projection.IProjectionDAO;
import it.unipv.ingsfw.gasCorpCinema.model.role.Admin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class AllProjectionsViewController implements Initializable {
	
	@FXML
	private ListView<Projection> myListView;
	@FXML
	private ChoiceBox<CinemaHall> myChoiceBoxHall;
	@FXML
	private ChoiceBox<Movie> myChoiceBoxMovie;
	@FXML
	private ChoiceBox<Date> myChoiceBoxDate;
	@FXML
	private Label myLabel;
	
	private IMovieDAO movieDAO = new MovieDAO();
	private IProjectionDAO projectionDAO = new ProjectionDAO();
	private ICinemaHallDAO cinemaHallDAO = new CinemaHallDAO();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		myListView.getItems().addAll(projectionDAO.getAllProjections());
		
		if (myListView.getItems().isEmpty()) {
            myLabel.setText("Nessuna proiezione disponibile");
		}
		
		myChoiceBoxHall.getItems().addAll(cinemaHallDAO.getAllHalls());
		myChoiceBoxMovie.getItems().addAll(movieDAO.getAllMovies());
		myChoiceBoxDate.getItems().addAll(projectionDAO.getAllDatesWithAProjection());
		
		myChoiceBoxHall.setOnAction(this::getIdHall);	
		myChoiceBoxMovie.setOnAction(this::getMovie);	
		myChoiceBoxDate.setOnAction(this::getDate);	

	}
	
	public void getIdHall(ActionEvent event) {
//		myListView.getItems().clear();
//		myListView.getItems().addAll(admin.getprojectionsByHall(myChoiceBoxHall.getValue().getIdHall()));
		if (myChoiceBoxHall.getValue() != null) {
            myListView.getItems().clear();
            myListView.getItems().addAll(projectionDAO.getAllProjectionsByHall(myChoiceBoxHall.getValue().getIdHall()));
            if (myChoiceBoxMovie.getValue() != null) {
                myChoiceBoxMovie.setValue(null);
            } else if (myChoiceBoxDate.getValue() != null) {
                myChoiceBoxDate.setValue(null);
            }
        }
	}

	private void getMovie(ActionEvent actionevent1) {
//		myListView.getItems().clear();
//		myListView.getItems().addAll(admin.getprojectionsByMovie(myChoiceBoxMovie.getValue().getTitle()));
		if (myChoiceBoxMovie.getValue() != null) {
            myListView.getItems().clear();
            myListView.getItems().addAll(projectionDAO.getAllProjectionsByMovie(myChoiceBoxMovie.getValue().getIdMovie()));
            if (myChoiceBoxHall.getValue() != null) {
                myChoiceBoxHall.setValue(null);
            } else if (myChoiceBoxDate.getValue() != null) {
                myChoiceBoxDate.setValue(null);
            }
        }
	}
	
	private void getDate(ActionEvent actionevent1) {
//		myListView.getItems().clear();
//		myListView.getItems().addAll(admin.getprojectionsByDate(myChoiceBoxDate.getValue()));
		if (myChoiceBoxDate.getValue() != null) {
            myListView.getItems().clear();
            myListView.getItems().addAll(projectionDAO.getAllProjectionsByDate(myChoiceBoxDate.getValue()));
            if (myChoiceBoxHall.getValue() != null) {
                myChoiceBoxHall.setValue(null);
            } else if (myChoiceBoxMovie.getValue() != null) {
                myChoiceBoxMovie.setValue(null);
            }
        }
    }
}
