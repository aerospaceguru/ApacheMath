package com.martinoconnell.info;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;

import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
import org.knowm.xchart.*;
import org.knowm.xchart.BitmapEncoder.BitmapFormat;
import org.knowm.xchart.XYSeries.XYSeriesRenderStyle;
import org.knowm.xchart.demo.charts.*; //This must be imported for Eclipse to recognise the interface ExampleChart
import org.knowm.xchart.style.Styler.LegendPosition;


public class LineChart implements ExampleChart<XYChart>{

	public static void main(String[] args) throws Exception {
		
		long start = System.currentTimeMillis(); // calculation start time
		
		// Main code execution
		ExampleChart<XYChart> exampleChart = new LineChart();
		XYChart chart = exampleChart.getChart();
		new SwingWrapper<XYChart>(chart).displayChart();
		
		long end  = System.currentTimeMillis(); // calculation end time
		System.out.println("\nCompute time is " + (end-start) + " ms"); // total calculation time
		
	}

	//This is the required implementation of the interface 
	@Override
	public XYChart getChart() {
		
		// Create Chart
		XYChart chart = new XYChartBuilder().width(1000).height(600).title("Line Test Chart")
				.xAxisTitle("X Values").yAxisTitle("Y Values").build();
		
		// Customise chart
		chart.getStyler().setLegendVisible(true);
		chart.getStyler().setLegendPosition(LegendPosition.InsideNW);
		chart.getStyler().setDefaultSeriesRenderStyle(XYSeriesRenderStyle.Line);
		chart.getStyler().setChartBackgroundColor(Color.decode("#FFFEF5"));
		chart.getStyler().setLegendFont(new Font("Courier", Font.BOLD, 24));
		chart.getStyler().setChartTitlePadding(30);
		chart.getStyler().setAxisTickPadding(10);
		chart.getStyler().setChartPadding(25);
		chart.getStyler().setChartTitleFont(new Font("Courier", Font.BOLD, 24));
		chart.getStyler().setAxisTitleFont(new Font("Courier", Font.BOLD, 12));
		chart.getStyler().setAxisTickLabelsFont(new Font("Courier", Font.PLAIN, 10));
		chart.getStyler().setLegendBackgroundColor(Color.decode("#E5E5E5"));		
		
		// Chart random data generator with additional arrays for adding a second line to the chart		
		PolynomialFunction p = new PolynomialFunction(Main.c);

		double[] xVal = new double[11];
		double[] x1Val = {-5, -4, -3, -2, -1, 0};
		double[] yVal = new double[11];
		double[] y1Val = {-6, 15, 18, 13, 5, 0};

		for(int i=0; i<11; i++) { xVal[i] = i-5;}

		for(int j=0; j<11; j++) {

			double x = xVal[j];
			yVal[j] = p.value(x);
			System.out.println("x value is: "+ xVal[j] + " y value is: " + yVal[j]);

		}
		
		// Add as many lines to the chart as required so long as the 'x' and 'y' array sizes match
		chart.addSeries("Test Data", xVal ,yVal);
		chart.addSeries("Extra Data", x1Val, y1Val);
		
		// Save chart to current directory with file name "Sample_Chart_300_DPI.PNG"
		try {
			
			BitmapEncoder.saveBitmapWithDPI(chart, "./Sample_Chart_300_DPI", BitmapFormat.PNG, 300);
			
		} catch (IOException e) {
			
			System.out.println("An I/O eception has occured");
			e.printStackTrace();
			
		}
		
		return chart;
	}

}
