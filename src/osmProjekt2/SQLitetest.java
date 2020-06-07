package osmProjekt2;
import java.sql.*;

import javax.swing.JOptionPane;

import org.jfree.data.jdbc.JDBCCategoryDataset;

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


	private Connection getConnection() {
		
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
		return con;
		
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
						+"name varchar(60) not null,"+
						"surname varchar(60) not null,"+
						"age integer not null,"+
						"gender varchar(10) not null,"+
						"pesel varchar(11) UNIQUE not null,"+
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
		} catch (SQLException e) {
			
			//e.printStackTrace();
			JOptionPane.showMessageDialog(null, 
                    "Patient with given PESEL already exists", 
                    "ALERT!", 
                    JOptionPane.WARNING_MESSAGE);
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
			
		}catch(SQLException e) {
			
			e.printStackTrace();
		}

	}
	
	public void deletePatient(String pesel) { 
		
		if(con==null) {
			getConnection();
		}
		
		try {
			
			PreparedStatement prep = con.prepareStatement("DELETE FROM patient WHERE pesel=?;");
			prep.setString(1, pesel);
			prep.execute();
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}

	public void deleteExams(int patientId) {
		
		if(con==null) {
			getConnection();
		}
		
		try {
			
			PreparedStatement prep = con.prepareStatement("DELETE FROM exam WHERE patient_id=?;");
			prep.setInt(1, patientId); 
			prep.execute();
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	public void deleteExam(String date, int pulse, int pressure) {
		if(con==null) {
			getConnection();
		}
		
		try {
			PreparedStatement prep = con.prepareStatement("DELETE FROM exam WHERE date=?;");
			//AND pulse=? AND pressure=?
			prep.setString(1, date); 
			//prep.setInt(2, pulse);
			//prep.setInt(3, pressure);
			prep.execute();	
			
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
			return res;
		} catch (SQLException e) {
			
			e.printStackTrace();
			return null;
		}
		
	}
	
	public ResultSet getSelectedPatientsId(String pesel) {
		if(con==null) {
			getConnection();
		}
		try {
			PreparedStatement prep = con.prepareStatement("SELECT patient_id FROM patient WHERE pesel = ?");
			prep.setString(1,	pesel);
			ResultSet res = prep.executeQuery();
			return res;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public int getPatientRowCount() {
		
		if(con==null) {
			getConnection();
		}
		
		try {
			
			PreparedStatement prep = con.prepareStatement("SELECT COUNT(pesel) FROM patient");
			
			ResultSet res = prep.executeQuery();

			
			int sum = res.getInt("COUNT(pesel)");
			return sum;
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			return 0;
		}
				
	}
	
	public int getExamRowCount() {
		
		if(con==null) {
			getConnection();
		}
		
		try {
			
			PreparedStatement prep = con.prepareStatement("SELECT COUNT(id) FROM exam");
			
			ResultSet res = prep.executeQuery();

			
			int sum = res.getInt("COUNT(id)");
			return sum;
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			return 0;
		}
		
	}
	
	
	public ResultSet getPatient() {
		
		if(con==null) {
			getConnection();
		}
		
		try {
			
			PreparedStatement prep = con.prepareStatement("SELECT * FROM patient");
			ResultSet res = prep.executeQuery();
			return res;
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			return null;
		}
		
		
		
	}

	
	public ResultSet getExam() {
		
		if(con==null) {
			getConnection();
		}
		
		try {
			
			PreparedStatement prep = con.prepareStatement("SELECT * FROM exam");
			ResultSet res = prep.executeQuery();
			return res;
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			return null;
		}
		
		
	}
	
	public ResultSet getExamsPatient(int examId) {
		if(con==null) {
			getConnection();
		}
		
		try {
			PreparedStatement prep = con.prepareStatement("SELECT * FROM exam where id = ?");
			
			
		//	PreparedStatement prep = con.prepareStatement("SELECT * FROM patient where");
			ResultSet res = prep.executeQuery();
			return res;
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			return null;
		}
		
	}
	
	public String getMinPulse(String pesel) {
		
		if(con==null) {
			getConnection();
		}
		
		try {
	
			ResultSet index = getSelectedPatientsId(pesel);
			int patient_id = index.getInt("patient_id");			
			PreparedStatement prep = con.prepareStatement("SELECT pulse FROM exam WHERE patient_id=? ORDER BY pulse ASC");
			prep.setInt(1, patient_id);
			ResultSet res = prep.executeQuery();
			String pulse = null;
			while (res.next()) {
			pulse =res.getString("pulse");
			}
			
			return pulse;
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			return null;
		}
				
	}
	
	
	public String getMaxPulse(String pesel) {
		
		if(con==null) {
			getConnection();
		}
		
		try {
	
			ResultSet index = getSelectedPatientsId(pesel);
			int patient_id = index.getInt("patient_id");			
			PreparedStatement prep = con.prepareStatement("SELECT pulse FROM exam WHERE patient_id=? ORDER BY pulse DESC");
			prep.setInt(1, patient_id);
			ResultSet res = prep.executeQuery();
			String pulse = null;
			while (res.next()) {
			pulse =res.getString("pulse");	
			}
			
			return pulse;
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			return null;
		}
				
	}
	
	public String getMinPressure(String pesel) {
			
			if(con==null) {
				getConnection();
			}
			
			try {
		
				ResultSet index = getSelectedPatientsId(pesel);
				int patient_id = index.getInt("patient_id");			
				PreparedStatement prep = con.prepareStatement("SELECT pressure FROM exam WHERE patient_id=? ORDER BY pressure ASC");
				prep.setInt(1, patient_id);
				ResultSet res = prep.executeQuery();
				String pressure = null;
				while (res.next()) {
				pressure =res.getString("pressure");	
				}
				
				return pressure;
				
				
			} catch (SQLException e) {
				
				e.printStackTrace();
				return null;
			}
					
		}
	
	public String getMaxPressure(String pesel) {
		
		if(con==null) {
			getConnection();
		}
		
		try {
	
			ResultSet index = getSelectedPatientsId(pesel);
			int patient_id = index.getInt("patient_id");			
			PreparedStatement prep = con.prepareStatement("SELECT pressure FROM exam WHERE patient_id=? ORDER BY pressure DESC");
			prep.setInt(1, patient_id);
			ResultSet res = prep.executeQuery();
			String pressure = null;
			while (res.next()) {
			pressure =res.getString("pressure");	
			}
			return pressure;
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			return null;
		}
				
	}
	
	public String getAvgPulse(String pesel) {
		
		if(con==null) {
			getConnection();
		}
		float average = 0;
		float iter = 0;
		
		try {
	
			ResultSet index = getSelectedPatientsId(pesel);
			int patient_id = index.getInt("patient_id");			
			PreparedStatement prep = con.prepareStatement("SELECT pulse FROM exam WHERE patient_id=?");
			prep.setInt(1, patient_id);
			ResultSet res = prep.executeQuery();
			
			while(res.next()) {
				
			average=res.getFloat("pulse") + average; 
			iter++;
				
			}
			
			average=average/iter;
			
			String pulse = String.valueOf(average);	

			return pulse;
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			return null;
		}
		
		
	}
	
	public String getAvgPressure(String pesel) {
		
		if(con==null) {
			getConnection();
		}
		float average = 0;
		float iter = 0;
		
		try {
	
			ResultSet index = getSelectedPatientsId(pesel);
			int patient_id = index.getInt("patient_id");			
			PreparedStatement prep = con.prepareStatement("SELECT pressure FROM exam WHERE patient_id=?");
			prep.setInt(1, patient_id);
			ResultSet res = prep.executeQuery();
			while(res.next()) {
				
			average=res.getFloat("pressure") + average; 
			iter++;
			}
			
			average=average/iter;
			
			String pressure = String.valueOf(average);	
			if (pressure == null) {
				pressure = "";
			}
			return pressure;
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			return null;
		}
		
		
	}
	
	
	public JDBCCategoryDataset getPressureDataSet(String pesel) {
		
		if(con==null) {
			getConnection();
		}
		
		
		
		
		try {
			
			ResultSet index = getSelectedPatientsId(pesel);
			int patient_id = index.getInt("patient_id");
			String sql = "SELECT date, pressure FROM exam WHERE patient_id="+String.valueOf(patient_id)+" ORDER BY date ASC"; 
			JDBCCategoryDataset dataset = new JDBCCategoryDataset(this.con, sql);
			//index.close();
			return dataset;
			
			
			
		}
		catch(SQLException e) {
			
			return null;
			
		}
		/*finally {
			  if (con != null) {
			    try {
			      con.close(); // <-- This is important
			    } catch (SQLException e) {
			      
			    }
			  }
		}*/
		
			
	}
	
	
	
	public JDBCCategoryDataset getPulseDataSet(String pesel) {
		
		if(con==null) {
			getConnection();
		}
		
		
		
		
		try {
			
			ResultSet index = getSelectedPatientsId(pesel);
			int patient_id = index.getInt("patient_id");
			String sql = "SELECT date, pulse FROM exam WHERE patient_id="+String.valueOf(patient_id)+" ORDER BY date ASC"; 
			JDBCCategoryDataset dataset = new JDBCCategoryDataset(this.con,sql);
			return dataset;
			
			
			
		}catch(SQLException e) {
			
			return null;
			
		}
		
			
	}
	
	
	

		
		
	
	
	
	
}
