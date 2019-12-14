// This CSV Reader class parses a simple two column .csv file

package com.martinoconnell.info;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

import org.apache.commons.csv.*;
import org.apache.commons.lang3.ArrayUtils;

public class ReadCSV {
	
	static ArrayList<Double> listX1 = new ArrayList<Double>();
	static ArrayList<Double> listY1 = new ArrayList<Double>();
	static int listX1size;
	static int listY1size;

	public void csvParser(String filename) throws IOException {

		Reader in = new FileReader(filename);
		Iterable<CSVRecord> records = CSVFormat.RFC4180.parse(in);
		for (CSVRecord record : records) {
			String x = record.get(0);
			String y = record.get(1);	
			double x1 = Double.parseDouble(x);
			double y1 = Double.parseDouble(y);
			listX1.add(x1);
			listY1.add(y1);
			}
		
		listX1size = listX1.size();
		listY1size = listY1.size();

		}	

	public double[] getListX1() {	
		Double[] x = listX1.toArray(new Double[listX1size]);
		double[] x1Val = ArrayUtils.toPrimitive(x);		
    	return x1Val;
	}

	public double[] getListY1() {
		Double[] y = listY1.toArray(new Double[listY1size]);
		double[] y1Val = ArrayUtils.toPrimitive(y);
    	return y1Val;
	}

}
