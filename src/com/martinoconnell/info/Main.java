package com.martinoconnell.info;

import org.apache.commons.math3.analysis.polynomials.*;
import org.apache.commons.math3.analysis.solvers.*;
import org.knowm.xchart.*;

public class Main {
	
	static double[] c = {-3, 2, 2, 1}; // Equation with coefficients in reverse order

	public static void main(String[] args) {

		long start = System.currentTimeMillis(); // calculation start time

		// EXERCISE ONE - Polynomials and first derivatives
		PolynomialFunction p = new PolynomialFunction(c);
		System.out.println("The polynomial is: " + p.toString());
		System.out.println("The first derivitive is: " + p.polynomialDerivative());
		System.out.println("The first derivitive evaluated at 5 is: " + p.polynomialDerivative().value(5.0) + "\n");

		// EXERCISE TWO - Solving root of polynomials using LaguerreSolver algorithm inputting (iterations, function, intervalMin, intervalMax)
		LaguerreSolver ls = new LaguerreSolver();
		double result = ls.solve(1000, p, -5, 5);
		System.out.println("The first root of the polynomial along the interval is: " + result);
		System.out.println("Polynomial evaluated with root is: " + p.value(result) + "\n");

		// EXERCISE THREE - Creating an array of eleven values from -5 to +5 and plotting the graph
		double[] xVal = new double[11];
		double[] yVal = new double[11];

		for(int i=0; i<11; i++) { xVal[i] = i-5;}

		for(int j=0; j<11; j++) {

			double x = xVal[j];
			yVal[j] = p.value(x);
			System.out.println("x value is: "+ xVal[j] + " y value is: " + yVal[j]);

		}

		XYChart chart = QuickChart.getChart("Sample Plotting Chart", "X Values", 
				"Y Values", "y(x)", xVal, yVal); // creates the chart
		new SwingWrapper(chart).displayChart(); // displays the chart
		

		long end  = System.currentTimeMillis(); // calculation end time
		System.out.println("\nCompute time is " + (end-start) + " ms"); // total calculation time

	}

}
