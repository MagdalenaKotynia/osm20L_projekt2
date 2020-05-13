package osmProjekt2;

import java.sql.*;
import java.util.logging.Logger;

import org.hsqldb.jdbcDriver;

public class DbConnector {
	
	
	private static final String DRIVER = "org.hsqldb.jdbcDriver";
	private static final String URL = "jdbc:hsqldb:data;create=true";
	private static final String USER = "";
	private static final String PASSWORD="";
	Connection connection = null;
	
	
	public DbConnector() {
		
		try {
			
            Class.forName(DRIVER);
            System.out.println("Sterownik bazy danych zostal uruchominy\n");
			
			
			this.connection = DriverManager.getConnection(URL); //stworzenie połączenia z bazą danych i stworzenie jej samej xd
			if(this.connection!=null) {
				System.out.println("Połączono z bazą danych... ");
			}
			
		}catch(SQLException | ClassNotFoundException e){
			
			
			System.err.println(e.getMessage());
		}
	
	}
	
	
	public void createTable() {
		
		try {
			connection.createStatement().execute( "Create TABLE PatientsTable("+
												  	"patient_id 	INT NOT NULL PRIMARY KEY,"+
												  	"Name 			varchar(50),"+
												  	"Surname 		varchar(50),"+
												  	"Age 			INT," +
												  	"Gender 		char(1),"+
												  	"PESEL 			varchar(11),)");
			System.out.println("Stworzono tabelę pacjentów... \n");
		} catch (SQLException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public void insertIntoTable(int id, String name, String surname, int age, char gender, String pesel) {
		
		try {
			
			connection.createStatement().execute("INSERT INTO PatientsTable(patient_id, Name, Surname, Age, Gender, Pesel) VALUES('"+id+","+name+","+surname+","+age+","+gender+","+pesel+"') ");
			
		} catch (SQLException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public void printAll() {
		Statement statement;
		
		try {
			
			statement = this.connection.createStatement();
			ResultSet res = statement.executeQuery("Select * FROM PatientsTable");
			while (res.next()) {
				System.out.println(res.getString("patient_id") + " " + res.getString("Name") +
						" " + res.getString("Surname") +" " + res.getString("Age") +" "+ 
						res.getString("Gender")+" "+res.getString("PESEL"));
			}
			
		} catch (SQLException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	
	
	
}

