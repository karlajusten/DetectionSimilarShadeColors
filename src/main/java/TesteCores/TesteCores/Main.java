package TesteCores.TesteCores;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Main {
	
	public static void main(String[] args) throws IOException {
				
		Colors colors = new Colors();
		List<List<Color>> shades = colors.getAllShades();
		List<String> nameShades = colors.getNamesOfShades();
		// Create new Excel workbook and sheet
	    Workbook workbook = new XSSFWorkbook();
	    
	    for(int s = 0; s<shades.size(); s++){
	    	for(int z = s; z<shades.size(); z++){
	    		Sheet sheet = workbook.createSheet(nameShades.get(s).concat("AND").concat(nameShades.get(z)));
	    	    
			    Row titleRow = sheet.createRow(0);
			    titleRow.createCell(0).setCellValue("R1");
			    titleRow.createCell(1).setCellValue("G1");
			    titleRow.createCell(2).setCellValue("B1");
			    titleRow.createCell(3).setCellValue("R2");
			    titleRow.createCell(4).setCellValue("G2");
			    titleRow.createCell(5).setCellValue("B2");
			    titleRow.createCell(6).setCellValue("Euclidean Distance1");
			    titleRow.createCell(7).setCellValue("Euclidean Distance2");
			    titleRow.createCell(8).setCellValue("H Variance (HSL)");
			    titleRow.createCell(9).setCellValue("HSL - SL equals");
			    titleRow.createCell(10).setCellValue("H Variance (HCL)");
			    titleRow.createCell(11).setCellValue("DeltaE LAB 94");
			    titleRow.createCell(12).setCellValue("L Variance (LAB 94)");
			    titleRow.createCell(13).setCellValue("DeltaE LAB 76");
			    titleRow.createCell(14).setCellValue("H Variance (HSV)");
			    compareShades(shades.get(s), shades.get(z), sheet);
	    	}
	    }
	    
	    // Write to disk	
	    FileOutputStream out = new FileOutputStream(new File("/home/karla/Área de Trabalho/TesteCores.xlsx"));
	    workbook.write(out);
		out.close();
		
	    
		workbook.close();
		
	    System.out.println("Excel written successfully..");
	}
	
	private static void compareShades(List<Color> shade1, List<Color> shade2, Sheet sheet){
		int row = 1;
	    for (int i = 0; i<shade1.size(); i++) {
	        for(int j=0; j<shade2.size(); j++){
	        	Row newRow = sheet.createRow(row);
		        newRow.createCell(0).setCellValue(Integer.toHexString(shade1.get(i).getRed()));
		        newRow.createCell(1).setCellValue(Integer.toHexString(shade1.get(i).getGreen()));
		        newRow.createCell(2).setCellValue(Integer.toHexString(shade1.get(i).getBlue()));
		        newRow.createCell(3).setCellValue(Integer.toHexString(shade2.get(j).getRed()));
		        newRow.createCell(4).setCellValue(Integer.toHexString(shade2.get(j).getGreen()));
		        newRow.createCell(5).setCellValue(Integer.toHexString(shade2.get(j).getBlue()));
		        newRow.createCell(6).setCellValue(euclideanDistance1(shade1.get(i), shade2.get(j)));
		        newRow.createCell(7).setCellValue(euclideanDistance2(shade1.get(i), shade2.get(j)));
		        newRow.createCell(8).setCellValue(calculateHVarianceHSL(shade1.get(i), shade2.get(j)));
		        newRow.createCell(9).setCellValue(myIdeaHSL(shade1.get(i), shade2.get(j)));
		        newRow.createCell(10).setCellValue(calculateHVarianceHCL(shade1.get(i), shade2.get(j)));
		        newRow.createCell(11).setCellValue(deltaE_LAB_94(shade1.get(i), shade2.get(j)));
		        newRow.createCell(12).setCellValue(calculateLVarianceLAB94(shade1.get(i), shade2.get(j)));
		        newRow.createCell(13).setCellValue(deltaE_LAB_76(shade1.get(i), shade2.get(j)));
		        newRow.createCell(14).setCellValue(calculateHVarianceHSV(shade1.get(i), shade2.get(j)));
		        
		        row++;
	       }
	        
	     }
	}
		

	/*
	 * Source: https://stackoverflow.com/questions/1725505/finding-similar-colors-programmatically
	 * 		   https://en.wikipedia.org/wiki/Color_difference
	 * When:
	 * 		color1 == color2 --> result = 0
	 * 		color1 = black; color2 = white --> result = 441.6729559300637
	 */
	private static double euclideanDistance1(Color color1, Color color2 ){
		
		return Math.sqrt(
			   ((color1.getRed()   - color2.getRed())  * (color1.getRed()   - color2.getRed()))   + 
			   ((color1.getGreen() - color2.getGreen()) * (color1.getGreen() - color2.getGreen())) + 
			   ((color1.getBlue()  - color2.getBlue())  * (color1.getBlue()  - color2.getBlue())));
		
	}
	
	/*
	 * Source: https://en.wikipedia.org/wiki/Color_difference
	 * When:
	 * 		color1 == color2 --> result = 0
	 * 		color1 = black; color2 = white --> result = 441.6729559300637
	 */
	private static double euclideanDistance2(Color color1, Color color2 ){
		
		double r = (color1.getRed() + color2.getRed());
		double deltaR = (color1.getRed()   - color2.getRed());
		double deltaG = (color1.getGreen() - color2.getGreen());
		double deltaB = (color1.getBlue()  - color2.getBlue());
		double last = (r*((deltaR*deltaR) - (deltaB*deltaB))/256);
				
		return Math.sqrt(
				   2*(deltaR * deltaR) + 
				   4*(deltaG * deltaG) + 
				   3*(deltaB * deltaB) +
				   last);
		
	}
	
	/*
	 * Source: https://stackoverflow.com/questions/4754506/color-similarity-distance-in-rgba-color-space
	 * When:
	 * 		color1 == color2 --> result = 780300
	 * 		color1 = black; color2 = white --> result = 1.2486555675E10
	 */
	private static double checkSimilarity(Color color1, Color color2 ){
		/*
		 * max((r₁-r₂)², (r₁-r₂ - a₁+a₂)²) +
			max((g₁-g₂)², (g₁-g₂ - a₁+a₂)²) +
			max((b₁-b₂)², (b₁-b₂ - a₁+a₂)²)
		 */
		int redSubtraction = color1.getRed()-color2.getRed();
		int greenSubtraction = color1.getGreen()-color2.getGreen();
		int blueSubtraction = color1.getBlue()-color2.getBlue();
		int alphaSum = color1.getAlpha() + color2.getAlpha();
		
		return Math.max(Math.pow(redSubtraction,2),   Math.pow(Math.pow(redSubtraction,2)-alphaSum,2)) +
			   Math.max(Math.pow(greenSubtraction,2), Math.pow(Math.pow(greenSubtraction,2)-alphaSum,2)) +
			   Math.max(Math.pow(blueSubtraction,2),  Math.pow(Math.pow(blueSubtraction,2)-alphaSum,2));
	}
	
	private static double calculateHVarianceHSL(Color color, Color color2) {
		HSL hsl1 = new HSL(color);
		HSL hsl2 = new HSL(color2);
		return hsl1.getH()-hsl2.getH();
	}
	
	/*
	 * 
	 */
	private static String myIdeaHSL(Color color, Color color2) {
		HSL hsl1 = new HSL(color);
		HSL hsl2 = new HSL(color2);
		if(hsl1.getH()== hsl1.getH() && hsl1.getS()== hsl1.getS() ){
			return "true";
		}
		else {
			return "false";
		}
	}

	private static double calculateHVarianceHCL(Color color, Color color2) {
		HCL hcl1 = new HCL(color);
		HCL hcl2 = new HCL(color2);
		return hcl1.getH()-hcl2.getH();
	}
	
	/*
	 * Totally based on: https://github.com/antimatter15/rgb-lab/blob/master/color.js
	 */
	// calculate the perceptual distance between colors in CIELAB
	// https://github.com/THEjoezack/ColorMine/blob/master/ColorMine/ColorSpaces/Comparisons/Cie94Comparison.cs
	private static double deltaE_LAB_94(Color a, Color b){
	  LAB labA = new LAB(a);
	  LAB labB = new LAB(b);
		
	  double deltaL = labA.getL() - labB.getL();
	  double deltaA = labA.getA() - labB.getA();
	  double deltaB = labA.getB() - labB.getB();
	  double c1 = Math.sqrt(labA.getA() * labA.getA() + labA.getB() * labA.getB());
	  double c2 = Math.sqrt(labB.getA() * labB.getA() + labB.getB() * labB.getB());
	  double deltaC = c1 - c2;
	  double deltaH = deltaA * deltaA + deltaB * deltaB - deltaC * deltaC;
	  deltaH = deltaH < 0 ? 0 : Math.sqrt(deltaH);
	  double sc = 1.0 + 0.045 * c1;
	  double sh = 1.0 + 0.015 * c1;
	  double deltaLKlsl = deltaL / (1.0);
	  double deltaCkcsc = deltaC / (sc);
	  double deltaHkhsh = deltaH / (sh);
	  double i = deltaLKlsl * deltaLKlsl + deltaCkcsc * deltaCkcsc + deltaHkhsh * deltaHkhsh;
	  return i < 0 ? 0 : Math.sqrt(i);
	}
	
	private static double calculateLVarianceLAB94(Color a, Color b) {
		LAB labA = new LAB(a);
		LAB labB = new LAB(b);
		return labA.getL() - labB.getL();
	}
	
	private static double deltaE_LAB_76(Color a, Color b){
	  LAB labA = new LAB(a);
	  LAB labB = new LAB(b);
		
	  double deltaL = labA.getL() - labB.getL();
	  double deltaA = labA.getA() - labB.getA();
	  double deltaB = labA.getB() - labB.getB();
	  return Math.sqrt(deltaL*deltaL + deltaA*deltaA + deltaB*deltaB);
	}
	
	private static double calculateHVarianceHSV(Color a, Color b) {
		HSV hsvA = new HSV(a);
		HSV hsvB = new HSV(b);
		return hsvA.getH() - hsvB.getH();
	}
}
