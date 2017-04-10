package be.vdab.oefenbundel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Oefenbundel {

	private static final String URL = "jdbc:mysql://localhost/bieren?useSSL=false";
	private static final String USER = "cursist";
	private static final String PASSWORD = "cursist";
	private static final String SQL_TAAK4 ="SELECT naam, verkochtsinds FROM bieren WHERE {fn month(verkochtsinds)} = ? ORDER BY verkochtsinds";
			
	public static void main(String[] args) {
		
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.print("Geef een maand nummer :");
			int maand = scanner.nextInt();
		
			if (maand >= 1 && maand < 13){
				try (
					Connection connection = DriverManager.getConnection(URL,USER,PASSWORD); 
					PreparedStatement statement = connection.prepareStatement(SQL_TAAK4)
					) {
				
						statement.setInt(1, maand);
				
							try (ResultSet resultSet = statement.executeQuery()) {
								while (resultSet.next()) {
									System.out.println(resultSet.getString("naam") + "\t" + resultSet.getDate("verkochtsinds"));
								}
							}
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
}
