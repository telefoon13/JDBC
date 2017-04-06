package be.vdab.oefenbundel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Oefenbundel {

	private static final String URL = "jdbc:mysql://localhost/bieren?useSSL=false";
	private static final String USER = "cursist";
	private static final String PASSWORD = "cursist";
	private static final String SQL_TAAK2 ="SELECT brouwers.naam,count(*) AS aantal FROM brouwers INNER JOIN bieren ON brouwers.id=bieren.brouwerid GROUP BY brouwers.id, brouwers.naam ORDER BY brouwers.naam";
			
	public static void main(String[] args) {
		
			try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(SQL_TAAK2)) {

			while (resultSet.next()) {
			System.out.printf("%s %d%n", resultSet.getString("naam"), resultSet.getInt("aantal"));
			}
			
			} catch (SQLException ex) {
			ex.printStackTrace();
			}
	}
	
}
