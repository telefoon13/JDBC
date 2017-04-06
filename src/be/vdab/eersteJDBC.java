package be.vdab;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class eersteJDBC {
	
	private static final String URL = "jdbc:mysql://localhost/tuincentrum?useSSL=false";
	private static final String USER = "cursist";
	private static final String PASSWORD = "cursist";
	private static final String SQL = "select id, naam, woonplaats from leveranciers where woonplaats = 'Wevelgem'";
	
	public static void main(String[] args) {
		
		try (
				Connection connection =DriverManager.getConnection(URL,USER,PASSWORD); 
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(SQL)
				) {

			int aantalLeveranciers = 0;
			while (resultSet.next()){
				++aantalLeveranciers;
				System.out.printf("%4d %s%n", resultSet.getInt("id"), resultSet.getString("naam"));
			}
			
			System.out.printf("%d leverancier(s) van Wevelgem", aantalLeveranciers);
			
			} catch (SQLException ex) {
				
			ex.printStackTrace();
			
			}
	}


}
