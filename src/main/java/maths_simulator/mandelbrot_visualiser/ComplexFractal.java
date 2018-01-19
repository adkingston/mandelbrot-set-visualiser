package maths_simulator.mandelbrot_visualiser;

import processing.core.PApplet;
import processing.event.MouseEvent;

import java.awt.Color;
import java.lang.Math;

public class ComplexFractal extends PApplet{
	
	float angle = PI/4;
	int scale = 500;
	
	float zoom, transX, transY;
	float xmin, xmax, ymin, ymax;
	boolean r = false;
	
	double mX, mY, pmX, pmY; 
	
	int posX, posY;
	
	int c1 = color(204, 102, 0);
	int c2 = color(0, 102, 153);
	
    public static void main(String[] args) {
        PApplet.main(ComplexFractal.class.getName());
    }

    public void settings(){
    	int a = (int) (2.2+1.2)*scale;
    	int b = (int) 1.2*2*scale;
    	size(a, b);
    }

    public void setup() {
    	pixelDensity(1);
    	loadPixels();
    
    	
    	xmin = -2.2f;
    	xmax = 1.2f;
    	ymin = -1.2f;
    	ymax = 1.2f;
    	
    	makeFractal();
    }
    
    public void draw() {
    	makeFractal();
    }

    public void mousePressed() {
    	
    	posX = mouseX;
    	posY = mouseY;

    	mX = map(posX, 0, width, xmin, xmax);
    	mY = map(posY, 0, height, ymin, ymax);
    	
    }
    
    public void mouseDragged() {
    	stroke(255);
    	
    }
    
    public void mouseReleased() {
    	
       	pmX = map(pmouseX, 0, width, xmin, xmax);
    	pmY = map(pmouseY, 0, height, ymin, ymax);
    	
    	System.out.println("(" + mX + ", " + mY + ", " + pmX + ", " + pmY + ")");
    	
    	xmin = (float) Math.min(mX, pmX);
    	xmax = (float) Math.max(mX, pmX);
    	
    	ymin = (float) Math.min(mY, pmY);
    	ymax = (float) Math.max(mY,  pmY);
    	
    }
    
    public void keyTyped() {
    	if (keyCode == 0) {
    		System.out.println("True");
    		xmin = -2.2f;
    		xmax = 1.2f;
    		ymin = -1.2f;
    		ymax = 1.2f;
    	} else {
    		System.out.println("False");
    	}
    }
    
    /**
     * map : [n, m] -> [x, y]
     * @param z point to map
     * @param n beginning of domain
     * @param m end of domain
     * @param x beginning of range
     * @param y end of range
     * @return map(z), double
     */
    public double map(int z, int n, int m, float x, float y) {
    	return ((y - x)/((double) m - n))*z + ((x*m + y*n)/((double) m + n)); 
    }
    
    public void makeFractal() {
	   	for (int x = 0; x < width; x++) {
    		
    		double zX = map(x, 0, width, xmin, xmax);
    		
    		for (int y = 0; y < height; y++) {
    			
    			double zY = map(y, 0, height, ymin, ymax);
    			
    			Complex c = new Complex(zX, zY);
    			
    			double colour = c.mandelbrotSimpleConvergence(100);
    			
    			
    			if (colour == 2) {
    				pixels[x+y*width] = color(0);
    			} else { 
    				
    				float red = cos((float) (0.1*colour*50)) * 127 + 128;
    				float green = sin((float) (0.2*colour*50) + 2) * 127 + 128;
    				float blue = sin((float) (0.3*colour*50) + 4) * 127 + 128;
    				
    				pixels[x+y*width] = color(red, green, blue);
    			}
    			
    		}
    	}	
	   	
	   	updatePixels();
    }
    
}