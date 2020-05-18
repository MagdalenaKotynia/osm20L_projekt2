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
    DatabaseMetaData	dbmd = null;		       	
    Statement			statement=null;		
    ResultSet			result=null;
	
	
	public DbConnector() {
		
		try {
			
            Class.forName(DRIVER);
            System.out.println("Sterownik bazy danych zostal uruchominy\n");
			
			
			this.connection = DriverManager.getConnection(URL); 
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
            
								statement.execute( "Create TABLE PatientsTable("+
												  	"patient_id IDENTITY(1,1) PRIMARY KEY,"+
												  	"Name 		VARCHAR(50) NOT NULL,"+
												  	"Surname 	VARCHAR(50) NOT NULL,"+
												  	"Age 		INTEGER NOT NULL," +
												  	"Gender 	VARCHAR(10) NOT NULL,"+
												  	"PESEL 		VARCHAR(11) NOT NULL UNIQUE,)");
								System.out.println("Stworzono tabelę pacjentów... \n");
								}else {
									System.out.println("Tabela Pacjentów juz istnieje...");
								}
						
		} catch (SQLException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		

		try {
			
			result=dbmd.getTables(null,null,"Exams",null);
			if (!result.next()) {
				
				statement.execute( "CREATE TABLE Exams("+
									"patient_id INTEGER NOT NULL,"+
									"exam_id INTEGER IDENTITY PRIMARY KEY,"+
									"exam_date DATE NOT NULL,"+
									"pulse_value INTEGER NOT NULL,"+
									"pressure_value INTEGER NOT NULL,)");
				
				statement.execute("ALTER TABLE Exams ADD FOREIGN KEY (patient_id) REFERENCES PatientsTable(patient_id)");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	public void insertIntoTable(String name, String surname, int age, String gender, String pesel) {
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO PatientsTable VALUES(default,name,surname,age,gender,pesel)");
			//preparedStatement.setInt(1, );
			/*preparedStatement.setString(1, name);
			preparedStatement.setString(2, surname);
			preparedStatement.setInt(3, age);
			preparedStatement.setString(4,  gender);
			preparedStatement.setString(5,  pesel);*/
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

