// Java program to find approximation of an ordinary 
// differential equation using euler method 
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.BitmapEncoder.BitmapFormat;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.XYChart;

class Euler { 
	// The differential equation goes here  
	double func(double t, double y) { 
		return (Math.pow(t, 2) + Math.pow(y,3)/6); 
	}

	// Function for Euler formula 
	int euler(double t0, double y, double h, double tf, double[] xdata, double[] ydata) {	
		double steps = (tf - t0)/h; //calculate # of steps to take
		int i = 1;
		while (i <= steps) {
			y = y + h * func(t0, y); 
			t0 = t0 + h;
			xdata[i] = t0;
			ydata[i] = y;
			// System.out.println("y(" + t0 + ") = " + y);
			i++;
		} 
		return i;
	} 

	// Driver program 
	public static void main(String args[]) throws IOException {

		Euler obj = new Euler(); 
		// Initial Values 
		double t0 = -1; 
		double y0 = 0; 
		double[] h = {1.0/5.0, 1.0/10.0, 1.0/20.0, 1.0/40.0, 1.0/80.0, 1.0/160.0, 1.0/320.0}; 
		double tf = 1; // end time value
		double[] numSteps = new double[h.length];
		for (int i = 0; i < h.length; i++) {
			numSteps[i] = (tf - t0)/h[i];
		}
		double[] finals = new double[7];
		int j = 0;
		for (double steps:h) {

			double[] xdata = new double[(int)numSteps[j] + 1];
			double[] ydata = new double[(int)numSteps[j] + 1];
			xdata[0] = t0;
			ydata[0] = y0;

			int i = obj.euler(t0, y0, steps, tf, xdata, ydata);
			finals[j] = ydata[i - 1];
			XYChart chart = QuickChart.getChart(h[j] + " Step Size Euler: y(1) = " + ydata[i - 1], "t", "y", "Euler Approximation", xdata, ydata);
			BitmapEncoder.saveBitmapWithDPI(chart, "C:\\Users\\mczyk\\Desktop\\Numerical Methods Project\\" + h[j] + " Step Size Euler", BitmapFormat.JPG, 300);
			j++;
		}
		File file = new File("C:\\Users\\mczyk\\Desktop\\Numerical Methods Project\\EulerData.txt");
		FileWriter output = new FileWriter(file);
		for (int i = 0; i < finals.length; i++)output.write("Estimate of y(" + tf + ") for M" + (i + 1) + " " + finals[i] + '\r');
		for (int k = 2; k < 7 ; k++) {
			double errorRatio = ((finals[k - 1] - finals[k - 2])/(finals[k] - finals[k - 1]));
			output.write("Error ratio for M" + (k + 1) +" " + errorRatio + '\r');
		}
		output.close();
		System.out.println("Terminated.");
	} 
} 