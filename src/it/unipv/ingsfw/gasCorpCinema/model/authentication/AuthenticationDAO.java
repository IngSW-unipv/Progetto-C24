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
	public boolean registration(String username, String email, String password) {
		// TODO Auto-generated method stub
		conn=DBConnection.startConnection(conn,schema);
		PreparedStatement st1;
        
        try{
        	String query = "INSERT INTO autenticazione (email, password, ruolo) VALUES (?, ?, 'utente')";
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
	public boolean login(String email, String password) {
		conn = DBConnection.startConnection(conn, schema);
		PreparedStatement stmt;
		ResultSet rs;

		
		try {
	        // Preparazione della query
	        String query = "SELECT * FROM autenticazione WHERE email = ? AND password = ?";
	        stmt = conn.prepareStatement(query);
	        
	        // Impostazione dei parametri
	        stmt.setString(1, email);
	        stmt.setString(2, password);
	        
	        // Esecuzione della query
	        rs = stmt.executeQuery();
	        
	        // Verifica se c'è una corrispondenza
	        return rs.next();
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    } finally {
	        // Chiusura della connessione al database indipendentemente dall'esito dell'autenticazione
	        DBConnection.closeConnection(conn);
	    }
	}
		
	
}
