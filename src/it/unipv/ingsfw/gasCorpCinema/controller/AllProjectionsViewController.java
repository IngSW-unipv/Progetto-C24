package it.unipv.ingsfw.gasCorpCinema.controller;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import it.unipv.ingsfw.gasCorpCinema.model.Admin;
import it.unipv.ingsfw.gasCorpCinema.model.cinemaHall.CinemaHall;
import it.unipv.ingsfw.gasCorpCinema.model.movie.Movie;
import it.unipv.ingsfw.gasCorpCinema.model.projection.Projection;
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
	
	private Admin admin = new Admin();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		myListView.getItems().addAll(admin.getAllProjections());
		
		if (myListView.getItems().isEmpty()) {
            myLabel.setText("Nessuna proiezione disponibile");
		}
		
		myChoiceBoxHall.getItems().addAll(admin.getAllHalls());
		myChoiceBoxMovie.getItems().addAll(admin.getAllMovies());
		myChoiceBoxDate.getItems().addAll(admin.getAllDatesWithAProjection());
		
		myChoiceBoxHall.setOnAction(this::getIdHall);	
		myChoiceBoxMovie.setOnAction(this::getMovie);	
		myChoiceBoxDate.setOnAction(this::getDate);	

	}
	
	public void getIdHall(ActionEvent event) {
//		myListView.getItems().clear();
//		myListView.getItems().addAll(admin.getprojectionsByHall(myChoiceBoxHall.getValue().getIdHall()));
		if (myChoiceBoxHall.getValue() != null) {
            myListView.getItems().clear();
            myListView.getItems().addAll(admin.getprojectionsByHall(myChoiceBoxHall.getValue().getIdHall()));
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
            myListView.getItems().addAll(admin.getprojectionsByMovie(myChoiceBoxMovie.getValue().getTitle()));
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
            myListView.getItems().addAll(admin.getprojectionsByDate(myChoiceBoxDate.getValue()));
            if (myChoiceBoxHall.getValue() != null) {
                myChoiceBoxHall.setValue(null);
            } else if (myChoiceBoxMovie.getValue() != null) {
                myChoiceBoxMovie.setValue(null);
            }
        }
    }
}
