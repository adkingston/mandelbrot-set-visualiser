package maths_simulator.mandelbrot_visualiser;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import maths_simulator.mandelbrot_visualiser.Complex;

public class ComplexTest {
	
	private double _x;
	private double _y;
	private Complex _z;
	
	@Before
	public void beforeTests() {
		_x = -1;
		_y = 0;
	}

	@Test
	public void stringToFuncTest() {
		_z = new Complex(_x, _y);
//		_z.stringToFunc("6*z^2+3i + c", new Complex(0, 0), new Complex(0, 0));
	}
//	@Test
//	public void initializeTest() {
//		_z = new Complex(_x, _y);
//	}
//	
	@Test
	public void realArithmeticTest() {
		_z = new Complex(-1, 0);
		
		String a = _z.toString();
		assertTrue(a.equals("-1.0"));
		
		double b = _z.getModulus();
		assertTrue(b == 1);
		
		Complex c = _z.add(_z);
		assertTrue(c.equals(new Complex(-2, 0)));
		
		Complex d = _z.multiply(_z);
		assertTrue(d.equals(new Complex(1, 0)));
	}
//	
//	@Test
//	public void complesArithmeticTest() {
//		_z = new Complex(-1, 2);
//		
//		String a = _z.toString();
//		assertTrue(a.equals("-1.0 + 2.0i"));
//		
//		double b = _z.getModulus();
//		assertTrue(b == Math.sqrt(5));
//		
//		Complex c = _z.add(_z);
//		assertTrue(c.equals(new Complex(-2, 4)));
//		
//		Complex d = _z.times(_z);
//		assertTrue(d.equals(new Complex(-3, -4)));
//		
//		Complex e = _z.times(new Complex(2, 0));
//		System.out.println(e);
//	}
//	
//	@Test
//	public void mandelbrotConvergenceTest() {
//		_z = new Complex(-1, 0);
//		// a value of 2 signifies _z is in the Mandelbrot set
//		assertTrue(_z.mandelbrotSimpleConvergence() == 2);
//	}
	
}