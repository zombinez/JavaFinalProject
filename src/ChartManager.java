import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleInsets;
import java.awt.*;
import javax.swing.JPanel;

public class ChartManager extends ApplicationFrame {
    public ChartManager(String[] countriesLabels, float[] economyValues, 
        String xAxisLabel, String yAxisLabel) {
        super("Countries Economy Histogram");
        setContentPane(DisplayChart(CreateChart(countriesLabels, economyValues, xAxisLabel, yAxisLabel)));
    }

    public JFreeChart CreateChart(String[] countriesLabels, float[] economyValues, String xAxisLabel, String yAxisLabel) throws RuntimeException{
        if(countriesLabels.length != economyValues.length) 
            throw new RuntimeException("Labels array and values array have different lengths");
        var dataset = new DefaultCategoryDataset();
        for(var i = 0; i < countriesLabels.length; i++) 
            dataset.addValue((float) Math.round(economyValues[i] * 100000) / 100000, countriesLabels[i], xAxisLabel);
        JFreeChart chart = ChartFactory.createBarChart("Countries Economy Histogram by the 2015", null, yAxisLabel, dataset);
        chart.setBackgroundPaint(Color.white);
        return chart;
    }

    public JPanel DisplayChart(JFreeChart chart) {
        chart.setPadding(new RectangleInsets(4, 8, 2, 2));
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setFillZoomRectangle(true);
        chartPanel.setMouseWheelEnabled(true);
        chartPanel.setPreferredSize(new Dimension(1280, 720));
        return chartPanel;
    }
}
