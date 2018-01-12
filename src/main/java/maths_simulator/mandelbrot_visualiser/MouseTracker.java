package maths_simulator.mandelbrot_visualiser;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class MouseTracker extends Main implements MouseWheelListener, MouseMotionListener {

	double _scalingFactor = 0.01;
	public double newScaling = 0;
	public Point pos = new Point(0,0);
	public PointerInfo a;
	
	public void mouseWheelMoved(MouseWheelEvent event) {
		newScaling += -event.getWheelRotation()*_scalingFactor;
		a = MouseInfo.getPointerInfo();
		pos  = a.getLocation();
	
		frame.remove(panel);
		frame.add(new MandelbrotSet(width, height, newScaling, 50, pos));
		frame.pack();
		
	}
	
	public double getScaling() {
		return newScaling;
	}

	public Point getPos() {
		return pos;
	}
	
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
	}



	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("X : " + e.getX());
		System.out.println("Y : " + e.getY());
	}

}