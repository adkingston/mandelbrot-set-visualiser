package maths_simulator.mandelbrot_visualiser;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

class JuliaSet extends JPanel{
	
	private Complex _c;
	private BufferedImage canvas;
	private int WIDTH;
	private int HEIGHT;
	
	public JuliaSet(Complex c, int width, int height, double dm, int k, Point p) {
		_c = c;
    	WIDTH = width;
    	HEIGHT = height;
    	
        canvas = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        for (int x=0; x<width; x++) {
        	for (int y=0; y<height; y++) {
        		double[] P = pixelToPoint(x, y, dm, p);
        		Complex z = new Complex(P[0], P[1]);
        		float mod = (float) juliaConvergence(z);
        		if (mod == 2) {
        			canvas.setRGB(x,  y,  Color.BLACK.getRGB());
        		} else {
        			canvas.setRGB(x, y, Color.HSBtoRGB(0.7f + mod*0.3f ,0.95f, 1.0f));
        		}
        	}
        }
    }
	
  public Dimension getPreferredSize() {
        return new Dimension(canvas.getWidth(), canvas.getHeight());
    }

    public void paintComponent(Graphics g) {
    	// overrides JPanel's paintComponent method
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(canvas, null, null);
    }
    
    public double map(double z, double n, double m, double x, double y) {
    	return ((y - x)/(m - n))*z + ((x*m + y*n)/(m+n)); 
    }
    
    public double[] pixelToPoint(int x, int y, double dm, Point pos) {
    	double[] point = new double[2];
    	double posX = map(pos.getX(), 0, WIDTH, -2.2 + dm, 1.2 - dm);
    	double posY = map(pos.getY(), 0, HEIGHT, -1.2 + dm, 1.2 - dm);
    	double a = map(x, 0, WIDTH, -2.2 + dm + posX, 1.2 - dm + posX);
    	double b = map(y, 0, HEIGHT, -1.12 + dm + posY, 1.12 - dm + posY);
    	point[0] = a;
    	point[1] = b;
    	return point;
    }
	
	public double juliaConvergence(Complex z) {
		
		// will check up to n=200. 
		
		Complex c = z.pow(2).add(_c);
		double sn = 0;
		int n = 1;
		while (c.modulus() <= 2.0 && n < 50 ){
			c = c.pow(2).add(_c);
			n++;
		}
		if (c.modulus() > 2) {
			sn = (n - Math.log(Math.log(c.modulus())/Math.log(2)))/((float) 50);
		} else {
			sn = 2;
		}
		return sn;
		
	}
	
}

