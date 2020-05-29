package osmProjekt2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.awt.EventQueue;
public class AppController implements ActionListener {
	
	private GUI view = null;
	private SQLitetest dbModel = null;
	
	public AppController(GUI view, SQLitetest dbModel) {
	this.view = view;
	this.dbModel = dbModel;
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		Object source = evt.getSource();
		if (source == this.view.button) {
			try {
				String agetxt = this.view.textField_2.getText();
				int age = Integer.parseInt(agetxt);
				String name = this.view.textField.getText();
				String surname = this.view.textField_1.getText();
				String gender = this.view.comboBox.getSelectedItem().toString();
				String pesel = this.view.textField_3.getText();
				dbModel.addUser(name,surname,age,gender,pesel);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if (source == this.view.button_2) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String date = sdf.format(this.view.dateChooser.getDate());
				String pulsetxt = this.view.textField_4.getText();
				int pulse = Integer.parseInt(pulsetxt);
				String pressuretxt = this.view.textField_5.getText();
				int pressure = Integer.parseInt(pressuretxt);
				String patient_pesel = this.view.textField_3.getText();
				ResultSet rs = dbModel.getSelectedPatientsId(patient_pesel);
				int patient_id = rs.getInt("patient_id");
				dbModel.addExam(date, pulse, pressure, patient_id);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
			
		}

	

}
