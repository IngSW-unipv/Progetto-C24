package it.unipv.ingsfw.gasCorpCinema.model.register;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;  
import java.text.SimpleDateFormat;
import java.util.Date;

import it.unipv.ingsfw.gasCorpCinema.DBConnection;
import it.unipv.ingsfw.gasCorpCinema.model.projection.Projection;

public class RegisterDAO implements IRegisterDAO{
	private String schema;
	private Connection conn;

	public RegisterDAO() {
		this.schema = "cinema";
	}

	@Override
	public void saleRegistration(Projection projection, int numberOfTickets, double total) {
		
		conn=DBConnection.startConnection(conn,schema);
		PreparedStatement st1;
		
		
	    try {
	    
	    	String query = "INSERT INTO register (purchaseDateAndTime, movieTitle, projectionDate, projectionTime,ticketNumber, price, total) VALUES (?, ?, ?, ?, ?, ?, ?)";
	    	st1 = conn.prepareStatement(query);
	    	
	    	 // Ottieni l'istante corrente
	        Date currentDate = new Date();

	        // Crea un formato per la data e l'ora desiderato
	        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

	        // Formatta la data corrente secondo il formato specificato
	        String dateTimeString = dateFormat.format(currentDate);
	    	
	    	//da controllare formati date e time
	    	st1.setString(1, dateTimeString );	
	    	st1.setString(2, projection.getMovieTitle());
	    	st1.setString(3, String.valueOf(projection.getDate()));
	    	st1.setString(4, String.valueOf(projection.getTime()));
	    	st1.setInt(5, numberOfTickets);
	    	st1.setDouble(6, projection.getPrice());
	    	st1.setDouble(7, total);
	    	
	    	st1.executeUpdate();
	    	
	    } catch (SQLException e) {
	      e.printStackTrace();
	    }
		
	    DBConnection.closeConnection(conn);
		
	}
	
	
	
	
	
	
}
