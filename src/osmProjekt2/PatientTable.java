package osmProjekt2;

import java.sql.*;
import java.util.*;

public class PatientTable extends DbTable {
	
	private static PatientTable instance;
	private static final String tableName = "PatientTable";
	//predefined first name to be used if DB is empty
	private static final String[] fName = {"paul", "benoit", "john", "neo", "cabbage", "honey", "mike"};
	
	
	private static final String createTable =
			"Create Cached Table " + tableName + " (" +
			"Obj OBJECT," + //the object implementing GetStId
			"Patient_id INTEGER IDENTITY PRIMARY KEY, " +
			"Name 		VARCHAR(50) NOT NULL, " +
			"Surname 	VARCHAR(50) NOT NULL, " +
			"Age 		INTEGER NOT NULL, " +
			"Gender 	VARCHAR(10) NOT NULL, " +
			"PESEL 		VARCHAR(11) NOT NULL, " +
			"UNIQUE (patient_id)," +
			"UNIQUE (PESEL), " +
			");";
	
	
	PatientTable(){
		instance = this;
		boolean tableExisted = true; // kontrola czy tablica istniala
		
		if(getCount() == -1) {
			
			tableExisted = false;
			statementExecute(createTable);
			statementExecute("ALTER TABLE " + tableName + " ALTER COLUMN Patient_id RESTART WITH 1;");  // id tablicy zaczyna sie od 1 zamiast 0 
			
		}
		
		// stworzenie prst
		
		try {
			
			
			prstInsert = con.prepareStatement("INSERT INTO " + tableName + " (Obj, Patient_id, Name, Surname, Age, Gender, PESEL) VALUES (?, ?, ?, ?, ?, ?, ?)");
            prstUpdate = con.prepareStatement("UPDATE " + tableName + " SET Name = ?, Surname = ?, Age = ?, Gender = ?, PESEL = ?, Obj = ? WHERE Patient_id = ?;");
            prstDelete = con.prepareStatement("DELETE FROM " + tableName + " WHERE Patient_id = ?;");
            prstGetById = con.prepareStatement("SELECT Obj FROM  " + tableName + " WHERE Patient_id = ?;");
            prstSelectAll = con.prepareStatement("SELECT Obj, Patient_id FROM " + tableName + " ORDER BY Name;");
			
		} catch (SQLException e) {

			System.out.println("Problem creating PreparedStatement for " + tableName + ".");
			throw new IllegalStateException("Problem creating PreparedStatement for " + tableName + ".");
				
		}
		
		//if the table didnt exist we fill it with predefined elements
		if(!tableExisted) {
				Patient test = new Patient("Magdalena", "Kotynia", 22, "Kobieta","45637289121");
				Patient test2 = new Patient("Szymon", "Kruszewski", 22, "Male", "12345567898");
				test.sqlInsert();
		
		ArrayList<GetSetId> al = sqlSelectAll();
		
		System.out.println("List if the " + al.size() + "elements in table"+ tableName+"order by Name.");
		for(int i = 0; i < al.size(); i++)
			System.out.println((Patient)al.get(i));
	}
	else {//Table already exist 
		ArrayList<GetSetId> al = sqlSelectAll();
		System.out.println("List if the" + al.size() + "elements in table" + tableName + "order by Name.");
		for(int i = 0; i < al.size(); i++)
			System.out.println((Patient)al.get(i));
	
	}	
	}
	
	//to return the single instance of this class
	static PatientTable getInstance() {
		return instance;
	}
	//to insert a new Patient
	boolean sqlInsert(Patient patient) {
		boolean status = false;
		
		try {
			prstInsert.setObject(1, patient, Types.JAVA_OBJECT);
			prstInsert.setNull(2,  Types.INTEGER);
			prstInsert.setString(3, patient.getName());
			prstInsert.setString(4,  patient.getSurname());
			prstInsert.setInt(5, patient.getAge());
			prstInsert.setString(6, patient.getGender());
			prstInsert.setString(7, patient.getPesel());
			
			//perform the insert statement
			int nbChanged = statementExecute(prstInsert);
			status = nbChanged > 0;
			System.out.println("Statement execute" + prstInsert + "rows affected" + nbChanged + "status" + status);
		}
			
			 /*prstInsert.setInt(1, id);			// .setNull(x, Types.INTEGER); ??????
			 prstInsert.setString(2, name);
			 prstInsert.setString(3, surname);
			 prstInsert.setInt(4, age);
			 prstInsert.setString(5, gender);
			 prstInsert.setString(6, pesel);*/
			
		catch(SQLException e) {
			System.out.println("PatientTable sqlInsert() exception: " + e);
		}
		return status;
		
	}
	
	boolean sqlUpdate(Patient patient) {
		 boolean status = false;
		
		try {
			prstUpdate.setObject(1, patient, Types.JAVA_OBJECT);
			prstUpdate.setNull(2,  Types.INTEGER);
			prstUpdate.setString(3, patient.getName());
			prstUpdate.setString(4,  patient.getSurname());
			prstUpdate.setInt(5, patient.getAge());
			prstUpdate.setString(6, patient.getGender());
			prstUpdate.setString(7, patient.getPesel());
			 /*prstUpdate.setInt(1, id);
			 prstUpdate.setString(2, name);
			 prstUpdate.setString(3, surname);
			 prstUpdate.setInt(4, age);
			 prstUpdate.setString(5, gender);
			 prstUpdate.setString(6, pesel);*/
			
			 int nbChanged = statementExecute(prstUpdate);
			 status = nbChanged > 0;
			 System.out.println("Statement execute " + prstUpdate + " rows affected " + nbChanged + " status " + status);
		}catch(SQLException e) {
			System.out.println("FirstNameTable sqlUpdate() exception: " + e);
		}
		return status;
		
	}
	
	
	int getCount() {
		return getCount(tableName);
	}
	
	Patient getById(int id) {
		return(Patient) sqlGetById(id);
	}
	

}
