package osmProjekt2;

import javax.swing.JFrame;
import org.jfree.chart.*;
import org.jfree.data.category.*;

public class PressurePlot extends JFrame {

	
	public PressurePlot() {
		
		
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.addValue(15, "Pressure", "20-02-1998");
		JFreeChart plot = ChartFactory.createLineChart("Pressure plot", "Date", "Pressure value", dataset);
		
	}
	
	
	
}
