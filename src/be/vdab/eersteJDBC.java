package be.vdab;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class eersteJDBC {
	
	private static final String URL = "jdbc:mysql://localhost/tuincentrum";
	private static final String USER = "cursist";
	private static final String PASSWORD = "cursist";
	private static final String SQL = "SELECT id,naam FROM leveranciers ORDER BY id";
	
	public static void main(String[] args) {
		
		try (
				Connection connection =DriverManager.getConnection(URL,USER,PASSWORD); 
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(SQL)
				) {

			while (resultSet.next()){
			System.out.printf("%4d %s%n", resultSet.getInt(1),resultSet.getString(2));
			}
			
			} catch (SQLException ex) {
				
			ex.printStackTrace();
			
			}
	}


}
