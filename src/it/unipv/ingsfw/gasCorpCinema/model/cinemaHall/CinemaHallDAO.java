package it.unipv.ingsfw.gasCorpCinema.model.cinemaHall;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import it.unipv.ingsfw.gasCorpCinema.DBConnection;

public class CinemaHallDAO implements ICinemaHallDAO  {
	
	private String schema;
	private Connection conn;
	
	public CinemaHallDAO() {
		this.schema = "cinema";
	}
	
	@Override
	public CinemaHall getHallById(int idHall) {

		CinemaHall cinemaHall = null;
		
		conn=DBConnection.startConnection(conn,schema);
		PreparedStatement st1;
		ResultSet rs1;
		
	    try{
	    	String query = "SELECT * FROM cinemahalls WHERE idHall = ?";
	    	
	    	st1 = conn.prepareStatement(query);
	    	st1.setInt(1, idHall);
	    	
	    	rs1=st1.executeQuery(query);
	    	
	        while(rs1.next()) {
	        	
	          cinemaHall = new CinemaHall(rs1.getInt(1),rs1.getInt(2));
	        }
	    } catch (SQLException e) {
	      e.printStackTrace();
	    }
	    
	    DBConnection.closeConnection(conn);
	    return cinemaHall;
	}

	@Override
	public List<CinemaHall> getAllHalls() {
		
		List<CinemaHall> cinemaHalls = new ArrayList<>();
		
		conn=DBConnection.startConnection(conn,schema);
		Statement st1;
		ResultSet rs1;
		
	    try {
	    	st1 = conn.createStatement();
	    	String query = "SELECT * FROM cinemahalls";
	        rs1 = st1.executeQuery(query);
	        
	      	while (rs1.next()) {
	      		
	        CinemaHall cinemaHall = new CinemaHall(rs1.getInt(1),rs1.getInt(2));
	        cinemaHalls.add(cinemaHall);
	      	}
	      	
	    } catch (SQLException e) {
	      e.printStackTrace();
	    }
	    
	    DBConnection.closeConnection(conn);
	    return cinemaHalls;
	}

	@Override
	public void createHall(CinemaHall cinemaHall) {
		
		conn=DBConnection.startConnection(conn,schema);
		PreparedStatement st1;
		
		
	    try {
	    
	    	String query = "INSERT INTO cinemahalls (idHall, capacity) VALUES (?, ?)";
	    	st1 = conn.prepareStatement(query);
	    	
	    	st1.setInt(1, cinemaHall.getIdHall());
	    	st1.setInt(2, cinemaHall.getCapacity());
	    	
	    	st1.executeUpdate();
	    	
	    } catch (SQLException e) {
	      e.printStackTrace();
	    }
		
	    DBConnection.closeConnection(conn);
	}

	@Override
	public void deleteHall(CinemaHall cinemaHall) {
		
		conn=DBConnection.startConnection(conn,schema);
		PreparedStatement st1;
		
	    try {
	    	
	    	String query = "DELETE FROM cinemahalls WHERE idHall = ?";
	    	st1 = conn.prepareStatement(query);
	    	
	    	st1.setInt(1, cinemaHall.getIdHall());
	    	st1.executeUpdate();
	    	
	    } catch (SQLException e) {
	      e.printStackTrace();
	    }
	    
	    DBConnection.closeConnection(conn);
	  }
}