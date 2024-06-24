package it.unipv.ingsfw.gasCorpCinema.model.register;

import it.unipv.ingsfw.gasCorpCinema.model.movie.Movie;
import it.unipv.ingsfw.gasCorpCinema.model.projection.Projection;

public class Register {
	private static Register instance;
	private IRegisterDAO registerDAO;
	
	private Register() {
		registerDAO = new RegisterDAO();
	}
	public static Register getInstance() {
		if(instance==null) {
			instance=new Register();
		}
		return instance;
	}
	
	public void saleRegistration(Projection projection, Movie movie, int numberOfTickets, double total) {
		registerDAO.saleRegistration(projection, movie, numberOfTickets, total);
	} 
	
}
