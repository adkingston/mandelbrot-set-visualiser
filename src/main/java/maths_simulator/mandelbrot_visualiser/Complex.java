package maths_simulator.mandelbrot_visualiser; 

import java.lang.Math;
import java.util.ArrayList;

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
	
	public double log(double a) {
		return Math.log(a);
	}
	
	public double log2(double a) {
		return Math.log(a)/Math.log(2.0);
	}
	
	public double getReal() {
		return _real;
	}
	
	public double getImaginary() {
		return _imaginary;
	}
	
	public String toString() {
		if (_imaginary > 0) {
			return _real + " + "  + _imaginary + "i";
		} else if (_imaginary < 0) {
			return _real + _imaginary + "i";
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
	
	public double modulus() {
		return Math.sqrt(this.getReal()*this.getReal() + this.getImaginary()*this.getImaginary());
	}
	
	public Complex conjugate() {
		return new Complex(this._real, -this._imaginary);
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

	
	public double dot(Complex a, Complex b) {
		return a.getReal()*b.getReal() + a.getImaginary()*b.getImaginary();
	}

	
	
	/**
	 * P_c: C -> C given by P_c(z) = z^2 + c. If lim n-> infinity {modulus(P_c^n(0))} <= 2,
	 * then c is in the Mandelbrot set
	 * @return boolean
	 */
	public double mandelbrotSimpleConvergence() {
		// will check up to n=200. 
		
		Complex c = this.multiply(this).add(this);
		double sn = 0;
		int n = 1;
		while (c.modulus() <= 2.0 && n < 100 ){
			c = c.multiply(c).add(this);
			n++;
		}
		if (c.modulus() > 2) {
			sn = (n - Math.log(Math.log(c.modulus())/Math.log(2)))/((float) 100);
		} else {
			sn = 2;
		}
		return sn;
		
	}
	
//	public int escapeAlgorithm() {
//		Complex c = this.P(this);
//		Complex c1 = this.P(this);
//		double mod = c1.modulus();
//		int n = 0;
//		while (mod <= 2 && n < 255) {
//			c = c1.P(this);
//			c1 = c;
//			mod = c1.modulus();
//			n++;
//		}
//		return n;
//	}
	
}