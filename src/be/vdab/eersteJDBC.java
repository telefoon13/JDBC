package be.vdab;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class eersteJDBC {
	
	private static final String URL = "jdbc:mysql://localhost/tuincentrum";
	private static final String USER = "root";
	private static final String PASSWORD = "vdab";
	
	public static void main(String[] args) {
		
		Connection connection = null;
		
		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Connectie gemaakt");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try{
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}


}
