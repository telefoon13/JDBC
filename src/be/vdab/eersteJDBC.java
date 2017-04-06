package be.vdab;

import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.sql.PreparedStatement;

public class eersteJDBC {
	
	private static final String URL = "jdbc:mysql://localhost/tuincentrum?useSSL=false";
	private static final String USER = "cursist";
	private static final String PASSWORD = "cursist";
	private static final String SQL = "INSERT INTO soorten(naam) VALUES (?)";
	
	public static void main(String[] args) {
		
		List<String> namen = new ArrayList<>();
		
		try( Scanner scanner = new Scanner(System.in)){
			System.out.println("Geef soort namen, type STOP om de invoer te stoppen");
			for (String naam; ! "stop".equalsIgnoreCase(naam = scanner.nextLine()) ; namen.add(naam));
		}
		
		try (
			Connection connection = DriverManager.getConnection(URL,USER,PASSWORD); 
			PreparedStatement statement = connection.prepareStatement(SQL)
			) {

			for(String naam : namen){
				statement.setString(1, naam);
				System.out.printf("%d %s%n", statement.executeUpdate(), naam);
			}
			
			} catch (SQLException ex) {
				
			ex.printStackTrace();
			
			}
		}
	}
