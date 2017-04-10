package be.vdab;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class eersteJDBC {
	
	private static final String URL = "jdbc:mysql://localhost/tuincentrum?useSSL=false";
	private static final String USER = "cursist";
	private static final String PASSWORD = "cursist";
	private static final String SQL1 = "SELECT verkoopprijs FROM planten WHERE id = ?";
	private static final String SQL2 = "UPDATE planten SET verkoopprijs = ? WHERE id = ?";
	
	public static void main(String[] args) {	
		
		try(Scanner scanner = new Scanner(System.in)){
			System.out.print("ID : ");
			int id = scanner.nextInt();
			System.out.print("Prijs : ");
			BigDecimal prijs = scanner.nextBigDecimal();
			
			try(
			Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement statement1 = connection.prepareStatement(SQL1);
			PreparedStatement statement2 = connection.prepareStatement(SQL2)
			){
				connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
				statement1.setInt(1, id);
				connection.setAutoCommit(false);
				
				try(ResultSet resultSet = statement1.executeQuery()){
					
					if (resultSet.next()){
						BigDecimal oudePrijs = resultSet.getBigDecimal("verkoopprijs");
						
						if (prijs.compareTo(oudePrijs.multiply(BigDecimal.valueOf(1.1))) <= 0 ){
							statement2.setBigDecimal(1, prijs);
							statement2.setInt(2, id);
							statement2.executeUpdate();
							connection.commit();
						} else {
							System.err.println("Nieuwe verkoopprijs is te hoog");
						}
					} else {
						System.err.println("Plant niet gevonden");
					}
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}