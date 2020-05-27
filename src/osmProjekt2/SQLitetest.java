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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			con = DriverManager.getConnection("jdbc:sqlite:SQLiteTest.db");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
				
				//if (!res.next()) {
				//	System.out.println("Tworzenie tabeli...");
				//}
				
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
						+ "REFERENCES patient(patient_id)"+
						"primary key(id)"+
						")");
				}
				
				// sample data
				
	/*			PreparedStatement prep = con.prepareStatement("INSERT INTO patient(name, surname, age, gender, pesel) values(?,?,?,?,?);");
				prep.setString(1, "Jan");
				prep.setString(2, "Kowalski");
				prep.setInt(3,  23);
				prep.execute();
				
				PreparedStatement prep2 = con.prepareStatement("INSERT INTO patient(name, surname, age) values(?,?,?);");
				prep2.setString(1, "Zbigniew");
				prep2.setString(2, "Wawrzyniak");
				prep2.setInt(3,  55);
				prep2.execute();*/
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
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
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
}
