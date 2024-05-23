package it.unipv.ingsfw.gasCorpCinema.model.projection;

import java.util.List;

public interface IProjectionDAO {
	
	Projection getProjectionByHall(int idHall);
	List<Projection> getAllProjections();
	public void createProjection(Projection projection);
	public void deleteProjection(Projection projection);
}
