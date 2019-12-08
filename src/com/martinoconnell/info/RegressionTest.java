package com.martinoconnell.info;

import java.io.IOException;

import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
import org.apache.commons.math3.stat.regression.SimpleRegression;
import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.BitmapEncoder.BitmapFormat;

public class RegressionTest {
	
	static XYChart chart;

	public static void main(String[] args) {

		// Carry out regression analysis and find equation of closest straight matching line for points {x,y}
		double[][] points = { {1,3}, {2,5}, {3,7}, {4,14}, {5,11} };
		RegressionTest test = new RegressionTest();
		test.calculateRegression(points);

		PolynomialFunction p = new PolynomialFunction(test.calculateRegression(points));
		
		SimpleRegression regression = new SimpleRegression();
		regression.addData(points);
		
		System.out.println("The value of 'c' is: " + test.calculateRegression(points)[0]);
		System.out.println("The value of 'm' is: " + test.calculateRegression(points)[1]);
		System.out.println("The equation of the closest matching line is: " + p);
		System.out.println("The slope error value is: " + regression.getRSquare());

		// Generate data for the chart of the closest straight line match	
		double[] xVal = new double[points.length];
		double[] yVal = new double[points.length];
		double[] yVal2 = new double[points.length];

		for(int i=0; i<points.length; i++) {xVal[i] = points[i][0];}
		for(int i=0; i<points.length; i++) {yVal[i] = p.value(xVal[i]);}
		for(int i=0; i<points.length; i++) {yVal2[i] = points[i][1];}

		//Generate quick chart
		chart = QuickChart.getChart("Sample Plotting Chart", "X Values", "Y Values", "Line of Best Fit", xVal, yVal); 
		chart.getStyler().setLegendVisible(true);
		chart.addSeries("Original line", xVal, yVal2);
		new SwingWrapper(chart).displayChart(); 

		// Save chart to current directory with file name "Regression_Chart_300_DPI.PNG"
		saveChart();
		
	}

	public double[] calculateRegression(double[][] data) {

		SimpleRegression regression = new SimpleRegression();
		regression.addData(data);

		double c = regression.getIntercept();
		double m = regression.getSlope(); 
		double[] constants = {c, m};

		return constants;
	}
	
	public static void saveChart() {
		
		try {

			BitmapEncoder.saveBitmapWithDPI(RegressionTest.chart, "./Regression_Chart_300_DPI", BitmapFormat.PNG, 300);

		} catch (IOException e) {

			System.out.println("An I/O eception has occured");
			e.printStackTrace();

		}
	}

}
