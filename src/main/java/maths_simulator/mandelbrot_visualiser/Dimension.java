package maths_simulator.mandelbrot_visualiser;

public class Dimension {
	
	float xMin; 
	float xMax;
	float yMin;
	float yMax;
	
	public Dimension(float xmin, float xmax, float ymin, float ymax) {
		xMin = xmin;
		xMax = xmax;
		yMin = ymin;
		yMax = ymax;
	}
	
	public float getXMin() {
		return xMin;
	}
	
	public float getXMax() {
		return xMax;
	}
	
	public float getYMin() {
		return yMin;
	}
	
	public float getYMax() {
		return yMax;
	}
	
	public String toString() {
		return ("(" + xMin + ", " + yMin + "), (" + xMax + ", " + yMax + ")");
	}
	
}