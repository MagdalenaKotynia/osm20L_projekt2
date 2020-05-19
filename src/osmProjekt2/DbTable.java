package osmProjekt2;

import java.sql.*;
import java.util.ArrayList;

public class DbTable {

	private static final String dbPathStandAlone = "jdbc:hsqldb:file:";
	static Connection con;
	static DbTable instance;
	PreparedStatement prstInsert, prstUpdate, prstDelete, prstGetById, prstSelectAll;
	private static PreparedStatement prstGetLastId;
	
	DbTable(String dbName){
		System.out.println("Constructor of DbTable in StandAlone mode");
		// zaladuj sterownik hsqldb
		loadDriver();
		
		// proba polaczenia w trybie lokalnym
		try {
			
			con = DriverManager.getConnection(dbPathStandAlone + dbName, "sa", "");
			System.out.println("Correctly connected in StandAlone mode.");
			
		} catch (SQLException e) {
			
			int code = e.getErrorCode();
			System.out.println("getConnection failed: " + code + " " + e);
			throw new IllegalStateException("Cannot connect to DB in StandAlone mode.");
		}
		
		createTables(); // stworzenie tablic
		
		
	}
	

	DbTable(){
		
	}
	

	private void loadDriver() {
		
		instance=this;
		
		try {
			
			Class.forName("org.hsqldb.jdbcDriver");
			System.out.println("HSQLdb driver correctly loaded.");
			
		}catch(Exception e) {
			
			System.out.println("Problem loading JDBC driver: " + e);
			throw new IllegalStateException("HSQLDB driver couldn't be loadded.");
			
		}
		
		
		
	}
	
	void createTables() {
		
		try {
			
			prstGetLastId = con.prepareStatement("call identity()");
			
		} catch (SQLException e) {
			
			System.out.println("Problem creating prstGetId PreparedStatement.");
			throw new IllegalStateException("Problem creating prstGetId PreparedStatement.");
			
		}
		
		new PatientTable();
	//	new ExamTable();
		
		
	}
	
	// wspolna metoda do uzyskiwania zbioru wynikow z sql stringa
	
	protected ResultSet queryExecute(String sqlString) {
		
		try {
			
			PreparedStatement stmt = con.prepareStatement(sqlString);
			return queryExecute(stmt);
			
		} catch (SQLException e) {

			int code = e.getErrorCode();
			System.out.println("queryExecute building prepStatement " + sqlString + " returns " + code + " "+ e);
			return null;

		}
		
		
	}
	
	// metoda do wykonywania zapytan uzyajaca prst
	protected ResultSet queryExecute(PreparedStatement stmt) {
		
		System.out.println("DbTable.queryExecute(" + stmt + ")");
		
		try {
			
			ResultSet result = stmt.executeQuery();
			return result; 
			
		} catch (SQLException e) {

			System.out.println("queryExecute: " + stmt + " returns " + e);
			return null; 
			
		}
		
		
	}
	
	
	// metoda wykonujaca statement.execute ze Stringa. Zwraca liczbe zmodyfikowanych wierszy
	
	int statementExecute(String sql) {
		
		int nb = -1;
		
		try {
			
			PreparedStatement stmt = con.prepareStatement(sql);
			nb = statementExecute(stmt);
			
		} catch (SQLException e) {
			
			System.out.println("StatementExecute building prepStatement for " + sql + " returns: " + e);
			
		}  
		
		System.out.println("statementExecute(" + sql + ") returns " + nb);
		return nb;
		
		
	}

	// metoda wykonujaca statement.execute ze prst. Zwraca liczbe zmodyfikowanych wierszy
	
	int statementExecute(PreparedStatement stmt) {
		
		int nb = -1;
		
		try {
			
			nb = stmt.executeUpdate();
			
		} catch (SQLException e) {
			
			int code = e.getErrorCode();
			System.out.println("statementExecute: " + stmt);
			System.out.println("status: " + code + ">> "+ e);
			
		}
		return nb;
		
		
	}
	
	// zwraca liczbe wierszy tablicy której nazwa podana jest jako parametr, jesli tablica nie istnieje zwraca -1
	
	int getCount(String tableName) {
		
		int nb = -1;
		String sql = "SELECT COUNT(*) FROM " + tableName + ";";
		
		// wywoloanie powyzej zdefiniowanej metody
		ResultSet rs = queryExecute(sql);
		
		// if ResultSet jest null zwraca -1
		
		if(rs == null) {
			System.out.println("GetCount() on " + tableName + " returns " + nb);
			return nb;
		}
		
		// pobierz liczbe wierszy
		
		try {
			
			rs.next();
			nb = rs.getInt(1);
			rs.close();
			
			
		}catch(SQLException e) {
			
			throw new IllegalStateException("DbTable.getCount() rs.getNext() or rs.getInt Exception: " + e);
			
		}
		
		System.out.println("GetCount() on " + tableName + " returns " + nb);
		return nb;
		
	}
	
	// zwraca id ostatniego elementu dodanego do DB
	
	int getIdentity() {
		
		ResultSet rs = queryExecute(prstGetLastId);
		
		try {
			rs.next();
			int id = rs.getInt(1);
			rs.close();
			return id;
			
		}catch(SQLException e) {
			
			throw new IllegalStateException("Something wrong with getIdentity().");
		}
		
		
	}
	
	static DbTable getInstance() {
		return instance;
	}
	

	
	
}
