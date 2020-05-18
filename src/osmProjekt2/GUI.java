package osmProjekt2;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.PolarChartPanel;
import net.miginfocom.swing.MigLayout;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.EtchedBorder;

import com.sun.jdi.connect.spi.Connection;
import com.toedter.calendar.JDateChooser;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import javax.swing.JScrollBar;

public class GUI {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JButton button_2;
	private JButton button;
	private JTable table;
	private JTable table_1;
	private JComboBox comboBox;
	private DbConnector myDataBase;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
		
	
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setFrame(new JFrame());
		getFrame().setBounds(100, 100, 840, 558);
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getFrame().getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		panel.setBounds(10, 10, 193, 167);
		getFrame().getContentPane().add(panel);
		panel.setLayout(new MigLayout("", "[61px][74px,grow]", "[19px][13px][][][][][]"));
		
		JLabel lblNewLabel = new JLabel("Name:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		panel.add(lblNewLabel, "cell 0 0,growx,aligny center");
		
		textField = new JTextField();
		panel.add(textField, "cell 1 0,growx,aligny top");
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Surname:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(lblNewLabel_1, "cell 0 1,alignx left,aligny top");
		
		textField_1 = new JTextField();
		panel.add(textField_1, "cell 1 1,growx");
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Age:\r\n");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(lblNewLabel_2, "cell 0 2,alignx left");
		
		textField_2 = new JTextField();
		panel.add(textField_2, "cell 1 2,growx");
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("PESEL:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(lblNewLabel_3, "cell 0 3,alignx left");
		
		textField_3 = new JTextField();
		panel.add(textField_3, "cell 1 3,growx");
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Gender:\r\n");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(lblNewLabel_4, "cell 0 4,alignx left");
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(gender.values()));
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(comboBox, "cell 1 4,growx");
		
		button = new JButton("Save");
		button.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(button, "cell 0 5");
		
		JButton button_1 = new JButton("Cancel");
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(button_1, "cell 1 5");
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		panel_1.setBounds(10, 187, 193, 116);
		getFrame().getContentPane().add(panel_1);
		panel_1.setLayout(new MigLayout("", "[63px][104px,grow]", "[19px][][][][]"));
		
		JLabel lblNewLabel_5 = new JLabel("Date:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_1.add(lblNewLabel_5, "cell 0 0,growx,aligny top");
		
		JDateChooser dateChooser = new JDateChooser();
		panel_1.add(dateChooser, "cell 1 0,growx,aligny top");
		
		JLabel lblNewLabel_6 = new JLabel("Pressure:");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_1.add(lblNewLabel_6, "cell 0 1,alignx left");
		
		textField_4 = new JTextField();
		panel_1.add(textField_4, "cell 1 1,growx");
		textField_4.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Pulse:");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_1.add(lblNewLabel_7, "cell 0 2,alignx left");
		
		textField_5 = new JTextField();
		panel_1.add(textField_5, "cell 1 2,growx");
		textField_5.setColumns(10);
		
		button_2 = new JButton("Save");
		button_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_1.add(button_2, "cell 0 3");
		
		JButton button_3 = new JButton("Cancel");
		button_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_1.add(button_3, "cell 1 3");
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		panel_2.setBounds(10, 313, 193, 162);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(new MigLayout("", "[55px][96px,grow]", "[24px][][][][][]"));
		
		JLabel lblNewLabel_8 = new JLabel("Min Pressure:");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_2.add(lblNewLabel_8, "cell 0 0,alignx left,growy");
		
		textField_6 = new JTextField();
		textField_6.setEditable(false);
		panel_2.add(textField_6, "cell 1 0,alignx left,aligny center");
		textField_6.setColumns(10);
		
		JLabel lblNewLabel_9 = new JLabel("Max Pressure:");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_2.add(lblNewLabel_9, "cell 0 1,alignx left");
		
		textField_7 = new JTextField();
		textField_7.setEditable(false);
		panel_2.add(textField_7, "cell 1 1,growx");
		textField_7.setColumns(10);
		
		JLabel lblNewLabel_10 = new JLabel("Avg. Pressure:");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_2.add(lblNewLabel_10, "cell 0 2,alignx left");
		
		textField_8 = new JTextField();
		textField_8.setEditable(false);
		panel_2.add(textField_8, "cell 1 2,growx");
		textField_8.setColumns(10);
		
		JLabel lblNewLabel_11 = new JLabel("Min Pulse:");
		lblNewLabel_11.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_2.add(lblNewLabel_11, "cell 0 3,alignx left");
		
		textField_9 = new JTextField();
		textField_9.setEditable(false);
		panel_2.add(textField_9, "cell 1 3,growx");
		textField_9.setColumns(10);
		
		JLabel lblNewLabel_12 = new JLabel("Max Pulse:");
		lblNewLabel_12.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_2.add(lblNewLabel_12, "cell 0 4,alignx left");
		
		textField_10 = new JTextField();
		textField_10.setEditable(false);
		panel_2.add(textField_10, "cell 1 4,growx");
		textField_10.setColumns(10);
		
		JLabel lblNewLabel_13 = new JLabel("Avg. Pulse:");
		lblNewLabel_13.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_2.add(lblNewLabel_13, "cell 0 5,alignx left");
		
		textField_11 = new JTextField();
		textField_11.setEditable(false);
		panel_2.add(textField_11, "cell 1 5,growx");
		textField_11.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(213, 10, 603, 167);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
			},
			new String[] {
				"patient_id", "Name", "Surname", "Age", "Gender", "PESEL"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, Integer.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPane.setViewportView(table);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(213, 187, 603, 116);
		frame.getContentPane().add(scrollPane_1);
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"patient_id", "exam_id", "Date", "Pressure ", "Pulse"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, Integer.class, String.class, Float.class, Float.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPane_1.setViewportView(table_1);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		panel_3.setBounds(213, 316, 603, 195);
		frame.getContentPane().add(panel_3);
		
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(getFrame(), popupMenu);
		
		myDataBase = new DbConnector();
		myDataBase.createTable();
		this.button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt)
			{
				try {
					/*String query = "INSERT INTO myDataBase (patient_id, Name, Surname, Age, Gender, PESEL) VALUES (default, ?, ?, ?, ?, ?)";
					PreparedStatement pst=myDataBase.connection.prepareStatement(query);
					//pst.setInt(1, 1);
					pst.setString(2, textField.getText());
					pst.setString(3, textField_1.getText());
					pst.setInt(4, 22);
					pst.setString(5, comboBox.getSelectedItem().toString());
					pst.setString(6, textField_3.getText());*/
					myDataBase.insertIntoTable(textField.getText(), textField_1.getText(), 22, comboBox.getSelectedItem().toString(), textField_3.getText());
					myDataBase.printAll();
					//pst.execute();
					JOptionPane.showMessageDialog(null, "Data saved");
					
					//pst.close();
				}
				catch (Exception e) {
					e.printStackTrace();
					
				}
			}
			});
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}


	
	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
	
}
