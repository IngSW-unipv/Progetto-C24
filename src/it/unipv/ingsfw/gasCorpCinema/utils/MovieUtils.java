package it.unipv.ingsfw.gasCorpCinema.utils;

import java.util.List;

import it.unipv.ingsfw.gasCorpCinema.model.movie.Movie;

public final class MovieUtils {
	
	public static void printAllMovies(List<Movie> movies) {
		for(Movie movie : movies) {
			System.out.println(movie);
		}
	}

}
