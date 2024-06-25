package it.unipv.ingsfw.gasCorpCinema.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.Time;

import org.junit.Test;

import it.unipv.ingsfw.gasCorpCinema.model.cinemaHall.CinemaHall;
import it.unipv.ingsfw.gasCorpCinema.model.movie.Movie;
import it.unipv.ingsfw.gasCorpCinema.utils.CinemaHallUtils;

public class CinemaHallUtilsTest {
	
	@Test
	public void cinemaHallIsAdeguate() {
		CinemaHall cinemaHallTop = new CinemaHall(1,300);
		CinemaHall cinemaHallNor = new CinemaHall(1,250);
		Movie movieTop = new Movie(1, "", "",Time.valueOf("18:00:00"),1,"Top");
		Movie movieNor = new Movie(1, "", "",Time.valueOf("18:00:00"),1,"Nor");
		
		assertTrue(CinemaHallUtils.cinemaHallIsAdeguate(cinemaHallTop, movieTop));
		assertTrue(CinemaHallUtils.cinemaHallIsAdeguate(cinemaHallNor, movieNor));
		assertFalse(CinemaHallUtils.cinemaHallIsAdeguate(cinemaHallTop, movieNor));
		
	}
}
