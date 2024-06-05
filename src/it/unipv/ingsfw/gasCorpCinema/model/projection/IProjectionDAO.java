package it.unipv.ingsfw.gasCorpCinema.model.projection;

import java.sql.Date;
import java.util.List;

public interface IProjectionDAO {
	
	public Projection getProjectionByHall(int idHall);
	public Projection getProjectionByHallTimeDate(int idHall,Date date,String time);
	public List<Projection> getAllProjectionsByMovie(String movieTitle);
	public List<Projection> getAllProjections();
	public void createProjection(Projection projection);
	public void deleteProjection(Projection projection);
}
