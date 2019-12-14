// This programme parses a simple two column x,y .csv file and draws a chart of the data

package com.martinoconnell.info;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;

import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.demo.charts.ExampleChart;
import org.knowm.xchart.style.Styler.LegendPosition;
import org.knowm.xchart.BitmapEncoder.BitmapFormat;
import org.knowm.xchart.XYSeries.XYSeriesRenderStyle;


public class NewLineChart implements ExampleChart<XYChart>{

	
	public static void main(String[] args) throws Exception {
		
		// Main code execution
		ReadCSV read = new ReadCSV();
		read.csvParser("./javaTest.csv");

		ExampleChart<XYChart> exampleChart = new NewLineChart();
		XYChart chart = exampleChart.getChart();
		new SwingWrapper<XYChart>(chart).displayChart();
	
	}

	@Override
	public XYChart getChart() {
		
		ReadCSV r = new ReadCSV();
		double[] x1Val = r.getListX1();
		double[] y1Val = r.getListY1();

		// Create Chart
		XYChart chart = new XYChartBuilder().width(1000).height(600).title("Second Line Test Chart")
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

		// Add as many lines to the chart as required so long as the 'x' and 'y' array sizes match
		chart.addSeries("Test Data", x1Val ,y1Val);

		
		// Save chart to current directory with file name "Sample_Chart_300_DPI.PNG"
		try {
			
			BitmapEncoder.saveBitmapWithDPI(chart, "./Second_Line_Test_Chart_300_DPI", BitmapFormat.PNG, 300);
			
		} catch (IOException e) {
			
			System.out.println("An I/O eception has occured");
			e.printStackTrace();
			
		}
		return chart;
	}


}
