package it.unipv.ingsfw.gasCorpCinema.model.register;


import it.unipv.ingsfw.gasCorpCinema.model.projection.Projection;

public class Register {
	private static Register instance;
	private IRegisterDAO registerDAO;
	
	public static Register getInstance() {
		if(instance==null) {
			instance=new Register();
		}
		return instance;
	}
	
	
	public void saleRegistration(Projection projection, int numberOfTickets, double total) {
		registerDAO.saleRegistration(projection, numberOfTickets, total);
	} 
	
}
