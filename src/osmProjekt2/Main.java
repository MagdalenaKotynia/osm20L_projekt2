package osmProjekt2;



import java.awt.EventQueue;
import java.sql.*;

public class Main {

	public static void main(String[] args) {

		SQLitetest test = new SQLitetest();
	//	ResultSet rs;
	//	int i = test.getPatientRowCount();
		String smth = test.getAvgPulse("123456457");
		System.out.println(smth);
		
		PressurePlot plot_1 = new PressurePlot(test, "123456457");
		PulsePlot plot_2 = new PulsePlot(test, "123456457");

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
