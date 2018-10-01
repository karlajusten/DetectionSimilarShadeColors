package TesteCores.TesteCores;

import java.awt.Color;

/*
 * @author Karla Aparecida Justen <justen.karla@gmail.com>
 * 
 * Source:
 * http://citeseerx.ist.psu.edu/viewdoc/citations;jsessionid=7718CD91A6C4AFF6258358E632FCF8C1?doi=10.1.1.125.3833
 * 
 * A New Perceptually Uniform Color Space with Associated Color Similarity Measure 
 * for Content-Based Image and Video Retrieval 
 * by M. Sarifuddin and Rokia Missaoui
 */

public class HCL {

	private double H;
	private double C;
	private double L;
	
	public HCL(Color color) {
		RGBtoHCL(color);
	}
	
	private void RGBtoHCL(Color color) {
		int gMinusB = color.getGreen() - color.getBlue();
		int rMinusG = color.getRed() - color.getGreen();
		int bMinusR = color.getBlue() - color.getRed();
		//double q = calculateQ(color);
		
		H = calculateHue(rMinusG, gMinusB);
		/*C = calculateChorma(rMinusG, gMinusB, bMinusR, q);
		L = calculateLuminance(q, color);*/
		
	}

	public double calculateHue(double rMinusG, double gMinusB){
		double H = Math.atan(gMinusB/rMinusG);
		if(rMinusG<0){
			if(gMinusB>=0){
				return 180 + 1.333333333*H;
			}else { //gMinusB<0
				return 0.75*H - 180;
			}
		}
		else { // rMinusG>=0
			if(gMinusB>=0){
				return 0.666666667*H;
			}else { //gMinusB<0
				return 1.333333333*H;
			}
		}
	}
	
	public double calculateQ(Color color){
		double alpha = (Math.min((Math.min(color.getRed(), color.getGreen())),color.getGreen())
				/Math.max((Math.max(color.getRed(), color.getGreen())),color.getGreen())*100);
		
		return Math.pow(Math.E, alpha*3);
	}
	
	
	public double calculateChorma(double rMinusG, double gMinusB, double bMinusR, double q){
		
		return (q*(Math.abs(rMinusG)+
								  Math.abs(gMinusB)+
								  Math.abs(bMinusR)))/3 ;
	
	}
	
	public double calculateLuminance(double q, Color color){
		
		return ((q*Math.max((Math.max(color.getRed(), color.getGreen())),color.getGreen())) + 
				((1-q)*(Math.min((Math.min(color.getRed(), color.getGreen())),color.getGreen()))))/2;
	}

	public double getH(){
		return H;
	}
	
	public double getC(){
		return C;
	}
	
	public double getL(){
		return L;
	}
	
}
