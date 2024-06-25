package it.unipv.ingsfw.gasCorpCinema.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import java.sql.Time;
import org.junit.Before;
import org.junit.Test;
import it.unipv.ingsfw.gasCorpCinema.controller.AddMovieViewController;
import it.unipv.ingsfw.gasCorpCinema.model.movie.Movie;

public class AddMovieViewControllerTest {

	AddMovieViewController controller;

	@Before
	public void setUp() {
		controller = new AddMovieViewController();
	}

	@Test
	public void testIsTop() {
		assertEquals(controller.isTop(false), "Nor");
		assertEquals(controller.isTop(true), "Top");
	}

	@Test
	public void testIsDuplicatedMovie() {
		assertFalse(controller.isDuplicatedMovie(new Movie(1, "", "",Time.valueOf("18:00:00"),1,"")));
	}
}
