package be.vdab;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class eersteJDBC {
	
	private static final String URL = "jdbc:mysql://localhost/tuincentrum?useSSL=false";
	private static final String USER = "cursist";
	private static final String PASSWORD = "cursist";
	private static final String SQL = "SELECT indienst, voornaam, familienaam FROM werknemers WHERE indienst >= {d '2001-1-1'} ORDER BY indienst";
	
	public static void main(String[] args) {		
		
		try (
			Connection connection = DriverManager.getConnection(URL,USER,PASSWORD); 
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(SQL)
			) {	
			
			while (resultSet.next()){
				System.out.printf("%s %s %s%n", resultSet.getDate("indienst"), resultSet.getString("voornaam"), resultSet.getString("familienaam"));
			}


			} catch (SQLException ex) {
				ex.printStackTrace();
			}
	}
}
