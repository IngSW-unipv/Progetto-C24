package it.unipv.ingsfw.gasCorpCinema.model.projection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import it.unipv.ingsfw.gasCorpCinema.DBConnection;
import it.unipv.ingsfw.gasCorpCinema.model.movie.Movie;

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

				Projection projection = new Projection(rs1.getInt(1),rs1.getInt(2),rs1.getDate(3),rs1.getTime(4),rs1.getDouble(5));
				projections.add(projection);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		DBConnection.closeConnection(conn);
		return projections;
	}

	@Override
	public void createProjection(Projection projection, int hallId, int movieId) {

		conn=DBConnection.startConnection(conn,schema);
		PreparedStatement st1;
		PreparedStatement st2;


		try {

			String query = "INSERT INTO projections (idProjection, movieId, date, time, price) VALUES (?, ?, ?, ?, ?)";
			st1 = conn.prepareStatement(query);

			st1.setInt(1, projection.getIdProjection());
			st1.setInt(2, movieId);
			st1.setDate(3, projection.getDate());
			st1.setTime(4, projection.getTime());
			st1.setDouble(5, projection.getPrice());

			st1.executeUpdate();
			
			String query2 = "INSERT INTO hall_projection(idHall,idProjection) VALUES (?, ?)";
			st2 = conn.prepareStatement(query2);
			
			st2.setInt(1, hallId);
			st2.setInt(2, projection.getIdProjection());
			st2.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		DBConnection.closeConnection(conn);
	}

	@Override
	public void deleteProjection(Projection projection) {

		conn=DBConnection.startConnection(conn,schema);
		PreparedStatement st1;
		PreparedStatement st2;

		try {
			String query1 = "DELETE FROM hall_projection WHERE idProjection = ?";
			st1 = conn.prepareStatement(query1);

			st1.setInt(1, projection.getIdProjection());
			st1.executeUpdate();

			String query2 = "DELETE FROM projections WHERE idProjection = ?";
			st2 = conn.prepareStatement(query2);

			st2.setInt(1, projection.getIdProjection());
			st2.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		DBConnection.closeConnection(conn);
	}

	@Override
	public Projection getProjectionByHallDateTime(int idHall, Date date, Time time) {

		Projection projection = null;

		conn=DBConnection.startConnection(conn,schema);
		PreparedStatement st1;
		ResultSet rs1;

		try {
			String query = "SELECT hall_projection.idProjection, projections.movieId, projections.date, projections.time, projections.price FROM hall_projection JOIN projections ON hall_projection.idProjection = projections.idProjection WHERE hall_projection.idHall = ? AND projections.date = ? AND projections.time = ? LIMIT 1";

			st1 = conn.prepareStatement(query);
			st1.setInt(1, idHall);
			st1.setDate(2, date);
			st1.setTime(3, time);

			rs1=st1.executeQuery();

			while(rs1.next()) {
				new Projection(rs1.getInt(1),rs1.getInt(2),rs1.getDate(3),rs1.getTime(4),rs1.getDouble(5));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return projection;
	}

	@Override
	public List<Projection> getAllProjectionsByMovie(int idMovie) {

		List<Projection> projections = new ArrayList<>();

		conn=DBConnection.startConnection(conn,schema);
		PreparedStatement st1;
		ResultSet rs1;

		try {
			String query = "select * from projections where projections.movieId = ?";

			st1 = conn.prepareStatement(query);
			st1.setInt(1, idMovie);

			rs1=st1.executeQuery();

			while (rs1.next()) {
				Projection projection = new Projection(rs1.getInt(1),rs1.getInt(2),rs1.getDate(3),rs1.getTime(4),rs1.getDouble(5));
				projections.add(projection);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		DBConnection.closeConnection(conn);
		return projections;
	}

	@Override
	public int getNumberOfAvailableSeats(Projection projection) {

		int availableSeats = 0;

		conn=DBConnection.startConnection(conn,schema);
		PreparedStatement st1;
		ResultSet rs1;

		try {
			String query = "select cinemahalls.capacity from hall_projection JOIN cinemahalls ON hall_projection.idHall = cinemahalls.idHall WHERE hall_projection.idHall = ?";
			st1 = conn.prepareStatement(query);
			st1.setInt(1, projection.getIdProjection());

			rs1=st1.executeQuery();

			while(rs1.next()) {

				availableSeats = rs1.getInt("capacity");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return availableSeats;
	}

	@Override
	public List<Projection> getAllProjectionsByHall(int idHall) {

		List<Projection> projections = new ArrayList<>();

		conn=DBConnection.startConnection(conn,schema);
		PreparedStatement st1;
		ResultSet rs1;

		try {
			String query = "select hall_projection.idProjection, projections.movieId, projections.date, projections.time, projections.price from hall_projection JOIN projections ON hall_projection.idProjection = projections.idProjection where hall_projection.idHall = ?";

			st1 = conn.prepareStatement(query);
			st1.setInt(1, idHall);

			rs1=st1.executeQuery();

			while (rs1.next()) {

				Projection projection = new Projection(rs1.getInt(1),rs1.getInt(2),rs1.getDate(3),rs1.getTime(4),rs1.getDouble(5));
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
			String query = "select hall_projection.idProjection, projections.movieId, projections.date, projections.time, projections.price from hall_projection JOIN projections on hall_projection.idProjection = projections.idProjection where projections.date = ?";

			st1 = conn.prepareStatement(query);
			st1.setDate(1, date);

			rs1=st1.executeQuery();

			while (rs1.next()) {

				Projection projection = new Projection(rs1.getInt(1),rs1.getInt(2),rs1.getDate(3),rs1.getTime(4),rs1.getDouble(5));
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
			String query = "select hall_projection.idProjection, projections.movieId, projections.date, projections.time, projections.price from hall_projection JOIN projections on hall_projection.idProjection = projections.idProjection where hall_projection.idHall = ? AND projections.date = ?";

			st1 = conn.prepareStatement(query);
			st1.setInt(1, idHall);
			st1.setDate(2, date);

			rs1=st1.executeQuery();

			while(rs1.next()) {
				projections.add(new Projection(rs1.getInt(1),rs1.getInt(2),rs1.getDate(3),rs1.getTime(4),rs1.getDouble(5)));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return projections;
	}


	/* public List<Date> getAllDatesWithAProjection(){
		List<Date> dates = projectionDAO.getAllDatesWithAProjection();
		for(int i=0;i<dates.size();i++) {
			for(int j=i+1;j<dates.size();j++){
				if(dates.get(i).equals(dates.get(j))){
					dates.remove(j);
				}
			}
		}
		return dates;
	} */
}
