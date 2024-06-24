package it.unipv.ingsfw.gasCorpCinema.model.movie;

import java.util.List;

import it.unipv.ingsfw.gasCorpCinema.model.projection.Projection;

public interface IMovieDAO {
	public Movie getMovieByProjection(Projection projection);
	public Movie getMovieByTitle(String title);
	public List<Movie> getAllMovies();
	public void insertMovie(Movie movie);
	public void deleteMovie(Movie movie);
}
