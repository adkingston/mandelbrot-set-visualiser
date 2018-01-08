package maths_simulator.mandelbrot_visualiser; 

import java.lang.Math;

class Complex implements Comparable<Complex>{
	
	private double _real;
	private double _imaginary;
	private double _mod;
	
	public Complex() {
	}
	
	public Complex(double r, double j) {
		_real = r;
		_imaginary = j;
		_mod = modulus(this);
	}
	
	public double getReal() {
		return _real;
	}
	
	public double getImaginary() {
		return _imaginary;
	}
	
	public String toString() {
		if (_imaginary != 0) {
			return _real + " + "  + _imaginary + "i";
		} else {
			return Double.toString(_real);
		}	
	}
	
	public double getModulus() {
		return _mod;
	}
	
	public void setReal(double x) {
		_real = x;
	}
	
	public void setImaginary(double y) {
		_imaginary = y;
	}
	
	/** 
	 * Gives distance of point from origin. 
	 * If c = x + yi, then modulus(c) = sqrt(x^2 + y^2) 
	 */
	public double modulus(Complex c) {
		return Math.sqrt(c.getReal()*c.getReal() + c.getImaginary()*c.getImaginary());
	}

	/**
	 * Compares modulus of two points
	 */
	public int compareTo(Complex c) {
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
	
	public boolean equals(Complex c) {
		return this.compareTo(c) == 0;
	}
	
	public Complex add(Complex c) {
		double x = this._real + c.getReal();
		double y = this._imaginary + c.getImaginary();
		return new Complex(x, y);
	}
	
	/**
	 * (x+iy)^2 = (x^2-y^2) + i(2xy)
	 */
	public Complex multiply(Complex c) {
		double x = this._real*c.getReal() - this._imaginary*c.getImaginary();
		double y = (this._real*c.getImaginary() + this._imaginary*c.getReal());
		return new Complex(x, y);
	}
	
	public Complex P(Complex c0) {
		Complex c = this.multiply(this);
		return c.add(c0);
	}
	
	public boolean isFinite() {
		return (Double.isFinite(this.getReal()) || Double.isFinite(this.getImaginary()));
	}
	
	/**
	 * P_c: C -> C given by P_c(z) = z^2 + c. If lim n-> infinity {modulus(P_c^n(0))} <= 2,
	 * then c is in the Mandelbrot set
	 * @return boolean
	 */
	public double mandelbrotSimpleConvergence() {
		// will check up to n=100. 
		Complex c = this.P(this);
		int n = 0;
		while (c.isFinite() && n < 1000) {
			c = c.P(this);
			System.out.println(c);
			n++;
		}
		return c.getModulus();
	}
	
}