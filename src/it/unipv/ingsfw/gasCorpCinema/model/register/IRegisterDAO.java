package it.unipv.ingsfw.gasCorpCinema.model.register;

import it.unipv.ingsfw.gasCorpCinema.model.projection.Projection;

public interface IRegisterDAO {
	public void saleRegistration(Projection projection, int numberOfTickets, double total);
}
