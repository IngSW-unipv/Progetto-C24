package it.unipv.ingsfw.gasCorpCinema.model.projection;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Set;

import it.unipv.ingsfw.gasCorpCinema.model.cinemaHall.CinemaHall;

public interface IProjectionDAO {
	
	public Projection getProjectionByHallDateTime(int idHall,Date date,Time time);
	public List<Projection> getAllProjections();
	public List<Projection> getAllProjectionsByMovie(int idMovie);
	public List<Projection> getAllProjectionsByHall(int idHall);
	public List<Projection> getAllProjectionsByDate(Date date);
	public List<Projection> getProjectionsByHallAndDate(int idHall,Date date);
	public Set<Date> getAllDatesWithAProjection();
	public void createProjection(Projection projection, CinemaHall cinemaHall);
	public void deleteProjection(Projection projection);
	public int getNumberOfAvailableSeats(Projection projection);
}
