package osmProjekt2;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;

public class PulsePlot extends JFrame {

	
	private SQLitetest db = null;
	private BarRenderer renderer = null;

	
	public PulsePlot(SQLitetest smth, String pesel) {
		
		this.db=smth;
		
		JFreeChart plot = ChartFactory.createLineChart("Pulse Plot", "Date", "Pulse", db.getPulseDataSet(pesel), PlotOrientation.VERTICAL, false, true, true);
		CategoryPlot plotk =null;
		renderer = new BarRenderer();
		ChartFrame frame = new ChartFrame("Pressure Plot", plot);
		frame.setVisible(true);
		frame.setSize(400, 650);
		
	}
	
	
}
