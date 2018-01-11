package maths_simulator.mandelbrot_visualiser;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Main {
	
	public static MandelbrotSet panel;
	public static JFrame frame;
    public static int width = 640;
    public static int height = 480;
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable()
	       {
	           public void run()
	           {
	               createAndShowGUI();
	           }
	       });
	}
	
	public static void createAndShowGUI() {
		System.out.println("Running");

        frame = new JFrame("Mandelbrot Set");

        frame.getContentPane().setLayout(new BorderLayout());
        MouseTracker m = new MouseTracker();
        MouseTracker n = new MouseTracker();
              
        frame.addMouseWheelListener(m);
        frame.addMouseMotionListener(n);
        panel = new MandelbrotSet(width, height, m.getScaling(), 500, new Point(width/2, height/2));
        

        
        JScrollPane jsp = new JScrollPane(panel);
        
        frame.add(jsp, BorderLayout.CENTER);
        
        
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		System.out.println("Done");
	}

}