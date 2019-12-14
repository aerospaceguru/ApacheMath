// This programme imports CSV data from several files from a single folder and draws a chart. 
// The name of each series corresponds to the file name.

package com.martinoconnell.info;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.util.Scanner;

import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.CSVImporter;
import org.knowm.xchart.CSVImporter.DataOrientation;
import org.knowm.xchart.XYSeries.XYSeriesRenderStyle;
import org.knowm.xchart.demo.charts.ExampleChart;
import org.knowm.xchart.style.Styler.LegendPosition;
import org.knowm.xchart.style.markers.Marker;
import org.knowm.xchart.style.markers.SeriesMarkers;

import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.BitmapEncoder.BitmapFormat;

public class ImportCSVData implements ExampleChart<XYChart> {
	
	static String location;
	static String title;
	static String xAxisTitle;
	static String yAxisTitle;

	public static void main(String[] args) throws Exception {

		// Main code execution
		ExampleChart<XYChart> exampleChart = new ImportCSVData();
		XYChart chart = exampleChart.getChart();
		new SwingWrapper<XYChart>(chart).displayChart();
	}
	
	public ImportCSVData() {
		
		//Enter location of source data and set the chart parameters
		System.out.print("Location of CSV source data folder: ");
		Scanner sc = new Scanner(System.in);
		location = sc.nextLine();
		
		System.out.print("Enter Chart Title: ");
		Scanner sc1 = new Scanner(System.in);
		title = sc1.nextLine();
		
		System.out.print("Enter X Axis Title: ");
		Scanner sc2 = new Scanner(System.in);
		xAxisTitle = sc2.nextLine();
		
		System.out.print("Enter Y Axis Title: ");
		Scanner sc3 = new Scanner(System.in);
		yAxisTitle = sc3.nextLine();
		
	}

	@Override
	public XYChart getChart() {

		// Import CSV data from a dedicated folder containing individual CSV files of x,y columns
		XYChart chart = CSVImporter.getChartFromCSVDir(location, DataOrientation.Columns, 900, 600);
		
		// Set chart marker types and colours
		Marker[] seriesMarkers = {SeriesMarkers.NONE};
		Color[] seriesColors = {Color.decode("#0033E3"), Color.decode("#5F7CE2"), Color.decode("#C6CDE7")};

		// Customise chart
		chart.setTitle(title);
		chart.setXAxisTitle(xAxisTitle);
		chart.setYAxisTitle(yAxisTitle);
		chart.getStyler().setSeriesMarkers(seriesMarkers);
		chart.getStyler().setSeriesColors(seriesColors);
		chart.getStyler().setLegendVisible(true);
		chart.getStyler().setLegendPosition(LegendPosition.InsideNW);
		chart.getStyler().setDefaultSeriesRenderStyle(XYSeriesRenderStyle.Line);
		chart.getStyler().setChartBackgroundColor(Color.decode("#FFFFFF"));
		chart.getStyler().setLegendFont(new Font("Calibri", Font.BOLD, 12));
		chart.getStyler().setChartTitlePadding(30);
		chart.getStyler().setAxisTickPadding(10);
		chart.getStyler().setChartPadding(30);
		chart.getStyler().setChartTitleFont(new Font("Calibri", Font.BOLD, 30));
		chart.getStyler().setAxisTitleFont(new Font("Calibri", Font.BOLD, 16));
		chart.getStyler().setAxisTickLabelsFont(new Font("Calibri", Font.PLAIN, 12));
		chart.getStyler().setLegendBackgroundColor(Color.decode("#FFFFFF"));

		// Save chart to current directory with file name "Chart Title_300_DPI.PNG"
		try {

			BitmapEncoder.saveBitmapWithDPI(chart, location + "/" + title + "_300_DPI", BitmapFormat.PNG, 300);

		} catch (IOException e) {

			System.out.println("An I/O eception has occured");
			e.printStackTrace();

		}

		return chart;
	}
}