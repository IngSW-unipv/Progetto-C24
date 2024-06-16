package it.unipv.ingsfw.gasCorpCinema.model.projection;

import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;

import it.unipv.ingsfw.gasCorpCinema.model.movie.Movie;

public class Projection {
	
	
	private int idHall;
	private int seats;
	private String movieTitle;
	private Date date;
	private Time time;
	private double price;
	
	public Projection(int idHall,int seats,String movieTitle,Date date,Time time,double price) {
		this.idHall = idHall;
		this.seats = seats;
		this.movieTitle = movieTitle;
		this.date = date;
		this.time = time;
		this.price = price;
	}

	public int getIdHall() {
		return idHall;
	}

	public int getSeats() {
		return seats;
	}
	
	public String getMovieTitle() {
		return movieTitle;
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
	
	public Time getStartTime() {
		LocalTime localTime1 = time.toLocalTime();
		
		LocalTime addAdsTime = localTime1.plusMinutes(25);
		Time startTime = Time.valueOf(addAdsTime);
	
		return startTime;
	}
	
	public Time getEndTime(Movie movie){
		Time duration = movie.getDuration();
        Time time = getStartTime();
        
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
		// TODO Auto-generated method stub
		return "SALA: " + idHall + " | " + "POSTI: " + seats + " | " + "FILM: " + movieTitle + " | " + "DATA: " + date + " | " + "ORARIO: " + time + " | " + "PREZZO: " + price;
	}
}
