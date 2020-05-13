package osmProjekt2;

import org.jfree.chart.*;
import java.sql.*;

public class Main {

	public static void main(String[] args) {
		
		DbConnector connector = new DbConnector();
		connector.createTable();
		//connector.insertIntoTable(2, "Jan", "Kowalski", 29, 'F', "1234567890");
		//connector.printAll();

	}

}
