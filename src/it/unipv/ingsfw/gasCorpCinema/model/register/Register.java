package it.unipv.ingsfw.gasCorpCinema.model.register;

import it.unipv.ingsfw.gasCorpCinema.model.PersistenceFacade;
import it.unipv.ingsfw.gasCorpCinema.model.movie.Movie;
import it.unipv.ingsfw.gasCorpCinema.model.projection.Projection;

public class Register {
	private static Register instance;
	private PersistenceFacade persistence = PersistenceFacade.getInstance(); 

	public static Register getInstance() {
		if(instance==null) {
			instance=new Register();
		}
		return instance;
	}
	
	public void saleRegistration(Projection projection, Movie movie, int numberOfTickets, double total) {
		persistence.saleRegistration(projection, movie, numberOfTickets, total);
	} 
	
}
