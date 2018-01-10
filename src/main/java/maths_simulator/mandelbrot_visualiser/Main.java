package maths_simulator.mandelbrot_visualiser;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class Main {
	
	public static void main(String[] args) {
		System.out.println("Running");
        int width = 25100;
        int height = 23500;
        JFrame frame = new JFrame("Mandelbrot Set");

        MandelbrotSet panel = new MandelbrotSet(width, height);
        JScrollPane jsp = new JScrollPane(panel);
        frame.add(jsp);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		System.out.println("Done");
	}
	
	
	
}