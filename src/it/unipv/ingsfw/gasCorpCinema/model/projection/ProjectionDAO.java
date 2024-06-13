package it.unipv.ingsfw.gasCorpCinema.model.projection;

import java.sql.Connection;
import java.sql.Date;
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
	      		
	        Projection projection = new Projection(rs1.getInt(1),rs1.getInt(2),rs1.getString(3),rs1.getDate(4),rs1.getString(5),rs1.getDouble(6));
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

	@Override
	public Projection getProjectionByHallDateTime(int idHall, Date date, String time) {
		
		Projection projection = null;
	    
		conn=DBConnection.startConnection(conn,schema);
		PreparedStatement st1;
		ResultSet rs1;
		
	    try {
	    	String query = "SELECT * FROM projections WHERE idHall = ? AND date = ? AND time = ?";
	    	
	    	st1 = conn.prepareStatement(query);
	    	st1.setInt(1, idHall);
	    	st1.setDate(2, date);
	    	st1.setString(3, time);
	    	
	    	rs1=st1.executeQuery();
	    	
	    	while(rs1.next()) {
	    		
	    		projection = new Projection(rs1.getInt(1),rs1.getInt(2),rs1.getString(3),rs1.getDate(4),rs1.getString(5),rs1.getDouble(6));
	    		
	        }
	    	
	    } catch (SQLException e) {
	      e.printStackTrace();
	    }
	    return projection;
	}

	@Override
	public List<Projection> getAllProjectionsByMovie(String movieTitle) {
		
		List<Projection> projections = new ArrayList<>();
		
		conn=DBConnection.startConnection(conn,schema);
		PreparedStatement st1;
		ResultSet rs1;
		
	    try {
	    	String query = "SELECT * FROM projections WHERE movieTitle = ?";
	    	
	    	st1 = conn.prepareStatement(query);
	    	st1.setString(1, movieTitle);
	        
	    	rs1=st1.executeQuery();

	      	while (rs1.next()) {
	      		
	        Projection projection = new Projection(rs1.getInt(1),rs1.getInt(2),rs1.getString(3),rs1.getDate(4),rs1.getString(5),rs1.getDouble(6));
	        projections.add(projection);
	      	}
	      	
	    } catch (SQLException e) {
	      e.printStackTrace();
	    }
	    
	    DBConnection.closeConnection(conn);
	    return projections;
	}

	@Override
	public int getNumberOfAvaiableSeats(Projection projection) {
		
		int availableSeats = 0;
	    
		conn=DBConnection.startConnection(conn,schema);
		PreparedStatement st1;
		ResultSet rs1;
		
	    try {
	    	String query = "SELECT seats FROM projections WHERE idHall = ? AND movieTitle = ? AND date = ? AND time = ?";
	    	
	    	st1 = conn.prepareStatement(query);
	    	st1.setInt(1, projection.getIdHall());
	    	st1.setString(2, projection.getMovieTitle());
	    	st1.setDate(3, projection.getDate());
	    	st1.setString(4, projection.getTime());
	    	
	    	rs1=st1.executeQuery();
	    	
	    	while(rs1.next()) {
	    		
	    		availableSeats = rs1.getInt("seats");
	        }
	    	
	    } catch (SQLException e) {
	      e.printStackTrace();
	    }
	    return availableSeats;
	}

	@Override
	public double getPriceOfProjection(Projection projection) {
		
		double price = 0;
	    
		conn=DBConnection.startConnection(conn,schema);
		PreparedStatement st1;
		ResultSet rs1;
		
	    try {
	    	String query = "SELECT price FROM projections WHERE idHall = ? AND movieTitle = ? AND date = ? AND time = ?";
	    	
	    	st1 = conn.prepareStatement(query);
	    	st1.setInt(1, projection.getIdHall());
	    	st1.setString(2, projection.getMovieTitle());
	    	st1.setDate(3, projection.getDate());
	    	st1.setString(4, projection.getTime());
	    	
	    	rs1=st1.executeQuery();
	    	
	    	while(rs1.next()) {
	    		
	    		price = rs1.getDouble("price");
	        }
	    	
	    } catch (SQLException e) {
	      e.printStackTrace();
	    }
	    return price;
	}

	@Override
	public List<Projection> getAllProjectionsByHall(int idHall) {
		
		List<Projection> projections = new ArrayList<>();
		
		conn=DBConnection.startConnection(conn,schema);
		PreparedStatement st1;
		ResultSet rs1;
		
	    try {
	    	String query = "SELECT * FROM projections WHERE idHall = ?";
	    	
	    	st1 = conn.prepareStatement(query);
	    	st1.setInt(1, idHall);
	        
	    	rs1=st1.executeQuery();

	      	while (rs1.next()) {
	      		
	        Projection projection = new Projection(rs1.getInt(1),rs1.getInt(2),rs1.getString(3),rs1.getDate(4),rs1.getString(5),rs1.getDouble(6));
	        projections.add(projection);
	      	}
	      	
	    } catch (SQLException e) {
	      e.printStackTrace();
	    }
	    
	    DBConnection.closeConnection(conn);
	    return projections;
	}

	@Override
	public List<Projection> getAllProjectionsByDate(Date date) {
		
		List<Projection> projections = new ArrayList<>();
		
		conn=DBConnection.startConnection(conn,schema);
		PreparedStatement st1;
		ResultSet rs1;
		
	    try {
	    	String query = "SELECT * FROM projections WHERE date = ?";
	    	
	    	st1 = conn.prepareStatement(query);
	    	st1.setDate(1, date);
	        
	    	rs1=st1.executeQuery();

	      	while (rs1.next()) {
	      		
	        Projection projection = new Projection(rs1.getInt(1),rs1.getInt(2),rs1.getString(3),rs1.getDate(4),rs1.getString(5),rs1.getDouble(6));
	        projections.add(projection);
	      	}
	      	
	    } catch (SQLException e) {
	      e.printStackTrace();
	    }
	    
	    DBConnection.closeConnection(conn);
	    return projections;
	}

	@Override
	public List<Date> getAllDatesWithAProjection() {
		
		List<Date> dates = new ArrayList<>();
		
		conn=DBConnection.startConnection(conn,schema);
		Statement st1;
		ResultSet rs1;
		
	    try {
	    	st1 = conn.createStatement();
	    	String query = "SELECT date FROM projections";
	        rs1 = st1.executeQuery(query);
	        
	      	while (rs1.next()) {
	      	
	      	Date date = rs1.getDate("date");
	        dates.add(date);
	      	}
	      	
	    } catch (SQLException e) {
	      e.printStackTrace();
	    }
	    
	    DBConnection.closeConnection(conn);
	    return dates;
	}

	@Override
	public List<Projection> getProjectionsByHallAndDate(int idHall, Date date) {
		
		List<Projection> projections = new ArrayList<>();
	    
		conn=DBConnection.startConnection(conn,schema);
		PreparedStatement st1;
		ResultSet rs1;
		
	    try {
	    	String query = "SELECT * FROM projections WHERE idHall = ? AND date = ?";
	    	
	    	st1 = conn.prepareStatement(query);
	    	st1.setInt(1, idHall);
	    	st1.setDate(2, date);
	    	
	    	rs1=st1.executeQuery();
	    	
	    	while(rs1.next()) {
	    		
	    		Projection projection = new Projection(rs1.getInt(1),rs1.getInt(2),rs1.getString(3),rs1.getDate(4),rs1.getString(5),rs1.getDouble(6));
	    		projections.add(projection);
	        }
	    	
	    } catch (SQLException e) {
	      e.printStackTrace();
	    }
	    return projections;
	}

}
