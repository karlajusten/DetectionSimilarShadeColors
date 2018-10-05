package TesteCores.TesteCores;

import java.awt.Color;

/*
 * Totally based on: https://github.com/antimatter15/rgb-lab/blob/master/color.js
 */
public class LAB {

	private double B;
	private double A;
	private double L;
	
	public LAB(Color color) {
		rgb2lab(color);
	}
	
	private void  rgb2lab(Color color){
	  double r = color.getRed() / 255;
	  double g = color.getGreen() / 255;
	  double b = color.getBlue() / 255;
	  double x, y, z;

	  r = (r > 0.04045) ? Math.pow((r + 0.055) / 1.055, 2.4) : r / 12.92;
	  g = (g > 0.04045) ? Math.pow((g + 0.055) / 1.055, 2.4) : g / 12.92;
	  b = (b > 0.04045) ? Math.pow((b + 0.055) / 1.055, 2.4) : b / 12.92;

	  x = (r * 0.4124 + g * 0.3576 + b * 0.1805) / 0.95047;
	  y = (r * 0.2126 + g * 0.7152 + b * 0.0722) / 1.00000;
	  z = (r * 0.0193 + g * 0.1192 + b * 0.9505) / 1.08883;

	  x = (x > 0.008856) ? Math.pow(x, 1/3) : (7.787 * x) + 16/116;
	  y = (y > 0.008856) ? Math.pow(y, 1/3) : (7.787 * y) + 16/116;
	  z = (z > 0.008856) ? Math.pow(z, 1/3) : (7.787 * z) + 16/116;

	  L = (116 * y) - 16;
	  A = 500 * (x - y);
	  B = 200 * (y - z);
	}
	
	public double getB(){
		return B;
	}
	
	public double getA(){
		return A;
	}
	
	public double getL(){
		return L;
	}
}
