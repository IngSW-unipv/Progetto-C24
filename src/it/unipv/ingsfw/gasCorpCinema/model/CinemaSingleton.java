package it.unipv.ingsfw.gasCorpCinema.model;

public class CinemaSingleton{
	
	private String cinemaName;
	private static CinemaSingleton instance = null;
	
	private CinemaSingleton() {
		cinemaName = "G.A.S. CORP CINEMA";
	}
	
	public static CinemaSingleton getInstance() {
		if(instance == null) {
			instance = new CinemaSingleton();
			System.out.println("Create new instance");
		}
		else
			System.out.println("Instance already available");
		return instance;
	}
	
	public String getCinemaName() {
		return cinemaName;
	}
}
