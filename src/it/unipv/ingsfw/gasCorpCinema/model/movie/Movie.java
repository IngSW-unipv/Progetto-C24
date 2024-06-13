package it.unipv.ingsfw.gasCorpCinema.model.movie;

public class Movie {
	
	private String title;
	private String genre;
	private int duration;
	private int year;
	private int rating;
	
	public Movie(String title,String genre,int duration,int year,int rating) {
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

	public int getDuration() {
		return duration;
	}

	public int getYear() {
		return year;
	}
	
	public int getRating() {
		return rating;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "TITOLO: " + title + " | " + "GENERE: " + genre + " | " + "DURATA: " + duration + " minuti" + " | " + "ANNO USCITA: " + year + " | " + " RATING: " + rating;
	}
	
	
	
}
