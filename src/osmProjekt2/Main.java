package osmProjekt2;



import java.awt.EventQueue;
import java.sql.*;

public class Main {

	public static void main(String[] args) {

		//test.addUser("Eryk", "Kowalski", 22, "Male", "1234567897654");
		
		//test.deleteExam(2);
		//test.updatePatient(1, "Jan", "Kostuszewwski", 12, "FEMALE", "123456457");
		//test.selectPatientsExamCard(1);
		
		/*ResultSet rs;
		rs = test.displayUsers();
		ResultSet rs2;
		rs2 = test.selectPatientsExamCard(1);
		
		try {
			while(rs.next()) {
				System.out.println(rs.getString("name") + " " + rs.getString("surname"));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			while(rs2.next()) {
				System.out.println(rs2.getInt("id") + " " + rs2.getInt("patient_id"));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					SQLitetest test = new SQLitetest();
					AppController ctrl = new AppController(window, test);
					window.setController(ctrl);
					window.getFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		

	}

}
