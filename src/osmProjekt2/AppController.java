package osmProjekt2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTable;

import java.awt.EventQueue;
public class AppController implements ActionListener, MouseListener {
	
	private AppView view = null;
	private SQLitetest dbModel = null;
	
	public AppController(AppView view, SQLitetest dbModel) {
	this.view = view;
	this.dbModel = dbModel;
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		Object source = evt.getSource();
		if (source == this.view.getBtnNewButton()) {
			try {
				addPatientFromForm();

			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if (source == this.view.getBtnNewButton_2()) {
			try {
				addExamFromForm();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if (source == this.view.getBtnNewButton_1()) {
			try {
				clearPatientFields();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if (source == this.view.getBtnNewButton_3()) {
			try {
				deletePatient();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}

		else if (source == this.view.getBtnNewButton_3()) {
			
		}
		
		else if (source == this.view.getBtnNewButton_6()) {
			try {
				plotPressure();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if (source == this.view.getBtnNewButton_7()) {
			try {
				plotPulse();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		else { // do podpiecia pod odpowiedni listener chyba Jtable? ----- zeby dzialalo musi byc wypelniony jtextfield z peselem 
			
			String pesel = this.view.getTextField_3().getText();
			this.view.getTextField_9().setText(dbModel.getAvgPressure(pesel));
			this.view.getTextField_10().setText(dbModel.getMaxPulse(pesel));
			this.view.getTextField_6().setText(dbModel.getMinPressure(pesel));
			this.view.getTextField_7().setText(dbModel.getMaxPressure(pesel));
			this.view.getTextField_8().setText(dbModel.getMinPulse(pesel));
			this.view.getTextField_11().setText(dbModel.getAvgPulse(pesel));
			
		}
	
	}
	
	@Override
	public void mouseClicked(MouseEvent evt) {
		JTable source = (JTable)evt.getSource();
		if (source == this.view.getTable()) {
			try {
				int row = source.rowAtPoint(evt.getPoint());
				selectPatientFromTable(row);

			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if (source == this.view.getTable_1()) {
			try {
				int row = source.rowAtPoint(evt.getPoint());
				selectExamFromTable(row);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
		
			
		
	public void addPatientFromForm() {
		String agetxt = this.view.getTextField_2().getText();
		int age = Integer.parseInt(agetxt);
		String name = this.view.getTextField().getText();
		String surname = this.view.getTextField_1().getText();
		String gender = this.view.getComboBox().getSelectedItem().toString();
		String pesel = this.view.getTextField_3().getText();
		dbModel.addUser(name,surname,age,gender,pesel);
		
	}
	
	public void addExamFromForm() throws SQLException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(this.view.getDateChooser().getDate());
		String pulsetxt = this.view.getTextField_4().getText();
		int pulse = Integer.parseInt(pulsetxt);
		String pressuretxt = this.view.getTextField_5().getText();
		int pressure = Integer.parseInt(pressuretxt);
		String patient_pesel = this.view.getTextField_3().getText();
		ResultSet rs = dbModel.getSelectedPatientsId(patient_pesel);
		int patient_id = rs.getInt("patient_id");
		dbModel.addExam(date, pulse, pressure, patient_id);
	}
	public void clearPatientFields() {
		this.view.getTextField().setText(null);
		this.view.getTextField_1().setText(null);
		this.view.getTextField_2().setText(null);
		this.view.getTextField_3().setText(null);
	}
	public void clearExamFields() {
		this.view.getTextField_4().setText(null);
		this.view.getTextField_5().setText(null);
		this.view.getDateChooser().setDate(null);
	}
	public void selectPatientFromTable(int row) {
		String name = (String)this.view.getTableModel().getValueAt(row, 1);
		String surname = (String)this.view.getTableModel().getValueAt(row, 2);
		String age = (String)this.view.getTableModel().getValueAt(row, 3);
		String gender = (String)this.view.getTableModel().getValueAt(row, 4);
		String pesel = (String)this.view.getTableModel().getValueAt(row, 5);
		
		this.view.getTextField().setText(name);
		this.view.getTextField_1().setText(surname);
		this.view.getTextField_2().setText(age);
		this.view.getTextField_3().setText(pesel);
		this.view.getComboBox().setSelectedItem(gender);
		
	}
	public void selectExamFromTable(int row) throws ParseException {
		String date = (String)this.view.getExamTableModel().getValueAt(row, 1);
		Date sdf = new SimpleDateFormat("yyyy-MM-dd").parse(date);
		String pressure = (String)this.view.getExamTableModel().getValueAt(row, 2);
		String pulse = (String)this.view.getExamTableModel().getValueAt(row, 3);
		
		this.view.getTextField_4().setText(pressure);
		this.view.getTextField_5().setText(pulse);
		this.view.getDateChooser().setDate(sdf);
	}
	public void deletePatient() throws SQLException { 
		String pesel = this.view.getTextField_3().getText();
		ResultSet rs = this.dbModel.getSelectedPatientsId(pesel);
		int id = (int) rs.getObject(1);
		this.dbModel.deleteExam(id);
		this.dbModel.deletePatient(pesel);
		rs.close();
	}
	public void deleteExam() {
		
	}
	public void plotPressure() throws SQLException {
		String pesel = this.view.getTextField_3().getText();
		//ResultSet rs = this.dbModel.getSelectedPatientsId(pesel);
		//int id = (int) rs.getObject(1);
		PressurePlot plot = new PressurePlot(this.dbModel, pesel);
	}
	public void plotPulse() {
		String pesel = this.view.getTextField_3().getText();
		PulsePlot plot = new PulsePlot(this.dbModel, pesel);
	}
	
	

}
