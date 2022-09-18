package com.land;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class landDBUtil {
	
	private static String url = "jdbc:mysql://localhost:3306/property?useSSL=false";
	private static String userName = "root";
	private static String password = "shaggy2175";
	
	private static final String INSERT_LANDS_SQL = "INSERT INTO propertydb" + " (type, description, price) VALUES " 
													+ " (?, ?, ?);";
	private static final String SELECT_LAND_BY_ID = "SELECT id,type,description,price FROM propertydb WHERE id =?";
	private static final String SELECT_ALL_LANDS = "SELECT * FROM propertydb";
	private static final String DELETE_LANDS_SQL = "DELETE FROM propertydb WHERE id = ?;";
	private static final String UPDATE_LANDS_SQL = "UPDATE propertydb set type = ?, description = ?, price = ? WHERE id = ?;";
	
	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(url, userName, password);
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	public void insertLand(land land) throws SQLException{
		try(Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_LANDS_SQL)){
			preparedStatement.setString(1, land.getType());
			preparedStatement.setString(2, land.getDescription());
			preparedStatement.setString(3, land.getPrice());
			preparedStatement.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public boolean updateLand(land land) throws SQLException{
		boolean rowUpdated;
		try(Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_LANDS_SQL);){
			statement.setString(1, land.getType());
			statement.setString(2, land.getDescription());
			statement.setString(3, land.getPrice());
			statement.setInt(4, land.getId());
			
			rowUpdated = statement.executeUpdate()>0;
			
		}
		return rowUpdated;
	}
	
	public land selectLand(int id) {
	land land = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_LAND_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String type = rs.getString("type");
				String description = rs.getString("description");
				String price = rs.getString("price");
				land = new land(id, type, description, price);
			}
		} catch (SQLException e) {
			e.printStackTrace();;
		}
		return land;
	}
	
	
	public List<land> selectAllLands() {
		List<land> lands = new ArrayList<>();
			// Step 1: Establishing a Connection
			try (Connection connection = getConnection();
					// Step 2:Create a statement using connection object
					PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_LANDS);) {
				
				System.out.println(preparedStatement);
				// Step 3: Execute the query or update query
				ResultSet rs = preparedStatement.executeQuery();

				// Step 4: Process the ResultSet object.
				while (rs.next()) {
					int id = rs.getInt("id");
					String type = rs.getString("type");
					String description = rs.getString("description");
					String price = rs.getString("price");
					lands.add(new land(id, type, description, price));
				}
			} catch (SQLException e) {
				e.printStackTrace();;
			}
			return lands;
		}
	
	
	public boolean deleteLand(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_LANDS_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}
	
	
	
}
