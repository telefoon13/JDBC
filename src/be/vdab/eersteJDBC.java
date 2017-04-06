package be.vdab;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.PreparedStatement;

public class eersteJDBC {
	
	private static final String URL = "jdbc:mysql://localhost/tuincentrum?useSSL=false";
	private static final String USER = "cursist";
	private static final String PASSWORD = "cursist";
	private static final String SQL = "select id, naam, woonplaats from leveranciers where woonplaats = ?";
	
	public static void main(String[] args) {
		
		try( Scanner scanner = new Scanner(System.in)){
			System.out.print("Woonplaats : ");
			String woonplaats;
			woonplaats = scanner.nextLine();
		
		
		try (
				Connection connection =DriverManager.getConnection(URL,USER,PASSWORD); 
				PreparedStatement statement = connection.prepareStatement(SQL)
				) {

			statement.setString(1, woonplaats);
			try(ResultSet resultSet = statement.executeQuery()){
			while (resultSet.next()){
				System.out.println(resultSet.getString("naam"));
			}
			}
			} catch (SQLException ex) {
				
			ex.printStackTrace();
			
			}
		}
	}


}
