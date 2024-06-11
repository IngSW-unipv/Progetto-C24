package it.unipv.ingsfw.gasCorpCinema.model.projection;

import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import it.unipv.ingsfw.gasCorpCinema.model.movie.Movie;

public class Projection {
	
	private int idHall;
	private int seats;
	private String movieTitle;
	private Date date;
	private String time;
	private double price;
	
	public Projection(int idHall,int seats,String movieTitle,Date date,String time,double price) {
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
	
	public String getTime() {
		return time;
	}

	public double getPrice() {
		return price;
	}
	
	public Time getStartTime(){
		try {
            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
            java.util.Date utilDate = formatter.parse(time);
            return new Time(utilDate.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
	}
	
	public Time getEndTime(Movie movie){
		int duration = movie.getDuration();
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(getStartTime());
        calendar.add(Calendar.MINUTE, duration);
        
        // Ottieni l'orario di fine come un oggetto Time
        return new Time(calendar.getTimeInMillis());
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "ID CinemaHall: " + idHall + " | " + "Seats: " + seats + " | " + "Movie title: " + movieTitle + " | " + "Date: " + date + " | " + "Time: " + time + " | " + "Price: " + price;
	}
}
