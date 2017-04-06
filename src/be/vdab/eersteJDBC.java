package be.vdab;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class eersteJDBC {
	
	private static final String URL = "jdbc:mysql://localhost/tuincentrum?useSSL=false";
	private static final String USER = "cursist";
	private static final String PASSWORD = "cursist";
	private static final String SQL = "INSERT INTO soorten(naam) VALUES (?)";
	
	public static void main(String[] args) {		
		
		try( Scanner scanner = new Scanner(System.in)){
			System.out.println("Geef soort namen, type STOP om de invoer te stoppen");
			String naam = scanner.nextLine();
		
		try (
			Connection connection = DriverManager.getConnection(URL,USER,PASSWORD); 
			PreparedStatement statement = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)
			) {

				statement.setString(1, naam);
				statement.executeUpdate();
				
				try(ResultSet resultSet = statement.getGeneratedKeys()){
					resultSet.next();
					System.out.println(resultSet.getLong(1));
				}

			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}
}
