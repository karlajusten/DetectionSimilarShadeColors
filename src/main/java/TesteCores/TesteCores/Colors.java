package TesteCores.TesteCores;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Colors {

	List<Color> red;
	List<Color> green;
	List<Color> blue;
	List<Color> pink;
	List<Color> cyan;
	List<Color> purple;
	List<Color> yellow;
	
	public Colors() {
		red = new ArrayList<Color>();
		green = new ArrayList<Color>();
		blue = new ArrayList<Color>();
		pink = new ArrayList<Color>();
		cyan = new ArrayList<Color>();
		purple = new ArrayList<Color>();
		yellow = new ArrayList<Color>();
		buildAllShades();
	}
	
	public List<String> getNamesOfShades(){
		return Arrays.asList("red", "green", "blue", "pink", "cyan", "purple", "yellow");
	}
	public List<List<Color>> getAllShades(){
		return Arrays.asList(red, green, blue, pink, cyan, purple, yellow);
	}
	
	/*Shades of red = http://www.0to255.com/ff0000 */
	private void buildAllShades(){
		
		/*http://www.0to255.com/*/
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
		
		pink = Arrays.asList(
				new Color(Integer.valueOf("ff",16),Integer.valueOf("00",16),Integer.valueOf("a4",16)),
				new Color(Integer.valueOf("f5",16),Integer.valueOf("00",16),Integer.valueOf("9e",16)),
				new Color(Integer.valueOf("eb",16),Integer.valueOf("00",16),Integer.valueOf("97",16)),
				new Color(Integer.valueOf("e0",16),Integer.valueOf("00",16),Integer.valueOf("90",16)),
				new Color(Integer.valueOf("d2",16),Integer.valueOf("00",16),Integer.valueOf("87",16)),
				new Color(Integer.valueOf("c4",16),Integer.valueOf("00",16),Integer.valueOf("7e",16)),
				new Color(Integer.valueOf("b8",16),Integer.valueOf("00",16),Integer.valueOf("76",16)),
				new Color(Integer.valueOf("ad",16),Integer.valueOf("00",16),Integer.valueOf("6f",16)),
				new Color(Integer.valueOf("95",16),Integer.valueOf("00",16),Integer.valueOf("60",16)),
				new Color(Integer.valueOf("76",16),Integer.valueOf("00",16),Integer.valueOf("4c",16))
				);
		
		cyan = Arrays.asList(
				new Color(Integer.valueOf("00",16),Integer.valueOf("ff",16),Integer.valueOf("ff",16)),
				new Color(Integer.valueOf("00",16),Integer.valueOf("ed",16),Integer.valueOf("ed",16)),
				new Color(Integer.valueOf("00",16),Integer.valueOf("dc",16),Integer.valueOf("dc",16)),
				new Color(Integer.valueOf("00",16),Integer.valueOf("c4",16),Integer.valueOf("c4",16)),
				new Color(Integer.valueOf("00",16),Integer.valueOf("af",16),Integer.valueOf("af",16)),
				new Color(Integer.valueOf("00",16),Integer.valueOf("91",16),Integer.valueOf("91",16)),
				new Color(Integer.valueOf("00",16),Integer.valueOf("7a",16),Integer.valueOf("7a",16)),
				new Color(Integer.valueOf("00",16),Integer.valueOf("66",16),Integer.valueOf("66",16)),
				new Color(Integer.valueOf("00",16),Integer.valueOf("5e",16),Integer.valueOf("5e",16)),
				new Color(Integer.valueOf("00",16),Integer.valueOf("4e",16),Integer.valueOf("4e",16)),
				new Color(Integer.valueOf("00",16),Integer.valueOf("3d",16),Integer.valueOf("3d",16))
				);
		
		purple = Arrays.asList(
				new Color(Integer.valueOf("b6",16),Integer.valueOf("07",16),Integer.valueOf("ff",16)),
				new Color(Integer.valueOf("af",16),Integer.valueOf("07",16),Integer.valueOf("f5",16)),
				new Color(Integer.valueOf("a5",16),Integer.valueOf("07",16),Integer.valueOf("e7",16)),
				new Color(Integer.valueOf("9a",16),Integer.valueOf("06",16),Integer.valueOf("d8",16)),
				new Color(Integer.valueOf("95",16),Integer.valueOf("06",16),Integer.valueOf("d0",16)),
				new Color(Integer.valueOf("8f",16),Integer.valueOf("06",16),Integer.valueOf("c8",16)),
				new Color(Integer.valueOf("88",16),Integer.valueOf("05",16),Integer.valueOf("be",16)),
				new Color(Integer.valueOf("81",16),Integer.valueOf("05",16),Integer.valueOf("b4",16)),
				new Color(Integer.valueOf("76",16),Integer.valueOf("04",16),Integer.valueOf("a5",16)),
				new Color(Integer.valueOf("6f",16),Integer.valueOf("04",16),Integer.valueOf("9b",16)),
				new Color(Integer.valueOf("65",16),Integer.valueOf("03",16),Integer.valueOf("8d",16))
				);
		
		yellow = Arrays.asList(
				new Color(Integer.valueOf("ff",16),Integer.valueOf("f7",16),Integer.valueOf("03",16)),
				new Color(Integer.valueOf("ed",16),Integer.valueOf("e5",16),Integer.valueOf("03",16)),
				new Color(Integer.valueOf("e6",16),Integer.valueOf("df",16),Integer.valueOf("03",16)),
				new Color(Integer.valueOf("d6",16),Integer.valueOf("cf",16),Integer.valueOf("03",16)),
				new Color(Integer.valueOf("cc",16),Integer.valueOf("c5",16),Integer.valueOf("03",16)),
				new Color(Integer.valueOf("c0",16),Integer.valueOf("ba",16),Integer.valueOf("02",16)),
				new Color(Integer.valueOf("b1",16),Integer.valueOf("ab",16),Integer.valueOf("02",16)),
				new Color(Integer.valueOf("a5",16),Integer.valueOf("a0",16),Integer.valueOf("02",16)),
				new Color(Integer.valueOf("97",16),Integer.valueOf("92",16),Integer.valueOf("02",16)),
				new Color(Integer.valueOf("8f",16),Integer.valueOf("8a",16),Integer.valueOf("02",16))
				);
		
		//new Color(Integer.valueOf("",16),Integer.valueOf("",16),Integer.valueOf("",16)),
	}
}