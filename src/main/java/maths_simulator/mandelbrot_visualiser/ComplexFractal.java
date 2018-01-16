package maths_simulator.mandelbrot_visualiser;

import processing.core.PApplet;
import processing.event.MouseEvent;
import java.lang.Math;

public class ComplexFractal extends PApplet{
	
	float angle = PI/4;
	int scale = 500;
	
	float zoom, transX, transY;
	float xmin, xmax, ymin, ymax;
	boolean r = false;
	
	double mX, mY, pmX, pmY; 
	
	int posX, posY;
	
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
    }
    
    public void draw() {
    	

	   	for (int x = 0; x < width; x++) {
    		
    		double zX = map(x, 0, width, xmin, xmax);
    		
    		for (int y = 0; y < height; y++) {
    			
    			double zY = map(y, 0, height, ymin, ymax);
    			
    			Complex c = new Complex(zX, zY);
    			
    			double colour = c.mandelbrotSimpleConvergence(50);
    			
    			
    			if (colour == 2) {
    				pixels[x+y*width] = color(0);
    			} else { 
    				pixels[x+y*width] = color((float) colour*255, 0, (float) colour*255);
    			}
    			
    		}
    	}	
	   	
	   	updatePixels();
    }

    public void mousePressed() {
    	
    	posX = mouseX;
    	posY = mouseY;

    	mX = map(posX, 0, width, xmin, xmax);
    	mY = map(posY, 0, height, ymin, ymax);
    	
    	
       	stroke(255);
    	noFill();
    	rect(posX, posY, abs(posX - pmouseX), abs(posY - pmouseY));
    }
    
    public void mouseDragged() {
    	stroke(255);
    	noFill();
    	rect(posX, posY, abs(posX - pmouseX), abs(posY - pmouseY));
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
    	if (keyCode == ENTER) {
    		xmin = -2.2f;
    		xmax = 1.2f;
    		ymin = -1.2f;
    		ymax = 1.2f;
    				
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
    
}