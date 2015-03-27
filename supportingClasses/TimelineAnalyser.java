import java.awt.Dimension;
import java.util.Arrays;

import javax.swing.JOptionPane;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * This Singleton class uses JFreeChart to show timeline analysis
 * 
 * @author Kerem Baybars
 * @version 1.0
 */
public class TimelineAnalyser {
	private static TimelineAnalyser singletonTimelineAnalyser;
	
	/**
	 * Private constructor
	 */
	private TimelineAnalyser() {}
	
	/**
	 * Checks whether the singleton object is null and makes an instance.
	 * 
	 * @return - singleton Object
	 */
	public static synchronized TimelineAnalyser getSingletonTimelineAnalyser() {
		if(singletonTimelineAnalyser == null) {
			singletonTimelineAnalyser = new TimelineAnalyser();
		}
		return singletonTimelineAnalyser;
	}
	
	/**
	 * Prevents other classes from cloning this class.
	 */
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}
	
	/**
	 * Carries out Timeline analysis using a 3D bar chart.
	 * 
	 * @param contentArray
	 */
	public static void doTimelineAnalysis(Object[][] contentArray) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		int num = 1;
		for (int i = 0; i < contentArray.length; i++) {
		    dataset.addValue(num++,Arrays.deepToString(contentArray[i]),Arrays.deepToString(contentArray[i]));
		}

		JFreeChart lineChart3D = ChartFactory.createBarChart3D("Analysing 'Last Modified' of Files", 
				"Years", "Evidences", dataset, PlotOrientation.VERTICAL, true, true, false);
		
		ChartPanel chart = new ChartPanel(lineChart3D);
		chart.setPreferredSize(new Dimension(560,367));
		JOptionPane.showMessageDialog(null, chart, "Timeline Analysis", JOptionPane.INFORMATION_MESSAGE);
	}
}
