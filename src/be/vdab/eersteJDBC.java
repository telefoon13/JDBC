package be.vdab;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class eersteJDBC {
	
	private static final String URL = "jdbc:mysql://localhost/tuincentrum?useSSL=false&noAccessToProcedureBodies=true";
	private static final String USER = "cursist";
	private static final String PASSWORD = "cursist";
	private static final String CALL = "{call PlantenMetEenWoord(?)}";
	
	public static void main(String[] args) {	
		
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.print("Geef een woord :");
			String woord = scanner.nextLine();
		
			try (
				Connection connection = DriverManager.getConnection(URL,USER,PASSWORD);
				CallableStatement statement = connection.prepareCall(CALL)
				) {	
				
				statement.setString(1, '%' + woord + '%');
			
				try( ResultSet resultSet = statement.executeQuery()){
					int resultaten = 0;
					
					while(resultSet.next()){
						System.out.println(resultSet.getString("naam"));
						resultaten = 1;
					}
					
					if (resultaten == 0){
						System.out.println("Geen resultaten");
					}
				
				
			}
		}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
}