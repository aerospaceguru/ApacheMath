package com.martinoconnell.info;

import org.apache.commons.math3.analysis.polynomials.*;
import org.apache.commons.math3.analysis.solvers.*;

public class Main {
	
	static double[] c = {-3, 2, 1}; // Equation with coefficients in reverse order
	
	public static void main(String[] args) {
		
		long start = System.currentTimeMillis(); // calculation start time
		
		// Polynomials and first derivatives
		PolynomialFunction p = new PolynomialFunction(c);
		System.out.println(p.toString());
		System.out.println(p.polynomialDerivative());
		System.out.println(p.polynomialDerivative().value(5.0));
			
		// Solving root of polynomials using LaguerreSolver algorithm inputting (iterations, function, intervalMin, intervalMax)
		LaguerreSolver bs = new LaguerreSolver();
		double result = bs.solve(1000, p, -100, 100);
		System.out.println(result);
		System.out.println(p.value(result));
		
		long end  = System.currentTimeMillis(); // calculation end time
		
		System.out.println("\nCompute time is " + (end-start) + " ms"); // total calculation time

	}

}
