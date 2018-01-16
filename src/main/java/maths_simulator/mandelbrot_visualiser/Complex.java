package maths_simulator.mandelbrot_visualiser; 

import java.lang.Math;

interface ComplexNums<TA, TM> {
	TA add(TA c); 
	TA negate();
	TA multiply(TM c);
}

class Complex implements Comparable<Complex>, ComplexNums<Complex, Complex>{
	
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
		return new Complex(this._real + c.getReal(), this._imaginary + c.getImaginary());
	}
	
	public Complex negate() {
		return new Complex(-this._real, -this._imaginary);
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
	
	public Complex pow(int n) {
		Complex c = this;
		for (int i=1; i<n; i++) {
			c = c.multiply(this);
		}
		return c;
	}
	
	/**
	 * P_c: C -> C given by P_c(z) = z^2 + c. If lim n-> infinity {modulus(P_c^n(0))} <= 2,
	 * then c is in the Mandelbrot set
	 * @return 2 if z is in the Mandelbrot set a double between 0 and 1 otherwise to determine colouring
	 */
	public double mandelbrotSimpleConvergence(int k) {
		// will check up to n=200. 
		
		Complex c = this.pow(23).add(this);
		double sn = 0;
		int n = 1;
		while (c.modulus() <= 2.0 && n < k ){
			c = c.pow(23).add(this);
			n++;
		}
		
		if (c.modulus() > 2) {
			sn = (n - Math.log(Math.log(c.modulus())/Math.log(2)))/((float) k);
		} else {
			sn = 2;
		}
		return sn;
		
	}
	/**
	 * 
	 * @param f String of form (a_d*z^d + b_d*c^k_d + l_d) + (a_(d-1)*z^(d-1) + b_(d-1)*c^k_(d-1) + l_(d-1) + ... + (a_0 + b_0*c^k_0 + l_0)
	 * @param z Complex number z to input
	 * @param c Complex number c to input
	 * @return value of f(z,c) 
	 */
//	
//	public Complex stringToFunc(String f, Complex z, Complex c) {
//		Complex j = new Complex(0,0);
//		Expression e = new ExpressionBuilder(f)
//							.variables("z", "c")
//							.build()
//							.setVariable("z", z)
//							.setVariable("c", c);;
//		return j;
//	}
//	


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