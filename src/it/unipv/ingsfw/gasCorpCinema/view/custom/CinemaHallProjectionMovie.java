package it.unipv.ingsfw.gasCorpCinema.view.custom;

import it.unipv.ingsfw.gasCorpCinema.model.cinemaHall.CinemaHall;
import it.unipv.ingsfw.gasCorpCinema.model.movie.Movie;
import it.unipv.ingsfw.gasCorpCinema.model.projection.Projection;

public class CinemaHallProjectionMovie<T, R, E> {
	private CinemaHall cinemaHall;
    private Projection projection;
    private Movie movie;
    
    public CinemaHallProjectionMovie(CinemaHall cinemaHall, Projection projection, Movie movie){
    	this.cinemaHall = cinemaHall;
    	this.projection = projection;
    	this.movie = movie;
    }
   
    @Override
    public String toString() {
    	// TODO Auto-generated method stub
    	return "SALA: " + cinemaHall.getIdHall() + " | " + "FILM: " + movie.getTitle() + " | " + "DATA: " + projection.getDate() + " | " + "ORARIO: " + projection.getTime() + 
    			" | " + "PREZZO: " + projection.getPrice();
    }
    
}
