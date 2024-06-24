package it.unipv.ingsfw.gasCorpCinema.utils;

import it.unipv.ingsfw.gasCorpCinema.model.cinemaHall.CinemaHall;
import it.unipv.ingsfw.gasCorpCinema.model.movie.Movie;
import it.unipv.ingsfw.gasCorpCinema.model.projection.Projection;
import javafx.scene.control.TextField;

public class StringUtils {

	public static boolean isFieldEmpty(TextField textField) {
        return textField == null || textField.getText().trim().isEmpty();
    }

	public static boolean isNumeric(String str) {
        return str.matches("\\d+(\\.\\d+)?");
    }
	
	public static String completeCinemaHallProjectionMovieToString(CinemaHall hall, Projection projection, Movie movie) {
		return "SALA: " + hall.getIdHall() + " | " + "POSTI: " + hall.getCapacity() + " | " + "FILM: " + movie.getTitle() + " | " + "DATA: " + projection.getDate() + " | " + "ORARIO: " + projection.getTime() + " | " + "PREZZO: " + projection.getPrice();
	}

}
