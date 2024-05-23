package it.unipv.ingsfw.gasCorpCinema.controller;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import it.unipv.ingsfw.gasCorpCinema.model.Admin;
import it.unipv.ingsfw.gasCorpCinema.model.cinemaHall.CinemaHall;
import it.unipv.ingsfw.gasCorpCinema.model.movie.Movie;
import it.unipv.ingsfw.gasCorpCinema.model.projection.Projection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddProjectionViewController implements Initializable {
	
	@FXML
	private ChoiceBox<CinemaHall> myChoiceBox1;
	@FXML
	private ChoiceBox<Movie> myChoiceBox2;
	@FXML
	private DatePicker myDatePicker;
	@FXML
	private TextField myTextField1,myTextField2;
	@FXML
	private Label myLabel1,myLabel2,myLabel3,myLabel4;
	@FXML
	private Button myButton;
	
	private Admin admin = new Admin();
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		myChoiceBox1.getItems().addAll(admin.getAllHalls());
		myChoiceBox1.setOnAction(this::getIdHall);
		
		myChoiceBox2.getItems().addAll(admin.getAllMovies());
		myChoiceBox2.setOnAction(this::getTitle);
	}
	
	public void getDate(ActionEvent event) {
		
		LocalDate myDate = myDatePicker.getValue();
		String myFormattedDate = myDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		// Se metti 3 MMM ti mette il nome del mese
		myLabel3.setText(myFormattedDate.toString());
	}
	
	public void getIdHall(ActionEvent event) {
		myLabel1.setText(String.valueOf(myChoiceBox1.getValue().getIdHall()));
	}
	
	public void getTitle(ActionEvent event) {
		myLabel2.setText(myChoiceBox2.getValue().getTitle());
	}
	
	public void createProjection() {
		Projection myProjection = new Projection(myChoiceBox1.getValue().getIdHall(),myChoiceBox1.getValue().getCapacity(),
		myChoiceBox2.getValue().getTitle(),Date.valueOf(myDatePicker.getValue()),myTextField1.getText(),Double.parseDouble(myTextField2.getText()));
		admin.createProjection(myProjection);
	}
}
