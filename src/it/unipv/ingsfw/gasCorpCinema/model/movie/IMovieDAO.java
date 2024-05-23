package it.unipv.ingsfw.gasCorpCinema.model.movie;

import java.util.List;

public interface IMovieDAO {
	
	public Movie getMovieByTitle(String title);
	public List<Movie> getAllMovies();
	public void insertMovie(Movie movie);
	public void deleteMovie(Movie movie);
}
