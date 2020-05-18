package osmProjekt2;



import java.awt.EventQueue;
import java.sql.*;

public class Main {

	public static void main(String[] args) {
		
		DbConnector connector = new DbConnector();
		connector.createTable();
		connector.insertIntoTable("Jan", "Kowalski", 29, "Mężczyzna", "1234567890");
		connector.insertIntoTable("Anna", "Kowalska", 22, "Kobieta", "1099876543");
		connector.insertIntoTable("Sebastian", "Gromelski", 11, "Mężczyzna", "1212364564");
		connector.insertIntoTable("Jarosław", "Poznalski", 65, "Mężczyzna", "4367684565");
		connector.insertIntoTable("Michalina", "Sul", 22, "Kobieta", "12398754681");
		connector.printAll();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.getFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		

	}

}
