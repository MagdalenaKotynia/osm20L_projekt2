package osmProjekt2;



import java.awt.EventQueue;
import java.sql.*;

public class Main {

	public static void main(String[] args) {
		
		DbConnector connector = new DbConnector();
		connector.createTable();
		connector.insertIntoTable(1, "Jan", "Kowalski", 29, "Mężczyzna", "1234567890");
		connector.insertIntoTable(2, "Anna", "Kowalska", 22, "Kobieta", "1099876543");
		connector.insertIntoTable(3, "Sebastian", "Gromelski", 11, "Mężczyzna", "1212364564");
		connector.insertIntoTable(4, "Jarosław", "Poznalski", 65, "Mężczyzna", "4367684565");
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
