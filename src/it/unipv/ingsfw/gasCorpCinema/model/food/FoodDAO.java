package it.unipv.ingsfw.gasCorpCinema.model.food;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import it.unipv.ingsfw.gasCorpCinema.DBConnection;

public class FoodDAO implements IFoodDAO{
	private String schema;
	private Connection conn;
	
	public FoodDAO() {
		this.schema = "cinema";
	}
	
	@Override
	public Food getFoodByDescription(String description) {

		
		Food food = null;
		
		conn=DBConnection.startConnection(conn,schema);
		PreparedStatement st1;
		ResultSet rs1;
		
	    try{
	    	String query = "SELECT * FROM food WHERE description = ?";
	    	
	    	st1 = conn.prepareStatement(query);
	    	st1.setString(1, description);
	    	
	    	rs1=st1.executeQuery();
	    	
	        while(rs1.next()) {
	        	
	          food = new Food(rs1.getString(1),rs1.getInt(2),rs1.getDouble(3));
	          
	        }
	    } catch (SQLException e) {
	      e.printStackTrace();
	    }
	    
	    DBConnection.closeConnection(conn);
	    return food;
	}

	@Override
	public List<String> getAllDescriptions() {
		
		
		List<String> foodDescription = new ArrayList<>();
		
		conn=DBConnection.startConnection(conn,schema);
		Statement st1;
		ResultSet rs1;
		
	    try {
	    	st1 = conn.createStatement();
	    	String query = "SELECT description FROM food";
	        rs1 = st1.executeQuery(query);
	        
	      	while (rs1.next()) {
	      		
	      		 foodDescription.add(rs1.getString("description"));
	      	}
	      	
	    } catch (SQLException e) {
	      e.printStackTrace();
	    }
	    
	    DBConnection.closeConnection(conn);
	    return foodDescription;	
	}

	@Override
	public double getPriceOfFood(String description) {
		
		double price = 0.0;
		
		conn=DBConnection.startConnection(conn,schema);
		PreparedStatement st1;
		ResultSet rs1;
		
	    try{
	    	String query = "SELECT price FROM food WHERE description = ?";
	        st1 = conn.prepareStatement(query);
	        st1.setString(1, description);
	        rs1 = st1.executeQuery();

	        if (rs1.next()) {
	            price = rs1.getDouble("price");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    DBConnection.closeConnection(conn);
	    return price;
	}
	
	public void decreaseQuantityOfFood(int quantity, String description) {
		conn=DBConnection.startConnection(conn,schema);
		PreparedStatement st1;
		
		try {
			
			String query = "UPDATE food SET food.quantity=food.quantity -?  WHERE food.description=?";
			st1 = conn.prepareStatement(query);
			st1.setInt(1, quantity);
			st1.setString(2, description);
			st1.executeUpdate();
							
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DBConnection.closeConnection(conn);
	}
}
