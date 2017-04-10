package be.vdab;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class eersteJDBC {
	
	private static final String URL = "jdbc:mysql://localhost/tuincentrum?useSSL=false";
	private static final String USER = "cursist";
	private static final String PASSWORD = "cursist";
	private static final String SQL = "SELECT id, voornaam, indienst FROM werknemers";
	
	public static void main(String[] args) {		
		
		try (
			Connection connection = DriverManager.getConnection(URL,USER,PASSWORD);
			Statement statement = connection.createStatement()
			) {	
			
			try( ResultSet resultSet = statement.executeQuery(SQL)){
				ResultSetMetaData metaData = resultSet.getMetaData();
				
				for( int index = 1 ; index <= metaData.getColumnCount() ; index++){
					System.out.printf("%s %s%n", metaData.getColumnName(index), metaData.getColumnTypeName(index));
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
}
