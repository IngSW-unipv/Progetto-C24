package it.unipv.ingsfw.gasCorpCinema.model;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
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
		}else if(canAddProjection(projection) == false) {
			System.out.println("Cannot add projection: Time conflict with an existing projection.");
		}else {
			projectionDAO.createProjection(projection);
        }
	}
	
	private boolean isOverlapping(Projection p1, Projection p2) {
        Time start1 = p1.getStartTime();
        Time end1 = p1.getEndTime(movieDAO.getMovieByTitle(p1.getMovieTitle()));
        Time start2 = p2.getStartTime();
        Time end2 = p2.getEndTime(movieDAO.getMovieByTitle(p2.getMovieTitle()));

        return (start1.before(end2) && start2.before(end1));
    }
	
	public boolean canAddProjection(Projection projection) {
        List<Projection> existingProjections = projectionDAO.getProjectionsByHallAndDate(projection.getIdHall(),projection.getDate());
        
        for (Projection existingProjection : existingProjections) {
            if (isOverlapping(existingProjection, projection)) {
                return false;
            }
        }
        
        return true;
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
		for(int i=0;i<dates.size();i++) {
			for(int j=i+1;j<dates.size();j++){
				if(dates.get(i).equals(dates.get(j))){
					dates.remove(j);
				}
			}
		}
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
	
	//Override temporaneo forse 
	public List<Projection> getprojectionsByMovie(Movie movie) {
		List<Projection> movieProjections = projectionDAO.getAllProjectionsByMovie(movie.getTitle());
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
