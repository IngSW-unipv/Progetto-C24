package it.unipv.ingsfw.gasCorpCinema.model.cinemaHall;

import java.util.List;

public interface ICinemaHallDAO {
	
	public CinemaHall getHallById(int idHall);
	public List<CinemaHall> getAllHalls();
	public void createHall(CinemaHall cinemaHall);
	public void deleteHall(CinemaHall cinemaHall);
}
