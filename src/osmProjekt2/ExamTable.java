package osmProjekt2;
import java.sql.*;

public class ExamTable extends DbTable {

	private static ExamTable instance;
	private static final String tableName = "ExamTable";
	private static final String createTable = 
			"Create Cached Table " + tableName + " (" +
			"patientId INTEGER NOT NULL, " +
			"exam_id INTEGER IDENTITY PRIMARY KEY, " +
			"exam_date DATE NOT NULL, " +
			"pulse_value INTEGER NOT NULL, " +
			"pressure_value INTEGER NOT NULL, " +
			"FOREIGN KEY (patientId) REFERENCES PatientTable (patient_id), " +
			"UNIQUE (exam_id)" +
			");";
	
	ExamTable(){
		
		instance = this;
		boolean tableExisted = true;
		
		if(getCount(tableName) == -1) {
			tableExisted = false;
			statementExecute(createTable);
			statementExecute("ALTER TABLE " + tableName + " ALTER COLUMN exam_id RESTART WITH 1;"); // zmiana indeksu by zaczynal sie od 1
		}
		
		
		try {
			
			prstInsert = con.prepareStatement("INSERT INTO " + tableName + " (patientId, exam_id, exam_date, pulse_value, pressure_value) VALUES (?, ?, ?, ?, ?)");
			prstUpdate = con.prepareStatement("UPDATE " + tableName + " SET patientId = ?, exam_date = ?, pulse_value = ?, pressure_value = ? WHERE exam_id = ?;");
			prstDelete = con.prepareStatement("DELETE FROM " + tableName + " WHERE exam_id = ?;");
			prstGetById = con.prepareStatement("SELECT patient_id, exam_date, pulse_value, pressure_value FROM " + tableName + " WHERE exam_id = ?;");
			prstSelectAll = con.prepareStatement("SELECT patient_id, exam_id, exam_date, pulse_value, pressure_value FROM " + tableName + ";");
			
		}catch(SQLException e) {
			
			System.out.println("Problem creating PreparedStatement for " + tableName + ".");
			throw new IllegalStateException("Problem creating PreparedStatement for " + tableName + ".");
			
		}
		
		
		
	}
	
	static ExamTable getInstance() {
		return instance;
	}
	
	boolean sqlInsert(int patientID, int examID, Date examDate, int pulse, int pressure) {
		boolean status = false;
		
		try {
			prstInsert.setInt(1, patientID);
			prstInsert.setNull(2, Types.INTEGER); // .setInt(2, examID) ????
			prstInsert.setDate(3, examDate);
			prstInsert.setInt(4, pulse);
			prstInsert.setInt(5, pressure);
			
			int nbChanged = statementExecute(prstInsert);
			status = nbChanged > 0;
			System.out.println("Statement execute " + prstInsert + " rows affected " + nbChanged + " status " + status);
			
		}catch(SQLException e) {
			
			System.out.println("ExamTable sqlInsert() exception: " + e);	
		}
		return status;
		
	}
	
	boolean sqlUpdate(int patientID, int examID, Date examDate, int pulse, int pressure) {
		boolean status = false;
		
		try {
			
			prstUpdate.setInt(1, patientID);
			prstUpdate.setNull(2, Types.INTEGER); // .setInt(2, examID) ????
			prstUpdate.setDate(3, examDate);
			prstUpdate.setInt(4, pulse);
			prstUpdate.setInt(5, pressure);
			
			int nbChanged = statementExecute(prstUpdate);
			status = nbChanged > 0;
			System.out.println("Statement execute " + prstUpdate + " rows affected " + nbChanged + " status " + status);
			
			
		}catch(SQLException e) {
			System.out.println("ExamTable sqlUpdate() exception: " + e);
		}
		
		
		
		return status;
	}
	
	int getCount() {
		return getCount(tableName);
	}
	
	
	
	
}
