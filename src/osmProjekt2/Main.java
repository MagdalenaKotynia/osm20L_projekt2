package osmProjekt2;



import java.awt.EventQueue;
import java.sql.*;

public class Main {

	public static void main(String[] args) {
		
		//new DbTable("DICdb");
		// DbTable.getInstance().statementExecute("SHUTDOWN COMPACT;");

		SQLitetest test = new SQLitetest();
		test.addUser("Robert", "Żera");
		ResultSet rs;
		rs = test.displayUsers();
		
		try {
			while(rs.next()) {
				System.out.println(rs.getString("name") + " " + rs.getString("surname"));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
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
