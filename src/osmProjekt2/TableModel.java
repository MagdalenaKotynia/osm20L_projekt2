package osmProjekt2;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.table.AbstractTableModel;

public class TableModel extends AbstractTableModel {

	private String[] columnNames = {"Imie", "Nazwisko", "Wiek", "Plec", "PESEL"};
	protected SQLitetest data;
	
	
	public TableModel(SQLitetest patientdata) {
		this.data = patientdata;
	}
	
	public int getRowCount() {
		
		int size;
		
		if(data==null) {
			size=0;
		}else {
			size=data.getPatientRowCount();
		}
		
		return size;
		
	}
	
	
	public int getColumnCount() {
		return columnNames.length;
	}
	
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		Object temp = null;
		
		
		try {
		if(columnIndex==0) {
			
			ResultSet rs = data.getPatient();
			
			for(int i=0; i<=rowIndex; i++) {
				rs.next();
			}
			
			temp = rs.getString("name");
			
		}else if(columnIndex==1) {
			
			ResultSet rs = data.getPatient();
			
			for(int i=0; i<=rowIndex; i++) {
				rs.next();
			}
			
			temp = rs.getString("surname");
			
		}else if(columnIndex==2) {
			
			ResultSet rs = data.getPatient();
			
			for(int i=0; i<=rowIndex; i++) {
				rs.next();
			}
			
			temp = rs.getString("age");
			
		}else if(columnIndex==3) {
			
			ResultSet rs = data.getPatient();
			
			for(int i=0; i<=rowIndex; i++) {
				rs.next();
			}
			
			temp = rs.getString("gender");
			
		}else if(columnIndex==4) {
			
			ResultSet rs = data.getPatient();
			
			for(int i=0; i<=rowIndex; i++) {
				rs.next();
			}
			
			temp = rs.getString("pesel");
			
		}
		return temp;
		
			}catch(SQLException e) {
				e.printStackTrace();
				return temp;
			}
			
	}
	
	public String getColumnName(int col) {
		return columnNames[col];
	}
	
	public Class getColumnClass(int col) {
		return String.class;
	}
	
	public boolean isCellEditable(int row, int col) {
		
		return false;
		
	}
	
	public void setValueAt(SQLitetest data, int row, int col) {
		
		// IT IS UNFINISHED BTW
		fireTableCellUpdated(row, col);
		
	}
	
	
	
}
