package be.vdab.oefenbundel;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Oefenbundel {

	private static final String URL = "jdbc:mysql://localhost/bieren?useSSL=false&noAccessToProcedureBodies=true";
	private static final String USER = "cursist";
	private static final String PASSWORD = "cursist";
	private static final String SQL_TAAK5 ="{call VanTotAlcohol2(?,?)}";
			
	public static void main(String[] args) {
		
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.print("Min :");
			BigDecimal min = scanner.nextBigDecimal();
			System.out.print("Max :");
			BigDecimal max = scanner.nextBigDecimal();
		
			try (
					Connection connection = DriverManager.getConnection(URL,USER,PASSWORD); 
					CallableStatement statement = connection.prepareCall(SQL_TAAK5)
					) {
				
				statement.setBigDecimal(1, min);
				statement.setBigDecimal(2, max);
				
				try (ResultSet resultSet = statement.executeQuery()) {
					while (resultSet.next()) {
					System.out.println(resultSet.getString("naam") + "\t" + resultSet.getBigDecimal("alcohol"));
					}
				}
		}
			} catch (SQLException ex) {
			ex.printStackTrace();
			}
	}
	
}