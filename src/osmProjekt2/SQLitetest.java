package osmProjekt2;
import java.sql.*;

public class SQLitetest {

	private static Connection con;
	private static boolean hasData = false;
	
	
	public ResultSet displayUsers() {
		
		if (con==null) {
			getConnection();
		}
		
		Statement state;
		try {
			state = con.createStatement();
			ResultSet res = state.executeQuery("SELECT name, surname FROM patient");
			
			return res;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}


	}


	private void getConnection() {
		
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		try {
			con = DriverManager.getConnection("jdbc:sqlite:SQLiteTest.db");

		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		initialise();
		
		
	}


	private void initialise() {
		
		if(!hasData) {
			hasData = true;
			
			
			try {
				Statement state = con.createStatement();
				ResultSet res = state.executeQuery("SELECT name FROM patient;");


				
				if(!res.next()) {
				
				Statement state1 = con.createStatement();
				state1.execute("CREATE TABLE patient(patient_id integer,"
						+"name varchar(60),"+
						"surname varchar(60),"+
						"age integer,"+
						"gender varchar(10),"+
						"pesel varchar(11) UNIQUE,"+
						"primary key(patient_id)"+
						")");
				
				Statement state2 = con.createStatement();
				state2.execute("CREATE TABLE exam(id integer,"
						+"date real,"+
						"pulse integer,"+
						"pressure integer,"+
						"patient_id integer,"+
						"CONSTRAINT fk_patient,"+
						"FOREIGN KEY (patient_id)"
						+ "REFERENCES patient(patient_id) ON DELETE CASCADE"+
						"primary key(id)"+
						"PRAGMA foreign_keys = ON;"+
						")");
				}
				
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			

			
			

		}
		
	}
	
	public void addUser(String name, String surname, int age, String gender, String pesel) {
		if(con==null) {
			getConnection();
		}
		try {

			PreparedStatement prep = con.prepareStatement("INSERT INTO patient(name, surname, age, gender, pesel) values(?,?,?,?,?);");
			prep.setString(1, name);
			prep.setString(2, surname);
			prep.setInt(3, age);
			prep.setString(4, gender);
			prep.setString(5, pesel);
			prep.execute();
			prep.close();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public void addExam(String date, int pulse, int pressure, int patient_id) {
		
		if(con==null) {
			getConnection();
		}
		
		try {
			PreparedStatement prep = con.prepareStatement("INSERT INTO exam(date, pulse, pressure, patient_id) values(?,?,?,?);");
			prep.setString(1, date);
			prep.setInt(2, pulse);
			prep.setInt(3, pressure);
			prep.setInt(4, patient_id);
			prep.execute();
			prep.close();
			
		}catch(SQLException e) {
			
			e.printStackTrace();
		}
		
		
	}
	
	public void deletePatient(int patientId) { 
		
		if(con==null) {
			getConnection();
		}
		
		try {
			
			PreparedStatement prep = con.prepareStatement("DELETE FROM patient WHERE patient_id=?;");
			prep.setInt(1, patientId);
			prep.execute();
			prep.close();
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	// nie wiem czemu kaskadowe usuwanie nie dziala ale imo to by byl tylko taki bajer mozemy obejsc sie bez tego 
	public void deleteExam(int patientId) {
		
		if(con==null) {
			getConnection();
		}
		
		try {
			
			PreparedStatement prep = con.prepareStatement("DELETE FROM exam WHERE patient_id=?;");
			prep.setInt(1, patientId);
			prep.execute();
			prep.close();
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public void updatePatient(int patientId, String name, String surname, int age, String gender, String pesel) {
		
		if(con==null) {
			getConnection();
		}
		
		try {
			
			PreparedStatement prep = con.prepareStatement("UPDATE patient SET name=?, surname=?, age=?, gender=?, pesel=? WHERE patient_id=?");
			prep.setString(1, name);
			prep.setString(2, surname);
			prep.setInt(3, age);
			prep.setString(4,  gender);
			prep.setString(5,  pesel);
			prep.setInt(6, patientId);
			prep.execute();
			prep.close();
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	
	
	public void updateExam(int patientId, String date, int pulse, int pressure, int id) {
		
		if(con==null) {
			getConnection();
		}
		
		try {
			
			PreparedStatement prep = con.prepareStatement("UPDATE patient SET date=?, pulse=?, pressure=?, patient_id=? WHERE id=?");
			prep.setString(1, date);
			prep.setInt(2, pulse);
			prep.setInt(3, pressure);
			prep.setInt(4,  patientId);
			prep.setInt(5,  id);
			prep.execute();
			prep.close();
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public ResultSet selectPatientsExamCard(int patient_id){
		if(con==null) {
			getConnection();
		}
		
		try {
			PreparedStatement prep = con.prepareStatement("SELECT * FROM exam WHERE patient_id = ?");
			prep.setInt(1,	patient_id);
			ResultSet res = prep.executeQuery();
		
			/*while (res.next())
	        	System.out.println(res.getInt("pressure") + "\t" + res.getInt("patient_id"));
			if(!res.next())
			System.out.println("cos nie pyka");
			prep.execute();
			prep.close();*/
			return res;
		} catch (SQLException e) {
			
			e.printStackTrace();
			return null;
		}
		
	}
	
public void displayPatientsExams(int patientId) {
		
		if (con==null) {
			getConnection();
		}
		
		Statement state;
		try {
			state = con.createStatement();
			
			ResultSet res = state.executeQuery("SELECT * FROM exam WHERE patient_id = 'patientId'");
			
			while (res.next())
	        	System.out.println(res.getInt("id") + "\t" + res.getInt("patient_id"));
			if(!res.next())
			System.out.println("cos nie pyka");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
		
		
	
	
	
	
}
