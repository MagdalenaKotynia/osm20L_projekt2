package osmProjekt2;

import java.sql.*;
import java.util.logging.Logger;

import org.hsqldb.jdbcDriver;
import java.beans.XMLEncoder;
import java.beans.XMLDecoder;
import java.io.File;

public class DbConnector {
	
	
	private static final String DRIVER = "org.hsqldb.jdbcDriver";
	private static final String URL = "jdbc:hsqldb:data;create=true";
	Connection connection = null;
    DatabaseMetaData	dbmd = null;		//obiekt przechowujacy informacje o bazie danych        	
    Statement			statement=null;		//obiekt wykorzystywany do zapytan do bazy danych
    ResultSet			result=null;
	
	
	public DbConnector() {
		
		try {
			
            Class.forName(DRIVER);
            System.out.println("Sterownik bazy danych zostal uruchominy\n");
			
			
			this.connection = DriverManager.getConnection(URL); //stworzenie połączenia z bazą danych i stworzenie jej samej xd
			if(this.connection!=null) {
				System.out.println("Połączono z bazą danych... ");
			}
			
			dbmd = connection.getMetaData();
			statement=connection.createStatement();
			
		}catch(SQLException | ClassNotFoundException e){
			
			
			System.err.println(e.getMessage());
		}

	}
	
	
	public void createTable() {
		
		try {
			
			result=dbmd.getTables(null,null,"PatientsTable",null);
			if (!result.next()) {
				
				statement.execute( "CREATE TABLE Exams("+
									"patient_id int NOT NULL PRIMARY KEY,"+
									"pulse_id int NOT NULL,"+
									"pressure_id int NOT NULL,)");
			}
			
			
			
			
			result=dbmd.getTables(null,null,"PatientsTable",null);
			if (!result.next()) {
            
								statement.execute( "Create TABLE PatientsTable("+
												  	"patient_id 	int NOT NULL PRIMARY KEY,"+
												  	"Name 			varchar(50) NOT NULL,"+
												  	"Surname 		varchar(50) NOT NULL,"+
												  	"Age 			INT NOT NULL," +
												  	"Gender 		varchar(10) NOT NULL,"+
												  	"PESEL 			varchar(11) NOT NULL UNIQUE,)");
								System.out.println("Stworzono tabelę pacjentów... \n");
								}else {
									System.out.println("Tabela Pacjentów juz istnieje");
								}
						
		} catch (SQLException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public void insertIntoTable(int id, String name, String surname, int age, String gender, String pesel) {
		
		try {
			
			PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO PatientsTable VALUES(?,?,?,?,?,?)");
			preparedStatement.setInt(1, id);
			preparedStatement.setString(2, name);
			preparedStatement.setString(3, surname);
			preparedStatement.setInt(4, age);
			preparedStatement.setString(5,  gender);
			preparedStatement.setString(6,  pesel);
			int x = preparedStatement.executeUpdate();
			
			
			
		} catch (SQLException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public void printAll() {
		
		
		try {
			
			
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

