package maths_simulator.mandelbrot_visualiser;

import processing.core.PApplet;
import processing.event.MouseEvent;

import java.awt.Color;
import java.lang.Math;
import java.util.ArrayList;

public class ComplexFractal extends PApplet{
	
	int scale = 500;

	float xmin, xmax, ymin, ymax;	
	double mX, mY, pmX, pmY; 
	
	int i = 0;
	
	ArrayList<Dimension> dimensions = new ArrayList<Dimension>();
	
    public static void main(String[] args) {
        PApplet.main(ComplexFractal.class.getName());
    }

    public void settings(){
       	xmin = -2.2f;
    	xmax = 1.2f;
    	ymin = -1.2f;
    	ymax = 1.2f;
    	
    	dimensions.add(new Dimension(xmin, xmax, ymin, ymax));
    	
    	int a = (int) (abs(xmin) + abs(xmax))*scale;
    	int b = (int) (abs(ymin) + abs(ymax))*scale;
    	size(a, b);
    	
    }

    public void setup() {
    	pixelDensity(1);
    	loadPixels();
    	makeFractal(i);
    }
    
    public void draw() {
    	makeFractal(i);
    }

    public void mousePressed() {
    	Dimension d = dimensions.get(i);
    	mX = map(mouseX, 0, width, d.getXMin(), d.getXMax());
    	mY = map(mouseY, 0, height, d.getYMin(), d.getYMax());
    }
    
//    public void mouseDragged() {
//    	stroke(255);
//    }
    
    public void mouseReleased() {
    	Dimension d = dimensions.get(i);
    	pmX = map(mouseX, 0, width, d.getXMin(), d.getXMax());
    	pmY = map(mouseY, 0, height, d.getYMin(), d.getYMax());
    	
    	Dimension delta = new Dimension((float) Math.min(mX, pmX), 
									    (float) Math.max(mX, pmX), 
									    (float) Math.min(mY, pmY), 
									    (float) Math.max(mY, pmY)
									   );
    	i++;
    	if (i < dimensions.size()) {
    		dimensions.set(i, delta);
    		
    		for (int j = i+1; j<dimensions.size(); j++) { //remove old dimensions
    			dimensions.remove(j);
    		}
    		
    	} else { 
    		dimensions.add(delta);
    	}
    	redraw();
    }
    
    public void keyPressed() {
    	if (key == CODED) {
	    	if (keyCode == 10) {
	    		i=0;
	    	} else if (keyCode == LEFT && i > 0) {
	    		i--;
	    	} else if (keyCode == RIGHT && i < dimensions.size()-1) {
	    		i++;
	    	}
    	}
    	redraw();
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
    
    public void makeFractal(int i) {
 
    	Dimension d = dimensions.get(i);
    	
	   	for (int x = 0; x < width; x++) {
    		
    		double zX = map(x, 0, width, d.getXMin(), d.getXMax());
    		
    		for (int y = 0; y < height; y++) {
    			
    			double zY = map(y, 0, height, d.getYMin(), d.getYMax());
    			
    			Complex c = new Complex(zX, zY);
    			
    			double colour = c.mandelbrotSimpleConvergence(100);
    			
    			
    			if (colour == 2) {
    				pixels[x+y*width] = color(0);
    			} else { 
    				
    				float red = cos((float) (0.1*colour*100)) * 127 + 128;
    				float green = sin((float) (0.2*colour*100) + 2) * 127 + 128;
    				float blue = sin((float) (0.3*colour*100) + 4) * 127 + 128;
    				
    				pixels[x+y*width] = color(red, green, blue);
    			}
    			
    		}
    	}	
	   	
	   	updatePixels();
    }
    
}