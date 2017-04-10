package be.vdab;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class eersteJDBC {
	
	private static final String URL = "jdbc:mysql://localhost/tuincentrum?useSSL=false";
	private static final String USER = "cursist";
	private static final String PASSWORD = "cursist";
	private static final String SQL1 = "UPDATE planten SET verkoopprijs = verkoopprijs * 1.1 WHERE verkoopprijs >= 100";
	private static final String SQL2 = "UPDATE planten SET verkoopprijs = verkoopprijs * 1.05 WHERE verkoopprijs < 100";
	
	public static void main(String[] args) {	
		
		try (
			Connection connection = DriverManager.getConnection(URL,USER,PASSWORD);
			Statement statement = connection.createStatement()
		) {	
			
			connection.setAutoCommit(false);
			statement.executeUpdate(SQL1);
			statement.executeUpdate(SQL2);
			connection.commit();

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
}