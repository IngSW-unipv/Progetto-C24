package it.unipv.ingsfw.gasCorpCinema.model;

import java.util.List;
import it.unipv.ingsfw.gasCorpCinema.model.cinemaHall.CinemaHall;
import it.unipv.ingsfw.gasCorpCinema.model.cinemaHall.CinemaHallDAO;
import it.unipv.ingsfw.gasCorpCinema.model.cinemaHall.ICinemaHallDAO;
import it.unipv.ingsfw.gasCorpCinema.model.movie.IMovieDAO;
import it.unipv.ingsfw.gasCorpCinema.model.movie.Movie;
import it.unipv.ingsfw.gasCorpCinema.model.movie.MovieDAO;
import it.unipv.ingsfw.gasCorpCinema.model.projection.IProjectionDAO;
import it.unipv.ingsfw.gasCorpCinema.model.projection.Projection;
import it.unipv.ingsfw.gasCorpCinema.model.projection.ProjectionDAO;

public class Admin {
	
	private IMovieDAO movieDAO;
	private IProjectionDAO projectionDAO;
	private ICinemaHallDAO cinemaHallDAO;
	
	public Admin() {
		movieDAO = new MovieDAO();
		projectionDAO = new ProjectionDAO();
		cinemaHallDAO = new CinemaHallDAO();
	}
	
	public Movie getMovieByTitle(String title) {
		return movieDAO.getMovieByTitle(title);
	}
	
	public List<Movie> getAllMovies() {
		List<Movie> movies = movieDAO.getAllMovies();
		return movies;
	}
	
	public void insertMovie(Movie movie) {
		Movie existingMovie = movieDAO.getMovieByTitle(movie.getTitle());
		if(existingMovie != null) {
			System.out.println("Il film " + movie.getTitle() + " esiste già");
		}else {
			movieDAO.insertMovie(movie);
		}
	}
	
	public void deleteMovie(Movie movie) {
		movieDAO.deleteMovie(movie);
	}
	
	public void printAllMovies(List<Movie> movies) {
		for(Movie movie : movies) {
			System.out.println(movie);
		}
	}
	
	public Projection getProjectionByHall(int idHall) {
		return projectionDAO.getProjectionByHall(idHall);
	}
	
	public List<Projection> getAllProjections(){
		List<Projection> projections = projectionDAO.getAllProjections();
		return projections;
	}
	
	public void createProjection(Projection projection) {
		Projection existingProjection = projectionDAO.getProjectionByHallDateTime(projection.getIdHall(),projection.getDate(),projection.getTime());
		if(existingProjection != null) {
			System.out.println("Sala " + projection.getIdHall() + " già occupata il " + projection.getDate() + " Alle " + projection.getTime());
		}else {
			projectionDAO.createProjection(projection);
		}
	}
	
	public void deleteProjection(Projection projection) {
		projectionDAO.deleteProjection(projection);
	}
	
	public CinemaHall getHallById(int idHall) {
		return cinemaHallDAO.getHallById(idHall);
	}
	
	public List<CinemaHall> getAllHalls(){
		List<CinemaHall> cinemaHalls = cinemaHallDAO.getAllHalls();
		return cinemaHalls;
	}
	
	public void createHall(CinemaHall cinemaHall) {
		cinemaHallDAO.createHall(cinemaHall);
	}
	
	public void deleteHall(CinemaHall cinemaHall) {
		cinemaHallDAO.deleteHall(cinemaHall);
	}
	//da implementare
	public int getPriceByProjection(Movie movie) {
		int d=10;
		return d;
	}
	public List<Projection> getprojectionsByMovie(Movie movie) {
		List<Projection> movieProjections = projectionDAO.getAllProjectionsByMovie(movie.getTitle());
		return movieProjections;
	}
	//da implementare per poter far si che il cliente scelga solo il numero corretto di ticket
	public int getNumberOfAvailableSeats(Projection p) {
		int d=10;
		return d;
	}
}
