package it.unipv.ingsfw.gasCorpCinema.model.movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import it.unipv.ingsfw.gasCorpCinema.DBConnection;

public class MovieDAO implements IMovieDAO{
	
	private String schema;
	private Connection conn;
	
	public MovieDAO() {
		this.schema = "cinema";
	}
	
	@Override
	public Movie getMovieByTitle(String title) {

		Movie movie = null;
		
		conn=DBConnection.startConnection(conn,schema);
		PreparedStatement st1;
		ResultSet rs1;
		
	    try{
	    	String query = "SELECT * FROM movies WHERE title = ?";
	    	
	    	st1 = conn.prepareStatement(query);
	    	st1.setString(1, title);
	    	
	    	rs1=st1.executeQuery();
	    	
	        while(rs1.next()) {
	        	
	          movie = new Movie(rs1.getString(1),rs1.getString(2),rs1.getTime(3),rs1.getInt(4),rs1.getString(5));
	        }
	    } catch (SQLException e) {
	      e.printStackTrace();
	    }
	    
	    DBConnection.closeConnection(conn);
	    return movie;
	}

	@Override
	public List<Movie> getAllMovies() {
		
		List<Movie> movies = new ArrayList<>();
		
		conn=DBConnection.startConnection(conn,schema);
		Statement st1;
		ResultSet rs1;
		
	    try {
	    	st1 = conn.createStatement();
	    	String query = "SELECT * FROM movies";
	        rs1 = st1.executeQuery(query);
	        
	      	while (rs1.next()) {
	      		
	        Movie movie = new Movie(rs1.getString(1),rs1.getString(2),rs1.getTime(3),rs1.getInt(4),rs1.getString(5));
	        movies.add(movie);
	      	}
	      	
	    } catch (SQLException e) {
	      e.printStackTrace();
	    }
	    
	    DBConnection.closeConnection(conn);
	    return movies;
	}

	@Override
	public void insertMovie(Movie movie) {
		
		conn=DBConnection.startConnection(conn,schema);
		PreparedStatement st1;
		
		
	    try {
	    
	    	String query = "INSERT INTO movies (title, genre, duration, year, rating) VALUES (?, ?, ?, ?, ?)";
	    	st1 = conn.prepareStatement(query);
	    	
	    	st1.setString(1, movie.getTitle());
	    	st1.setString(2, movie.getGenre());
	    	st1.setTime(3, movie.getDuration());
	    	st1.setInt(4, movie.getYear());
	    	st1.setString(5, movie.getRating());
	    	
	    	st1.executeUpdate();
	    	
	    } catch (SQLException e) {
	      e.printStackTrace();
	    }
		
	    DBConnection.closeConnection(conn);
	}

	@Override
	public void deleteMovie(Movie movie) {
		
		conn=DBConnection.startConnection(conn,schema);
		PreparedStatement st1;
		
	    try {
	    	
	    	String query = "DELETE FROM movies WHERE title = ?";
	    	st1 = conn.prepareStatement(query);
	    	
	    	st1.setString(1, movie.getTitle());
	    	st1.executeUpdate();
	    	
	    } catch (SQLException e) {
	      e.printStackTrace();
	    }
	    
	    DBConnection.closeConnection(conn);
	  }
}
