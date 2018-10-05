package TesteCores.TesteCores;

import java.awt.Color;

//Based on same code Aldo send me by e-mail
public class HSV {

	private double h;
	private double s;
	private double v;
	
	public HSV(Color color) {
		RGBtoHSV(color);
	}
	
	public void RGBtoHSV(Color color){
		
		double r = color.getRed();
		double g = color.getGreen();
		double b = color.getBlue();

	    double min, max, delta;

	    min = Math.min(Math.min(r, g), b);
	    max = Math.max(Math.max(r, g), b);

	    // V
	    v = max;

	     delta = max - min;

	    // S
	     if( max != 0 )
	        s = delta / max;
	     else {
	        s = 0;
	        h = -1;
	        //return new double[]{h,s,v};
	     }

	    // H
	     if( r == max )
	        h = ( g - b ) / delta; // between yellow & magenta
	     else if( g == max )
	        h = 2 + ( b - r ) / delta; // between cyan & yellow
	     else
	        h = 4 + ( r - g ) / delta; // between magenta & cyan

	     h *= 60;    // degrees

	    if( h < 0 )
	        h += 360;

	    //return new double[]{h,s,v};
	}

	public double getH(){
		return h;
	}
	
	public double getS(){
		return s;
	}
	
	public double getV(){
		return v;
	}
	
	
}
