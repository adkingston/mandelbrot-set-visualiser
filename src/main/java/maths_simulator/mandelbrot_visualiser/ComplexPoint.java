package maths_simulator.mandelbrot_visualiser; 

import java.lang.Math;

class ComplexPoint implements Comparable<ComplexPoint>{
	
	double _real;
	double _imaginary;
	double _mod;
	
	public ComplexPoint(double r, double j) {
		_real = r;
		_imaginary = j;
		_mod = modulus(this);
	}
	
	/** 
	 * Gives distance of point from origin. 
	 * If c = x + yi, then modulus(c) = sqrt(x^2 + y^2) 
	 */
	public double modulus(ComplexPoint c) {
		return Math.sqrt(c._real*c._real + c._imaginary*c._imaginary);
	}

	/**
	 * Compares modulus of two points
	 */
	public int compareTo(ComplexPoint c) {
		double a = _mod;
		double b = modulus(c);
		if (b < a) {
			return 1;
		} else if (b == a) {
			return 0;
		} else {
			return -1;
		}
	}
	
	public boolean equals(ComplexPoint c) {
		return this.compareTo(c) == 0;
	}
	
	public int mandelbrotConvergence() {
		
	}
	
}