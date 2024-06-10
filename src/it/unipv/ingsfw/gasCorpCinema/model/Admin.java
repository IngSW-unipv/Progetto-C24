package it.unipv.ingsfw.gasCorpCinema.model;

import java.sql.Date;
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
	
//	public Projection getProjectionByHall(int idHall) {
//		return projectionDAO.getProjectionByHall(idHall);
//	}
	
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
	
	public List<Date> getAllDatesWithAProjection(){
		List<Date> dates = projectionDAO.getAllDatesWithAProjection();
		return dates;
	}
	
	public void createHall(CinemaHall cinemaHall) {
		cinemaHallDAO.createHall(cinemaHall);
	}
	
	public void deleteHall(CinemaHall cinemaHall) {
		cinemaHallDAO.deleteHall(cinemaHall);
	}
	
	public double getPriceOfProjection(Projection projection) {
		double price = projectionDAO.getPriceOfProjection(projection);
		return price;
	}
	public List<Projection> getprojectionsByMovie(String title) {
		List<Projection> movieProjections = projectionDAO.getAllProjectionsByMovie(title);
		return movieProjections;
	}
	
	public List<Projection> getprojectionsByHall(int idHall) {
		List<Projection> movieProjections = projectionDAO.getAllProjectionsByHall(idHall);
		return movieProjections;
	}
	
	public List<Projection> getprojectionsByDate(Date date) {
		List<Projection> movieProjections = projectionDAO.getAllProjectionsByDate(date);
		return movieProjections;
	}
	
	public int getNumberOfAvailableSeats(Projection p) {
		int avaiableSeats = projectionDAO.getNumberOfAvaiableSeats(p);
		return avaiableSeats;
	}
}
