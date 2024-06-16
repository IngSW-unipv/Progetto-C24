package it.unipv.ingsfw.gasCorpCinema.model.projection;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public interface IProjectionDAO {
	
	public Projection getProjectionByHallDateTime(int idHall,Date date,Time time);
	public List<Projection> getAllProjections();
	public List<Projection> getAllProjectionsByMovie(String movieTitle);
	public List<Projection> getAllProjectionsByHall(int idHall);
	public List<Projection> getAllProjectionsByDate(Date date);
	public List<Projection> getProjectionsByHallAndDate(int idHall,Date date);
	public List<Date> getAllDatesWithAProjection();
	public void createProjection(Projection projection);
	public void deleteProjection(Projection projection);
	public int getNumberOfAvaiableSeats(Projection projection);
	public double getPriceOfProjection(Projection projection);
}
