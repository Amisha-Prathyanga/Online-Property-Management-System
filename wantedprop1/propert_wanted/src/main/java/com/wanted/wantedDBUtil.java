package com.wanted;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;




public class wantedDBUtil {
	
	
	private static String url = "jdbc:mysql://localhost:3306/property?useSSL=false";
	private static String userName = "root";
	private static String password = "shaggy2175";
	
	private static final String INSERT_WANTED_SQL = "INSERT INTO wanted" + " (type, description, price, name, phone, location) VALUES " 
													+ " (?, ?, ?, ?, ?, ?);";
	private static final String SELECT_WANTED_BY_ID = "SELECT id,type,description,price,name,phone,location FROM wanted WHERE id =?";
	private static final String SELECT_ALL_WANTED = "SELECT * FROM wanted";
	private static final String DELETE_WANTED_SQL = "DELETE FROM wanted WHERE id = ?;";
	private static final String UPDATE_WANTED_SQL = "UPDATE wanted set type = ?, description = ?, price = ?, name = ?, phone = ?, location = ? WHERE id = ?;";
	
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
	
	public void insertWanted(wanted wanted) throws SQLException{
		try(Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_WANTED_SQL)){
			preparedStatement.setString(1, wanted.getType());
			preparedStatement.setString(2, wanted.getDescription());
			preparedStatement.setString(3, wanted.getPrice());
			preparedStatement.setString(4, wanted.getName());
			preparedStatement.setString(5, wanted.getPhone());
			preparedStatement.setString(6, wanted.getLocation());
			preparedStatement.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean updateWanted(wanted wanted) throws SQLException{
		boolean rowUpdated;
		try(Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_WANTED_SQL);){
			statement.setString(1, wanted.getType());
			statement.setString(2, wanted.getDescription());
			statement.setString(3, wanted.getPrice());
			statement.setString(4, wanted.getName());
			statement.setString(5, wanted.getPhone());
			statement.setString(6, wanted.getLocation());
			statement.setInt(7, wanted.getId());
			
			rowUpdated = statement.executeUpdate()>0;
			
		}
		return rowUpdated;
	}
	
	public wanted selectWanted(int id) {
		wanted wanted = null;
			// Step 1: Establishing a Connection
			try (Connection connection = getConnection();
					// Step 2:Create a statement using connection object
					PreparedStatement preparedStatement = connection.prepareStatement(SELECT_WANTED_BY_ID);) {
				preparedStatement.setInt(1, id);
				System.out.println(preparedStatement);
				// Step 3: Execute the query or update query
				ResultSet rs = preparedStatement.executeQuery();

				// Step 4: Process the ResultSet object.
				while (rs.next()) {
					String type = rs.getString("type");
					String description = rs.getString("description");
					String price = rs.getString("price");
					String name= rs.getString("name");
					String phone = rs.getString("phone");
					String location = rs.getString("location");
					wanted = new wanted(id, type, description, price, name, phone, location);
				}
			} catch (SQLException e) {
				e.printStackTrace();;
			}
			return wanted;
		}
		
		
		public List<wanted> selectAllWanted() {
			List<wanted> wanteds = new ArrayList<>();
				// Step 1: Establishing a Connection
				try (Connection connection = getConnection();
						// Step 2:Create a statement using connection object
						PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_WANTED);) {
					
					System.out.println(preparedStatement);
					// Step 3: Execute the query or update query
					ResultSet rs = preparedStatement.executeQuery();

					// Step 4: Process the ResultSet object.
					while (rs.next()) {
						int id = rs.getInt("id");
						String type = rs.getString("type");
						String description = rs.getString("description");
						String price = rs.getString("price");
						String name= rs.getString("name");
						String phone = rs.getString("phone");
						String location = rs.getString("location");
						wanteds.add(new wanted(id, type, description, price,  name, phone, location));
					}
				} catch (SQLException e) {
					e.printStackTrace();;
				}
				return wanteds;
			}
		
		public boolean deleteWanted(int id) throws SQLException {
			boolean rowDeleted;
			try (Connection connection = getConnection();
					PreparedStatement statement = connection.prepareStatement(DELETE_WANTED_SQL);) {
				statement.setInt(1, id);
				rowDeleted = statement.executeUpdate() > 0;
			}
			return rowDeleted;
		}
}
