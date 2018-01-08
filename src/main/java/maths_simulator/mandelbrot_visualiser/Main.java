package maths_simulator.mandelbrot_visualiser;

import javax.swing.JFrame;

public class Main {
	
	public static void main(String[] args) {
		
        int width = 1251;
        int height = 1001;
        JFrame frame = new JFrame("Mandelbrot Set");

        MandelbrotSet panel = new MandelbrotSet(width, height);

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	
	
}