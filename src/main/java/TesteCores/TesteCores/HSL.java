package TesteCores.TesteCores;

import java.awt.Color;

/*
 * @author Karla Aparecida Justen <justen.karla@gmail.com>
 * 
 * About: https://www.w3schools.com/colors/colors_hsl.asp
 *        https://en.wikipedia.org/wiki/HSL_and_HSV
 *        
 * Source: https://www.rapidtables.com/convert/color/rgb-to-hsl.html
 */

public class HSL {
	
	private double H;
	private double S;
	private double L;
	
	public HSL(Color color) {
		RGBtoHSL(color);
	}
	
	public void RGBtoHSL(Color color){
		double redl = color.getRed()/255;
		double greenl = color.getGreen()/255;
		double bluel = color.getBlue()/255;
		
		double cmax = Math.max(Math.max(redl, greenl), bluel);
		double cmin = Math.min(Math.min(redl, greenl), bluel);
		
		double delta = cmax - cmin;
		
		H = calculateHue(redl,greenl, bluel, cmax, delta);
		L = calculateLightness(cmax, cmin);
		S = calculateSaturation(L, delta);
	}
	
	
	private double calculateHue(double redl, double greenl, double bluel, double cmax, double delta) {
		
		if(delta == 0){
			return 0;
		} 
		else if(cmax == redl){
			return (60* (((greenl-bluel)/delta) % 6)) % 360; // % 360 because is in degrees
		}
		else if (cmax == greenl){
			return (60* (((bluel-redl)/delta)+2)) % 360; // % 360 because is in degrees
		}
		else if (cmax == bluel){
			return (60* (((redl-greenl)/delta)+4)) % 360; // % 360 because is in degrees
		}
		
		return 0; //unreachable
		
	}
	
	private double calculateLightness(double cmax, double cmin){
		return (cmax + cmin)/2;
	}
	
	private double calculateSaturation(double l, double delta){
		if (delta == 0){
			return 0;
		}
		else{ //delta != 0
			return delta/(1-Math.abs(2*l-1));
		}
	}
	
	public double getH(){
		return H;
	}
	
	public double getS(){
		return S;
	}
	
	public double getL(){
		return L;
	}
}