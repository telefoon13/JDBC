package be.vdab;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class eersteJDBC {
	
	private static final String URL = "jdbc:mysql://localhost/tuincentrum";
	private static final String USER = "cursist";
	private static final String PASSWORD = "cursist";
	private static final String UPDATE_SQL = "UPDATE planten SET verkoopprijs = verkoopprijs * 1.1";
	
	public static void main(String[] args) {
		
		try (Connection connection =DriverManager.getConnection(URL,USER,PASSWORD); Statement statement = connection.createStatement()) {

			System.out.println(statement.executeUpdate(UPDATE_SQL));
			
			} catch (SQLException ex) {
				
			ex.printStackTrace();
			
			}
	}


}
