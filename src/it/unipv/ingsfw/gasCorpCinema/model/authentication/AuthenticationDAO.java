package it.unipv.ingsfw.gasCorpCinema.model.authentication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.unipv.ingsfw.gasCorpCinema.DBConnection;


public class AuthenticationDAO implements IAuthenticationDAO {

	private String schema;
	private Connection conn;
	
	public AuthenticationDAO() {
		this.schema = "cinema";
	}
	
	@Override
	 public boolean emailExists(String email) {
	        conn = DBConnection.startConnection(conn, schema);
	        PreparedStatement stmt;
	        ResultSet rs;

	        try {
	            String query = "SELECT 1 FROM authentications WHERE email = ?";
	            stmt = conn.prepareStatement(query);
	            stmt.setString(1, email);

	            rs = stmt.executeQuery();

	            return rs.next();
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        } finally {
	            DBConnection.closeConnection(conn);
	        }
	    }
	@Override
	public boolean registration(String email, String password) {
		// TODO Auto-generated method stub
		conn=DBConnection.startConnection(conn,schema);
		PreparedStatement st1;
        
        try{
        	String query = "INSERT INTO authentications (email, password, role) VALUES (?, ?, 'user')";
        	st1 = conn.prepareStatement(query);
        	
        	st1.setString(1, email);
            st1.setString(2, password);
            int rowsInserted = st1.executeUpdate();
            
            return rowsInserted > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }finally {
        	DBConnection.closeConnection(conn);
        }
	}
	@Override
	public String login(String email, String password) {
	    conn = DBConnection.startConnection(conn, schema);
	    PreparedStatement stmt;
	    ResultSet rs;
	    String role = null;

	    try {
	        // Preparazione della query
	        String query = "SELECT role FROM authentications WHERE email = ? AND password = ?";
	        stmt = conn.prepareStatement(query);
	        
	        // Impostazione dei parametri
	        stmt.setString(1, email);
	        stmt.setString(2, password);
	        
	        // Esecuzione della query
	        rs = stmt.executeQuery();
	        
	        // Verifica se c'è una corrispondenza e ottieni il ruolo
	        if (rs.next()) {
	            role = rs.getString("role");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        // Chiusura della connessione al database indipendentemente dall'esito dell'autenticazione
	        DBConnection.closeConnection(conn);
	    }
	    return role;
	}
}
