package osmProjekt2;

import java.sql.*;
import java.util.*;

public class PatientTable extends DbTable {
	
	private static PatientTable instance;
	private static final String tableName = "PatientTable";
	
	private static final String createTable =
			"Create Cached Table " + tableName + " (" +
			"patient_id INTEGER IDENTITY PRIMARY KEY, " +
			"Name 		VARCHAR(50) NOT NULL, " +
			"Surname 	VARCHAR(50) NOT NULL, " +
			"Age 		INTEGER NOT NULL, " +
			"Gender 	VARCHAR(10) NOT NULL, " +
			"PESEL 		VARCHAR(11) NOT NULL, " +
			"UNIQUE (PESEL), " +
			");";
	
	
	PatientTable(){
		instance = this;
		boolean tableExisted = true; // kontrola czy tablica istniala
		
		if(getCount() == -1) {
			
			tableExisted = false;
			statementExecute(createTable);
			statementExecute("ALTER TABLE " + tableName + " ALTER COLUMN patient_id RESTART WITH 1;");  // id tablicy zaczyna sie od 1 zamiast 0 
			
		}
		
		// stworzenie prst
		
		try {
			
			
			prstInsert = con.prepareStatement("INSERT INTO " + tableName + " (patient_id, Name, Surname, Age, Gender, PESEL) VALUES (?, ?, ?, ?, ?, ?)");
            prstUpdate = con.prepareStatement("UPDATE " + tableName + " SET Name = ?, Surname = ?, Age = ?, Gender = ?, PESEL = ? WHERE patient_id = ?;");
            prstDelete = con.prepareStatement("DELETE FROM " + tableName + " WHERE patient_id = ?;");
            prstGetById = con.prepareStatement("SELECT Name, Surname, Age, Gender, PESEL FROM " + tableName + " WHERE patient_id = ?;");
            prstSelectAll = con.prepareStatement("SELECT Name, Surname, Age, Gender, PESEL, patient_id FROM " + tableName + " ORDER BY Name;");
			
		} catch (SQLException e) {

			System.out.println("Problem creating PreparedStatement for " + tableName + ".");
			throw new IllegalStateException("Problem creating PreparedStatement for " + tableName + ".");
				
		}
		
		
		
		
	}
	
	
	boolean sqlInsert(int id, String name, String surname, int age, String gender, String pesel) {
		boolean status = false;
		
		try {
			 prstInsert.setInt(1, id);
			 prstInsert.setString(2, name);
			 prstInsert.setString(3, surname);
			 prstInsert.setInt(4, age);
			 prstInsert.setString(5, gender);
			 prstInsert.setString(6, pesel);
			
			 int nbChanged = statementExecute(prstInsert);
			 status = nbChanged > 0;
			 System.out.println("Statement execute " + prstInsert + " rows affected " + nbChanged + " status " + status);
		}catch(SQLException e) {
			System.out.println("FirstNameTable sqlInsert() exception: " + e);
		}
		return status;
		
	}
	
	boolean sqlUpdate(int id, String name, String surname, int age, String gender, String pesel) {
		 boolean status = false;
		
		try {
			 prstUpdate.setInt(1, id);
			 prstUpdate.setString(2, name);
			 prstUpdate.setString(3, surname);
			 prstUpdate.setInt(4, age);
			 prstUpdate.setString(5, gender);
			 prstUpdate.setString(6, pesel);
			
			 int nbChanged = statementExecute(prstUpdate);
			 status = nbChanged > 0;
			 System.out.println("Statement execute " + prstUpdate + " rows affected " + nbChanged + " status " + status);
		}catch(SQLException e) {
			System.out.println("FirstNameTable sqlUpdate() exception: " + e);
		}
		return status;
		
	}
	
	
	static PatientTable getInstance() {
		return instance;
	}
	
	
	
	int getCount() {
		return getCount(tableName);
	}
	

}
