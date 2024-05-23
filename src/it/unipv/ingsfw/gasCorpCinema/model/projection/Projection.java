package it.unipv.ingsfw.gasCorpCinema.model.projection;

public class Projection {
	
	private int idHall;
	private int seats;
	private String movieTitle;
	private String date;
	private String time;
	private double price;
	
	public Projection(int idHall,int seats,String movieTitle,String date,String time,double price) {
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
	
	public String getDate() {
		return date;
	}
	
	public String getTime() {
		return time;
	}

	public double getPrice() {
		return price;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "ID CinemaHall: " + idHall + " | " + "Seats: " + seats + " | " + "Movie title: " + movieTitle + " | " + "Date: " + date + " | " + "Time: " + time + " | " + "Price: " + price;
	}
}
