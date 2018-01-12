package maths_simulator.mandelbrot_visualiser;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Main {
	
	public static MandelbrotSet panel;
	public static JFrame frame;
    public static int width = 680;
    public static int height = 480;
	
    public static MouseTracker track = new MouseTracker();
    
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
             
        panel = new MandelbrotSet(width, height, 0.0, 50, new Point(width/2, height/2));
        
        makeButtons();
        
        JScrollPane jsp = new JScrollPane(panel);
        
        frame.add(jsp, BorderLayout.CENTER);
        
        
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		System.out.println("Done");
	}

	public static void makeButtons() {
        JPanel buttons = new JPanel(new BorderLayout());
        
        final JButton zoom = new JButton("Zoom in");
        final JButton julia = new JButton("View Julia Sets");
        
        zoom.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent implementZoom) {
        		if (julia.isEnabled()) {
        			panel.removeMouseMotionListener(track);
        		}
        		panel.addMouseWheelListener(track);
        	}
        });
        
        julia.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent trackMouse) {
        		if (zoom.isEnabled()) {
        			System.out.println(zoom.isEnabled());
        			panel.removeMouseWheelListener(track);
        		}
        		panel.addMouseMotionListener(track);
        		JFrame juliaFrame = new JFrame("Julia Set");
        		JPanel juliaPanel = new JuliaSet(new Complex(track.getPos().getX(), track.getPos().getY()),
        													 width, 
        													 height,
        													 0.0,
        													 50,
        													 new Point(0,0));
        		
        		juliaFrame.setLocation(600, 600);
        		
        		JScrollPane jspJulia = new JScrollPane();
        		jspJulia.add(juliaPanel);
        		juliaFrame.add(jspJulia, BorderLayout.CENTER);
        		juliaFrame.setVisible(true);
        	}
        });
        
        buttons.add(zoom, BorderLayout.EAST);
        buttons.add(julia, BorderLayout.WEST);
        
        frame.add(buttons,  BorderLayout.NORTH); 
	}
	
}