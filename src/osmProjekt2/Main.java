package osmProjekt2;



import java.awt.EventQueue;
import java.sql.*;

public class Main {

	public static void main(String[] args) {
		


		SQLitetest test = new SQLitetest();

		//test.addUser("Eryk", "Kowalski", 22, "Male", "1234567897654");
		//test.deleteExam(2);
		//test.updatePatient(1, "Jan", "Kostuszewwski", 12, "FEMALE", "123456457");

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
