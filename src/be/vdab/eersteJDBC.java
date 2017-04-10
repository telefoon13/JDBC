package be.vdab;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.DatabaseMetaData;

public class eersteJDBC {
	
	private static final String URL = "jdbc:mysql://localhost/tuincentrum?useSSL=false";
	private static final String USER = "cursist";
	private static final String PASSWORD = "cursist";
	
	public static void main(String[] args) {		
		
		try (Connection connection = DriverManager.getConnection(URL,USER,PASSWORD)) {	
			
			DatabaseMetaData metaData = connection.getMetaData();
			System.out.printf("%s %d.%d%n", metaData.getDriverName(), metaData.getDriverMajorVersion(), metaData.getDriverMinorVersion());
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
}
