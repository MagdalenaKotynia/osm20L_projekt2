package osmProjekt2;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.table.AbstractTableModel;

public class ExamTableModel extends AbstractTableModel {
	
	private String[] columnNames = {"patient_id", "Date", "Pressure", "Pulse"};
	protected SQLitetest data;

	public ExamTableModel(SQLitetest examdata) {
		this.data = examdata;
	}
	
	public int getRowCount() {
		
		int size;
		
		if(data==null) {
			size=0;
		}else {
			size=data.getExamRowCount();
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
			
			ResultSet rs = data.getExam();
			
			for(int i=0; i<=rowIndex; i++) {
				rs.next();
			}
			
			temp = rs.getString("patient_id");
			
		}else if(columnIndex==1) {
			
			ResultSet rs = data.getExam();
			
			for(int i=0; i<=rowIndex; i++) {
				rs.next();
			}
			
			temp = rs.getString("date");
			
		}else if(columnIndex==2) {
			
			ResultSet rs = data.getExam();
			
			for(int i=0; i<=rowIndex; i++) {
				rs.next();
			}
			
			temp = rs.getString("pulse");
			
		}else if(columnIndex==3) {
			
			ResultSet rs = data.getExam();
			
			for(int i=0; i<=rowIndex; i++) {
				rs.next();
			}
			
			temp = rs.getString("pressure");
			
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
