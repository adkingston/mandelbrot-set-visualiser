package maths_simulator.mandelbrot_visualiser;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.lang.Math;

import javax.swing.JPanel;

public class MandelbrotSet extends JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private BufferedImage canvas;
    private int WIDTH;
    private int HEIGHT;

    public MandelbrotSet(int width, int height) {
    	WIDTH = width;
    	HEIGHT = height;
    	
        canvas = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        for (int x=0; x<width; x++) {
        	for (int y=0; y<height; y++) {
        		double[] P = pixelToPoint(x, y);
        		Complex c = new Complex(P[0], P[1]);
        		float mod = (float) c.mandelbrotSimpleConvergence();
        		if (mod == 2) {
        			paintPixel(Color.BLACK, x, y);
        		} else {
        			canvas.setRGB(x, y, Color.HSBtoRGB(2*mod ,0.6f,1.0f));
//        			paintPixel(
//        					new Color(mod, mod, mod
////        							  (float) Math.abs(Math.sin(mod)), 
////        							  (float) Math.abs(Math.sin(mod)), 
////        							  (float) Math.abs(Math.sin(mod))
//        							  ), 
//        					x, y);
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

    public void paintPixel(Color c, int x, int y) {
        int color = c.getRGB();
        canvas.setRGB(x, y, color);
        repaint();
    }

    public double[] pixelToPoint(int x, int y) {
    	double[] point = new double[2];
    	double a = (x - ((WIDTH-(WIDTH/2.0)/2.0)))/(WIDTH-(WIDTH/2.0)/2.0)*2;
    	double b = ((HEIGHT/2.0) - y)/(HEIGHT/2.0)*1.2;
    	point[0] = a;
    	point[1] = b;
    	return point;
    }

}