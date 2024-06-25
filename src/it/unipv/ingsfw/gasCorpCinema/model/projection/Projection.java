package it.unipv.ingsfw.gasCorpCinema.model.projection;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import it.unipv.ingsfw.gasCorpCinema.model.movie.Movie;
import it.unipv.ingsfw.gasCorpCinema.utils.StringUtils;

public class Projection {

	private int idProjection;
	private int movieId;
	private Date date;
	private Time time;
	private double price;
	private int availableSeats;
	private String movieTitle;
	private int idHall;

	public Projection(int idProjection, int movieId, Date date, Time time, double price,int availableSeats) {
		this.idProjection = idProjection;
		this.movieId = movieId;
		this.date = date;
		this.time = time;
		this.price = price;
		this.availableSeats = availableSeats;
	}
	
	public Projection(int movieId, Date date, Time time, double price,int availableSeats) {
		this.movieId = movieId;
		this.date = date;
		this.time = time;
		this.price = price;
		this.availableSeats = availableSeats;
	}
	
	public Projection(Date date, Time time, double price,int availableSeats, String movieTitle) {
		this.date = date;
		this.time = time;
		this.price = price;
		this.availableSeats = availableSeats;
		this.movieTitle = movieTitle;
	}
	
	public Projection(int idProjection, int movieId, Date date, Time time, double price,int availableSeats, String movieTitle, int idHall) {
		this.idProjection = idProjection;
		this.movieId = movieId;
		this.date = date;
		this.time = time;
		this.price = price;
		this.availableSeats = availableSeats;
		this.movieTitle = movieTitle;
		this.idHall = idHall;
	}

	public Date getDate() {
		return date;
	}

	public Time getTime() {
		return time;
	}

	public double getPrice() {
		return price;
	}

	public int getIdProjection() {
		return idProjection;
	}

	public int getAvailableSeats() {
		return availableSeats;
	}

	public Time getEndTime(Movie movie){
		Time duration = movie.getDuration();
		Time time = getTime();

		// Converti java.sql.Time in java.time.LocalTime
		LocalTime localTime1 = time.toLocalTime();
		LocalTime localTime2 = duration.toLocalTime();

		LocalTime endMovieTime = localTime1.plusHours(localTime2.getHour())
				.plusMinutes(localTime2.getMinute())
				.plusSeconds(localTime2.getSecond());

		LocalTime addTimeToFreeTheHall = endMovieTime.plusMinutes(15);

		Time endTime = Time.valueOf(addTimeToFreeTheHall);

		return endTime;
	}

	@Override
	public String toString() {
		return "FILM: " + movieTitle + " | " + "SALA: " + idHall + " | " + "DATA: " + date + " | " + "ORARIO: " + time + " | " + "PREZZO: " + price + " | " + "POSTI: " + availableSeats;
	}
	
	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	
	public void setIdProjection(int idProjection) {
		this.idProjection = idProjection;
	}
	
	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}
	
	public void setIdHall(int idHall) {
		this.idHall = idHall;
	}
	
	public int getIdHall() {
		return idHall;
	}

}
