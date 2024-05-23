package it.unipv.ingsfw.gasCorpCinema.model.projection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import it.unipv.ingsfw.gasCorpCinema.DBConnection;

public class ProjectionDAO implements IProjectionDAO {
	
	private String schema;
	private Connection conn;
	
	public ProjectionDAO() {
		this.schema = "cinema";
	}
	
	@Override
	public Projection getProjectionByHall(int idHall) {
		
		Projection projection = null;
	    
		conn=DBConnection.startConnection(conn,schema);
		PreparedStatement st1;
		ResultSet rs1;
		
	    try {
	    	String query = "SELECT * FROM projections WHERE idHall = ?";
	    	
	    	st1 = conn.prepareStatement(query);
	    	st1.setInt(1, idHall);
	    	
	    	rs1=st1.executeQuery(query);
	    	
	    	while(rs1.next()) {
	    		
	    		projection = new Projection(rs1.getInt(2),rs1.getInt(3),rs1.getString(4),rs1.getDate(5),rs1.getString(6),rs1.getDouble(7));
	    		
	        }
	    	
	    } catch (SQLException e) {
	      e.printStackTrace();
	    }
	    return projection;
	}

	@Override
	public List<Projection> getAllProjections() {
		
		List<Projection> projections = new ArrayList<>();
		
		conn=DBConnection.startConnection(conn,schema);
		Statement st1;
		ResultSet rs1;
		
	    try {
	    	st1 = conn.createStatement();
	    	String query = "SELECT * FROM projections";
	        rs1 = st1.executeQuery(query);
	        
	      	while (rs1.next()) {
	      		
	        Projection projection = new Projection(rs1.getInt(2),rs1.getInt(3),rs1.getString(4),rs1.getDate(5),rs1.getString(6),rs1.getDouble(7));
	        projections.add(projection);
	      	}
	      	
	    } catch (SQLException e) {
	      e.printStackTrace();
	    }
	    
	    DBConnection.closeConnection(conn);
	    return projections;
	}

	@Override
	public void createProjection(Projection projection) {
		
		conn=DBConnection.startConnection(conn,schema);
		PreparedStatement st1;
		
		
	    try {
	    
	    	String query = "INSERT INTO projections (idHall, seats, movieTitle, date, time, price) VALUES (?, ?, ?, ?, ?, ?)";
	    	st1 = conn.prepareStatement(query);
	    	
	    	st1.setInt(1, projection.getIdHall());
	    	st1.setInt(2, projection.getSeats());
	    	st1.setString(3, projection.getMovieTitle());
	    	st1.setDate(4, projection.getDate());
	    	st1.setString(5, projection.getTime());
	    	st1.setDouble(6, projection.getPrice());
	    	
	    	st1.executeUpdate();
	    	
	    } catch (SQLException e) {
	      e.printStackTrace();
	    }
		
	    DBConnection.closeConnection(conn);
	}

	@Override
	public void deleteProjection(Projection projection) {
		
		conn=DBConnection.startConnection(conn,schema);
		PreparedStatement st1;
		
	    try {
	    	
	    	String query = "DELETE FROM projections WHERE idHall = ? AND movieTitle = ? AND date = ? AND time = ?";
	    	st1 = conn.prepareStatement(query);
	    	
	    	st1.setInt(1, projection.getIdHall());
	    	st1.setString(2, projection.getMovieTitle());
	    	st1.setDate(3, projection.getDate());
	    	st1.setString(4, projection.getTime());
	    	st1.executeUpdate();
	    	
	    } catch (SQLException e) {
	      e.printStackTrace();
	    }
	    
	    DBConnection.closeConnection(conn);
	}

}
