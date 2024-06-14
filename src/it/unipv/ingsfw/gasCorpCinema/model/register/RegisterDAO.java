package it.unipv.ingsfw.gasCorpCinema.model.register;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;  
import java.text.SimpleDateFormat; 

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
	    	//DateFormat date = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss"); 
	    	//da sistemare formato data
	    	String dateString = String.valueOf(System.currentTimeMillis());
	    	
	    	st1.setString(1, dateString );	
	    	st1.setString(2, projection.getMovieTitle());
	    	st1.setString(3, String.valueOf(projection.getDate()));
	    	//da controllare formati date
	    	st1.setString(4, projection.getTime());
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
