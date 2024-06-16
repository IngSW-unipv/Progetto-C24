package it.unipv.ingsfw.gasCorpCinema.controller;

import java.sql.Time;

import it.unipv.ingsfw.gasCorpCinema.model.Admin;
import it.unipv.ingsfw.gasCorpCinema.model.movie.Movie;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


public class AddMovieViewController {
	
	@FXML
	private Button myButton;
	@FXML
	private TextField myTextField1,myTextField2,myTextField3,myTextField4,myTextField5;
	
	private Admin admin = new Admin();
	private Movie movie;
	
	public void insertMovie() {
		try {
			
			movie = new Movie(myTextField1.getText(),myTextField2.getText(),Time.valueOf(myTextField3.getText() + ":00"),Integer.parseInt(myTextField4.getText()),Integer.parseInt(myTextField5.getText()));
			admin.insertMovie(movie);
			
			
		}catch(NumberFormatException e) {
			e.printStackTrace();
		}
	}
}
