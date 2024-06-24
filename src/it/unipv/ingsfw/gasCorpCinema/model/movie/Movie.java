package it.unipv.ingsfw.gasCorpCinema.model.movie;

import java.sql.Time;

public class Movie {
	
	private int idMovie;
	private String title;
	private String genre;
	private Time duration;
	private int year;
	private String rating;
	
	public Movie(int idMovie, String title,String genre,Time duration,int year,String rating) {
		this.idMovie = idMovie;
		this.title = title;
		this.genre = genre;
		this.duration = duration;
		this.year = year;
		this.rating = rating;
	}

	public String getTitle() {
		return title;
	}

	public String getGenre() {
		return genre;
	}

	public Time getDuration() {
		return duration;
	}

	public int getYear() {
		return year;
	}
	
	public String getRating() {
		return rating;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "TITOLO: " + title + " | " + "GENERE: " + genre + " | " + "DURATA: " + duration + " | " + "ANNO USCITA: " + year + " | " + " RATING: " + rating;
	}

	public int getIdMovie() {
		return idMovie;
	}
	
	
	
}
