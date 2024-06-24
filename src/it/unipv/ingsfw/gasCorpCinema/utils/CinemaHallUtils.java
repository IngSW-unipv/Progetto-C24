package it.unipv.ingsfw.gasCorpCinema.utils;

import it.unipv.ingsfw.gasCorpCinema.model.cinemaHall.CinemaHall;
import it.unipv.ingsfw.gasCorpCinema.model.movie.Movie;

public class CinemaHallUtils {

	public static boolean cinemaHallIsAdeguate(CinemaHall cinemaHall, Movie movie) {
		if(movie.getRating().equals("Top") && cinemaHall.getCapacity() >= 300) {
			return true;
		}else if(movie.getRating().equals("Nor") && cinemaHall.getCapacity() < 300){
			return true;
		}
		
		return false;
	}
	
}
