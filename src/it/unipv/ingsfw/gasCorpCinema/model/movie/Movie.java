package it.unipv.ingsfw.gasCorpCinema.model.movie;

public class Movie {
	
	private String title;
	private String genre;
	private int duration;
	private int year;
	
	public Movie(String title,String genre,int duration,int year) {
		this.title = title;
		this.genre = genre;
		this.duration = duration;
		this.year = year;
	}

	public String getTitle() {
		return title;
	}

	public String getGenre() {
		return genre;
	}

	public int getDuration() {
		return duration;
	}

	public int getYear() {
		return year;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Titolo film: " + title + " | " + "Genere: " + genre + " | " + "Durata: " + duration + " minuti" + " | " + "Anno uscita: " + year;
	}
	
	
	
}
