package TesteCores.TesteCores;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Colors {

	List<Color> red;
	List<Color> green;
	List<Color> blue;
	
	public Colors() {
		red = new ArrayList<Color>();
		green = new ArrayList<Color>();
		blue = new ArrayList<Color>();
		buildAllShades();
		
	}
	
	public List<String> getNamesOfShades(){
		return Arrays.asList("red", "green", "blue");
	}
	public List<List<Color>> getAllShades(){
		return Arrays.asList(red, green, blue);
	}
	
	/*Shades of red = http://www.0to255.com/ff0000 */
	private void buildAllShades(){
		
		for(Integer r = 1; r<=15; r++){
			red.add(new Color(Integer.valueOf(Integer.toHexString(r).concat(Integer.toHexString(r)) , 16 ), 0, 0));
			
			green.add(new Color(0, Integer.valueOf(Integer.toHexString(r).concat(Integer.toHexString(r)) , 16 ), 0));
			blue.add(new Color(0, 0, Integer.valueOf(Integer.toHexString(r).concat(Integer.toHexString(r)) , 16 )));
		}
		for(Integer r = 0; r<15; r++){
			red.add(new Color(Integer.valueOf("FF" , 16 ), 
							(Integer.valueOf(Integer.toHexString(r).concat(Integer.toHexString(r)) , 16 )), 
							(Integer.valueOf(Integer.toHexString(r).concat(Integer.toHexString(r)) , 16 ))));
			
			green.add(new Color((Integer.valueOf(Integer.toHexString(r).concat(Integer.toHexString(r)) , 16 )),
							Integer.valueOf("FF" , 16 ), 
							(Integer.valueOf(Integer.toHexString(r).concat(Integer.toHexString(r)) , 16 ))));
			
			blue.add(new Color((Integer.valueOf(Integer.toHexString(r).concat(Integer.toHexString(r)) , 16 )),
					(Integer.valueOf(Integer.toHexString(r).concat(Integer.toHexString(r)) , 16 )),
					Integer.valueOf("FF" , 16 )));
		}
	}
}