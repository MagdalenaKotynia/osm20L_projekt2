package osmProjekt2;

import javax.swing.JFrame;
import org.jfree.chart.*;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.*;

public class PressurePlot extends JFrame {
	
	
	private SQLitetest db = null;
	private BarRenderer renderer = null;

	
	public PressurePlot(SQLitetest smth, String pesel) {
		
		this.db=smth;
		
		JFreeChart plot = ChartFactory.createLineChart("Pressure Plot", "Date", "Pressure", db.getPressureDataSet(pesel), PlotOrientation.VERTICAL, false, true, true);
		CategoryPlot plotk =null;
		renderer = new BarRenderer();
		ChartFrame frame = new ChartFrame("Pressure Plot", plot);
		frame.setVisible(true);
		frame.setSize(400, 650);
		
	}
	
	
	
}
