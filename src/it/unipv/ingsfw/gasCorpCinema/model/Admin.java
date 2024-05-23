package it.unipv.ingsfw.gasCorpCinema.model;

import java.util.List;
import it.unipv.ingsfw.gasCorpCinema.model.movie.IMovieDAO;
import it.unipv.ingsfw.gasCorpCinema.model.movie.Movie;
import it.unipv.ingsfw.gasCorpCinema.model.movie.MovieDAO;

public class Admin {
	
	private IMovieDAO movieDAO;
	
	public Admin() {
		movieDAO = new MovieDAO();
	}
	
	public Movie getMovieByTitle(String title) {
		return movieDAO.getMovieByTitle(title);
	}
	
	public List<Movie> getAllMovies() {
		List<Movie> movies = movieDAO.getAllMovies();
		return movies;
	}
	
	public void insertMovie(Movie movie) {
		movieDAO.insertMovie(movie);
	}
	
	public void deleteMovie(Movie movie) {
		movieDAO.deleteMovie(movie);
	}
	
	public void printAllMovies(List<Movie> movies) {
		for(Movie movie : movies) {
			System.out.println(movie);
		}
	}
}
