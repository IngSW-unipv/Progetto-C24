package it.unipv.ingsfw.gasCorpCinema.model;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Set;
import it.unipv.ingsfw.gasCorpCinema.model.authentication.AuthenticationDAO;
import it.unipv.ingsfw.gasCorpCinema.model.authentication.IAuthenticationDAO;
import it.unipv.ingsfw.gasCorpCinema.model.cinemaHall.CinemaHall;
import it.unipv.ingsfw.gasCorpCinema.model.cinemaHall.CinemaHallDAO;
import it.unipv.ingsfw.gasCorpCinema.model.cinemaHall.ICinemaHallDAO;
import it.unipv.ingsfw.gasCorpCinema.model.food.Food;
import it.unipv.ingsfw.gasCorpCinema.model.food.FoodDAO;
import it.unipv.ingsfw.gasCorpCinema.model.food.IFoodDAO;
import it.unipv.ingsfw.gasCorpCinema.model.movie.IMovieDAO;
import it.unipv.ingsfw.gasCorpCinema.model.movie.Movie;
import it.unipv.ingsfw.gasCorpCinema.model.movie.MovieDAO;
import it.unipv.ingsfw.gasCorpCinema.model.projection.IProjectionDAO;
import it.unipv.ingsfw.gasCorpCinema.model.projection.Projection;
import it.unipv.ingsfw.gasCorpCinema.model.projection.ProjectionDAO;
import it.unipv.ingsfw.gasCorpCinema.model.register.IRegisterDAO;
import it.unipv.ingsfw.gasCorpCinema.model.register.RegisterDAO;

public class PersistenceFacade {

	private ICinemaHallDAO cinemaHallDAO;
	private IMovieDAO movieDAO;
	private IProjectionDAO projectionDAO;
	private IRegisterDAO registerDAO;
	private IFoodDAO foodDAO;
	private IAuthenticationDAO authenticationDAO;
	private static PersistenceFacade instance;


	public PersistenceFacade() {
		this.cinemaHallDAO = new CinemaHallDAO();
		this.movieDAO = new MovieDAO();
		this.projectionDAO = new ProjectionDAO();
		this.registerDAO = new RegisterDAO();
		this.foodDAO = new FoodDAO();
		this.authenticationDAO = new AuthenticationDAO();

	}

	public static synchronized PersistenceFacade getInstance() {
		if (instance == null) {
			instance = new PersistenceFacade();
		}
		return instance;
	}

	
	public CinemaHall getHallById(int idHall) {
		return cinemaHallDAO.getHallById(idHall);
	}

	public CinemaHall getHallByProjectionId(int idProjection) {
		return cinemaHallDAO.getHallByProjectionId(idProjection);
	}

	public List<CinemaHall> getAllHalls() {
		return cinemaHallDAO.getAllHalls();
	}

	public void createHall(CinemaHall cinemaHall) {
		cinemaHallDAO.createHall(cinemaHall);
	}

	public void deleteHall(CinemaHall cinemaHall) {
		cinemaHallDAO.deleteHall(cinemaHall);
	}
	
	public Movie getMovieByProjection(Projection projection) {
        return movieDAO.getMovieByProjection(projection);
    }

    public Movie getMovieByTitle(String title) {
        return movieDAO.getMovieByTitle(title);
    }

    public List<Movie> getAllMovies() {
        return movieDAO.getAllMovies();
    }

    public void insertMovie(Movie movie) {
        movieDAO.insertMovie(movie);
    }

    public void deleteMovie(Movie movie) {
        movieDAO.deleteMovie(movie);
    }
    
    public Projection getProjectionByHallDateTime(int idHall, Date date, Time time) {
        return projectionDAO.getProjectionByHallDateTime(idHall, date, time);
    }

    public List<Projection> getAllProjections() {
        return projectionDAO.getAllProjections();
    }

    public List<Projection> getAllProjectionsByMovie(int idMovie) {
        return projectionDAO.getAllProjectionsByMovie(idMovie);
    }

    public List<Projection> getAllProjectionsByHall(int idHall) {
        return projectionDAO.getAllProjectionsByHall(idHall);
    }

    public List<Projection> getAllProjectionsByDate(Date date) {
        return projectionDAO.getAllProjectionsByDate(date);
    }

    public List<Projection> getProjectionsByHallAndDate(int idHall, Date date) {
        return projectionDAO.getProjectionsByHallAndDate(idHall, date);
    }

    public Set<Date> getAllDatesWithAProjection() {
        return projectionDAO.getAllDatesWithAProjection();
    }

    public void createProjection(Projection projection, CinemaHall cinemaHall) {
        projectionDAO.createProjection(projection, cinemaHall);
    }

    public void deleteProjection(Projection projection) {
        projectionDAO.deleteProjection(projection);
    }

    public int getNumberOfAvailableSeats(Projection projection) {
        return projectionDAO.getNumberOfAvailableSeats(projection);
    }

    public void decreaseNumberOfAvailableSeats(Projection projection, int numberOfTickets) {
        projectionDAO.decreaseNumberOfAvailableSeats(projection, numberOfTickets);
    }
    
	public void saleRegistration(Projection projection, Movie movie, int numberOfTickets, double total) {
		registerDAO.saleRegistration(projection, movie, numberOfTickets, total);
	}
	
	public Food getFoodByDescription(String description) {
        return foodDAO.getFoodByDescription(description);
    }

    public List<String> getAllDescriptions() {
        return foodDAO.getAllDescriptions();
    }

    public double getPriceOfFood(String description) {
        return foodDAO.getPriceOfFood(description);
    }
    
    public void decreaseQuantityOfFood(int quantity, String description) {
    	foodDAO.decreaseQuantityOfFood(quantity, description);
    }
    
    public boolean registration(String email, String password) {
        return authenticationDAO.registration(email, password);
    }

    public String login(String email, String password) {
        return authenticationDAO.login(email, password);
    }

    public boolean emailExists(String email) {
        return authenticationDAO.emailExists(email);
    }
}
