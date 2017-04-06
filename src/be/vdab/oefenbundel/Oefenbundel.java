package be.vdab.oefenbundel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Oefenbundel {

	private static final String URL = "jdbc:mysql://localhost/bieren";
	private static final String USER = "cursist";
	private static final String PASSWORD = "cursist";
	private static final String UPDATE_SQL_TAAK1 = "DELETE FROM bieren WHERE alcohol IS NULL";
	
	public static void main(String[] args) {
		
		try (Connection connection =DriverManager.getConnection(URL,USER,PASSWORD); Statement statement = connection.createStatement()) {

			System.out.println(statement.executeUpdate(UPDATE_SQL_TAAK1));
			
			} catch (SQLException ex) {
				
			ex.printStackTrace();
			
			}
	}
	
}
