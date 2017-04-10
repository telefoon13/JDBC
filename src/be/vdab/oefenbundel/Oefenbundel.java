package be.vdab.oefenbundel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Oefenbundel {

	private static final String URL = "jdbc:mysql://localhost/bieren?useSSL=false&noAccessToProcedureBodies=true";
	private static final String USER = "cursist";
	private static final String PASSWORD = "cursist";
	private static final String SQL_TAAK6a ="UPDATE bieren SET brouwerid = 2 WHERE brouwerid = 1 AND alcohol >= 8.5";
	private static final String SQL_TAAK6b ="UPDATE bieren SET brouwerid = 3 WHERE brouwerid = 1 AND alcohol < 8.5";
	private static final String SQL_TAAK6c ="DELETE FROM brouwers WHERE id = 1";
			
public static void main(String[] args) {	
		
		try (
			Connection connection = DriverManager.getConnection(URL,USER,PASSWORD);
			Statement statement = connection.createStatement()
		) {	
			
			connection.setAutoCommit(false);
			statement.addBatch(SQL_TAAK6a);
			statement.addBatch(SQL_TAAK6b);
			statement.addBatch(SQL_TAAK6c);
			statement.executeBatch();
			connection.commit();

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
}