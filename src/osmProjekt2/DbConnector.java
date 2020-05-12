package osmProjekt2;

import java.sql.*;

public class DbConnector {
	
	
	private static String URL = "jdbc:hsql:db;create=true";
	private static String USER = "";
	private static String PASSWORD="";
	
	
	public DbConnector() {
		
		try {
			Connection connection = null;
			connection = DriverManager.getConnection(URL); //stworzenie połączenia z bazą danych i stworzenie jej samej xd
		
		}catch(SQLException e){
		
			System.err.println(e.getMessage());
		}
	
	}
	
	
}

