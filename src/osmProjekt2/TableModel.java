package osmProjekt2;

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
		
		return size-1;
		
	}
	
	
	public int getColumnCount() {
		return columnNames.length;
	}
	
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		Object temp = null;
		rowIndex++;
		
		try {
		if(columnIndex==0) {
			
			temp = data.getPatient(rowIndex).getString("name");
			
		}else if(columnIndex==1) {
			
			temp = data.getPatient(rowIndex).getString("surname");
			
		}else if(columnIndex==2) {
			
			temp = data.getPatient(rowIndex).getString("age");
			
		}else if(columnIndex==3) {
			
			temp = data.getPatient(rowIndex).getString("gender");
			
		}else if(columnIndex==4) {
			
			temp = data.getPatient(rowIndex).getString("pesel");
			
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
		
		fireTableCellUpdated(row, col);
		
	}
	
	
	
}
