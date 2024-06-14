package it.unipv.ingsfw.gasCorpCinema.model;

import it.unipv.ingsfw.gasCorpCinema.model.projection.Projection;

public class Register {
	private static Register instance;
	
	public static Register getInstance() {
		if(instance==null) {
			instance=new Register();
		}
		return instance;
	}
	
	
	public void saleRegistration() {
		//codice di interazione con register dao
	} 
	
}
