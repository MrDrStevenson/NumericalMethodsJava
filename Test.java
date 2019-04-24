import java.io.IOException;

import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.BitmapEncoder.BitmapFormat;

public class Test extends RK4{

	public static void main(String args[]) throws IOException { 
        Test obj = new Test(); 
        // Initial Values 
        double t0 = -1; 
        double y0 = 0; 
        double[] h = {Integer.MAX_VALUE - 5}; 
        double tf = 1; // end time value
        for (double steps:h) {
    
        	double[] xdata = new double[(int)steps];
        	double[] ydata = new double[(int)steps];
        	xdata[0] = t0;
        	ydata[0] = y0;
        	
        	
        	int i = obj.rungeKutta(t0, y0, steps, tf, xdata, ydata);
        	XYChart chart = QuickChart.getChart(1/steps + " Step Size Runge Kutta: y(1) = " + ydata[i - 1], "t", "y", "RK4 Approximation", xdata, ydata);
        	BitmapEncoder.saveBitmapWithDPI(chart, "C:\\Users\\mczyk\\Desktop\\Numerical Methods Project/" + 1/steps + " Step Size RK4", BitmapFormat.PNG, 300);
        }
    } 
	
}
