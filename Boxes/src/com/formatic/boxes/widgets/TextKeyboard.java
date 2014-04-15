/*
	                          BOXES
	              A minimalist color box framework
	         Copyright (C) 2014 Antonio Vazquez Araujo
	          (antoniovazquezaraujo[at]gmail[dot]com)
	          
	This file is part of Boxes.

    Boxes is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    Boxes is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with Boxes.  If not, see <http://www.gnu.org/licenses/>.
*/
package com.formatic.boxes.widgets;

import java.util.HashMap;
import java.util.Map;

import com.formatic.boxes.Color;
import com.formatic.boxes.widgets.events.BoxEventListener;
import com.formatic.boxes.widgets.events.ButtonListener;
import com.formatic.boxes.widgets.events.TextKeyboardListener;

class KeyboardColors{
	static float alpha=1.0f;
	static Map<Character, String>names;
	static Map<String, Color>colorNames;
	static void load(){
		colorNames= new HashMap<String, Color>();
		addColor("AliceBlue",	new Color(0.94f, 0.97f, 1.00f, alpha));;
		addColor("AntiqueWhite",	new Color(0.98f, 0.92f, 0.84f, alpha));;
		addColor("Aqua",	new Color(0.00f, 0.02f, 1.00f, alpha));;
		addColor("Aquamarine",	new Color(0.50f, 1.00f, 0.83f, alpha));;
		addColor("Azure",	new Color(0.94f, 1.00f, 1.00f, alpha));;
		addColor("Beige",	new Color(0.96f, 0.96f, 0.86f, alpha));;
		addColor("Bisque",	new Color(1.00f, 0.89f, 0.77f, alpha));;
		addColor("Black",	new Color(0.00f, 0.00f, 0.00f, alpha));;
		addColor("BlanchedAlmond",	new Color(1.00f, 0.92f, 0.80f, alpha));;
		addColor("Blue",	new Color(0.00f, 1.00f, 1.00f, alpha));;
		addColor("BlueViolet",	new Color(0.54f, 0.17f, 0.89f, alpha));;
		addColor("Brown",	new Color(0.65f, 0.16f, 0.00f, alpha));;
		addColor("BurlyWood",	new Color(0.87f, 0.72f, 0.53f, alpha));;
		addColor("CadetBlue",	new Color(0.37f, 0.23f, 0.63f, alpha));;
		addColor("Chartreuse",	new Color(0.50f, 1.00f, 0.02f, alpha));;
		addColor("Chocolate",	new Color(0.82f, 0.41f, 0.00f, alpha));;
		addColor("Coral",	new Color(1.00f, 0.50f, 0.00f, alpha));;
		addColor("CorflowerBlue",	new Color(0.39f, 0.58f, 0.93f, alpha));;
		addColor("Cornsilk",	new Color(1.00f, 0.97f, 0.86f, alpha));;
		addColor("Crimson",	new Color(0.86f, 0.08f, 0.00f, alpha));;
		addColor("Cyan",	new Color(0.00f, 0.02f, 1.00f, alpha));;
		addColor("DarkBlue",	new Color(0.00f, 0.55f, 0.55f, alpha));;
		addColor("DarkCyan",	new Color(0.00f, 0.04f, 0.55f, alpha));;
		addColor("DarkGoldenRod",	new Color(0.72f, 0.53f, 0.00f, alpha));;
		addColor("DarkGray",	new Color(0.66f, 0.66f, 0.66f, alpha));;
		addColor("DarkGreen",	new Color(0.00f, 0.00f, 0.00f, alpha));;
		addColor("DarkKhaki",	new Color(0.74f, 0.72f, 0.42f, alpha));;
		addColor("DarkMagenta",	new Color(0.55f, 0.00f, 0.55f, alpha));;
		addColor("DarkOliveGreen",	new Color(0.33f, 0.03f, 0.00f, alpha));;
		addColor("DarkOrange",	new Color(1.00f, 0.55f, 0.00f, alpha));;
		addColor("DarkOrchid",	new Color(0.60f, 0.20f, 0.80f, alpha));;
		addColor("DarkRed",	new Color(0.55f, 0.00f, 0.00f, alpha));;
		addColor("DarkSalmon",	new Color(0.91f, 0.59f, 0.48f, alpha));;
		addColor("DarkSeaGreen",	new Color(0.56f, 0.74f, 0.56f, alpha));;
		addColor("DarkSlateBlue",	new Color(0.28f, 0.00f, 0.55f, alpha));;
		addColor("DarkSlateGray",	new Color(0.18f, 0.04f, 0.00f, alpha));;
		addColor("DarkTurquoise",	new Color(0.00f, 0.02f, 0.82f, alpha));;
		addColor("DarkViolet",	new Color(0.58f, 0.00f, 0.83f, alpha));;
		addColor("DeepPink",	new Color(1.00f, 0.08f, 0.58f, alpha));;
		addColor("DeepSkyBlue",	new Color(0.00f, 0.00f, 1.00f, alpha));;
		addColor("DimGray",	new Color(0.41f, 0.41f, 0.41f, alpha));;
		addColor("DodgerBlue",	new Color(0.12f, 0.17f, 1.00f, alpha));;
		addColor("FireBrick",	new Color(0.70f, 0.13f, 0.00f, alpha));;
		addColor("FloralWhite",	new Color(1.00f, 0.98f, 0.94f, alpha));;
		addColor("ForestGreen",	new Color(0.13f, 0.15f, 0.00f, alpha));;
		addColor("Fuchsia",	new Color(1.00f, 0.00f, 1.00f, alpha));;
		addColor("Gainsboro",	new Color(0.86f, 0.86f, 0.86f, alpha));;
		addColor("GhostWhite",	new Color(0.97f, 0.97f, 1.00f, alpha));;
		addColor("Gold",	new Color(1.00f, 0.84f, 0.02f, alpha));;
		addColor("GoldenRod",	new Color(0.85f, 0.65f, 0.00f, alpha));;
		addColor("Gray",	new Color(0.50f, 0.50f, 0.50f, alpha));;
		addColor("Green",	new Color(0.00f, 1.00f, 0.40f, alpha));;
		addColor("GreenYellow",	new Color(0.68f, 1.00f, 0.00f, alpha));;
		addColor("HoneyDew",	new Color(0.94f, 1.00f, 0.94f, alpha));;
		addColor("HotPink",	new Color(1.00f, 0.41f, 0.71f, alpha));;
		addColor("IndianRed",	new Color(0.80f, 0.36f, 0.00f, alpha));;
		addColor("Indigo",	new Color(0.29f, 0.00f, 0.51f, alpha));;
		addColor("Ivory",	new Color(1.00f, 1.00f, 0.94f, alpha));;
		addColor("Khaki",	new Color(0.94f, 0.90f, 0.55f, alpha));;
		addColor("Lavender",	new Color(0.90f, 0.90f, 0.98f, alpha));;
		addColor("LavenderBlush",	new Color(1.00f, 0.94f, 0.96f, alpha));;
		addColor("LawnGreen",	new Color(0.49f, 0.99f, 0.01f, alpha));;
		addColor("LemonChffon",	new Color(1.00f, 0.98f, 0.80f, alpha));;
		addColor("LightBlue",	new Color(0.68f, 0.85f, 0.90f, alpha));;
		addColor("LightCoral",	new Color(0.94f, 0.50f, 0.50f, alpha));;
		addColor("LightCyan",	new Color(0.88f, 1.00f, 1.00f, alpha));;
		addColor("LightGoldenRodYellow",	new Color(0.90f, 0.98f, 0.82f, alpha));;
		addColor("LightGray",	new Color(0.83f, 0.83f, 0.83f, alpha));;
		addColor("LightGreen",	new Color(0.56f, 0.93f, 0.56f, alpha));;
		addColor("LightPink",	new Color(1.00f, 0.71f, 0.76f, alpha));;
		addColor("LightSalmon",	new Color(1.00f, 0.63f, 0.48f, alpha));;
		addColor("LightSeaGreen",	new Color(0.13f, 0.31f, 0.67f, alpha));;
		addColor("LightSkyBlue",	new Color(0.53f, 0.81f, 0.98f, alpha));;
		addColor("LightSlateGray",	new Color(0.47f, 0.53f, 0.60f, alpha));;
		addColor("LightSteelBlue",	new Color(0.69f, 0.77f, 0.87f, alpha));;
		addColor("LightYellow",	new Color(1.00f, 1.00f, 0.88f, alpha));;
		addColor("Lime",	new Color(0.00f, 1.00f, 0.00f, alpha));;
		addColor("LimeGreen",	new Color(0.20f, 0.02f, 0.00f, alpha));;
		addColor("Linen",	new Color(0.98f, 0.94f, 0.90f, alpha));;
		addColor("Magenta",	new Color(1.00f, 0.00f, 1.00f, alpha));;
		addColor("Maroon",	new Color(0.50f, 0.00f, 0.00f, alpha));;
		addColor("MediumAquaMarine",	new Color(0.40f, 0.80f, 0.67f, alpha));;
		addColor("MediumBlue",	new Color(0.00f, 0.80f, 0.80f, alpha));;
		addColor("MediumOrchid",	new Color(0.73f, 0.33f, 0.83f, alpha));;
		addColor("MediumPurple",	new Color(0.58f, 0.44f, 0.86f, alpha));;
		addColor("MediumSeaGreen",	new Color(0.24f, 0.31f, 0.44f, alpha));;
		addColor("MediumSlateBlue",	new Color(0.48f, 0.41f, 0.93f, alpha));;
		addColor("MediumSpringGreen",	new Color(0.00f, 0.00f, 0.60f, alpha));;
		addColor("MediumTurquoise",	new Color(0.28f, 0.04f, 0.80f, alpha));;
		addColor("MediumVioletRed",	new Color(0.78f, 0.08f, 0.52f, alpha));;
		addColor("MidnightBlue",	new Color(0.10f, 0.02f, 0.44f, alpha));;
		addColor("MintCream",	new Color(0.96f, 1.00f, 0.98f, alpha));;
		addColor("MistyRose",	new Color(1.00f, 0.89f, 0.88f, alpha));;
		addColor("Moccasin",	new Color(1.00f, 0.89f, 0.71f, alpha));;
		addColor("NavajoWhite",	new Color(1.00f, 0.87f, 0.68f, alpha));;
		addColor("Navy",	new Color(0.00f, 0.50f, 0.50f, alpha));;
		addColor("OldLace",	new Color(0.99f, 0.96f, 0.90f, alpha));;
		addColor("Olive",	new Color(0.50f, 0.50f, 0.03f, alpha));;
		addColor("OliveDrab",	new Color(0.42f, 0.56f, 0.00f, alpha));;
		addColor("Orange",	new Color(1.00f, 0.65f, 0.02f, alpha));;
		addColor("OrangeRed",	new Color(1.00f, 0.27f, 0.04f, alpha));;
		addColor("Orchid",	new Color(0.85f, 0.44f, 0.84f, alpha));;
		addColor("PaleGoldenRod",	new Color(0.93f, 0.91f, 0.67f, alpha));;
		addColor("PaleGreen",	new Color(0.60f, 0.98f, 0.60f, alpha));;
		addColor("PaleTurquoise",	new Color(0.69f, 0.93f, 0.93f, alpha));;
		addColor("PaleVioletRed",	new Color(0.86f, 0.44f, 0.58f, alpha));;
		addColor("PapayaWhip",	new Color(1.00f, 0.94f, 0.84f, alpha));;
		addColor("PeachPff",	new Color(1.00f, 0.85f, 0.73f, alpha));;
		addColor("Peru",	new Color(0.80f, 0.52f, 0.00f, alpha));;
		addColor("Pink",	new Color(1.00f, 0.75f, 0.80f, alpha));;
		addColor("Plum",	new Color(0.87f, 0.63f, 0.87f, alpha));;
		addColor("PowderBlue",	new Color(0.69f, 0.88f, 0.90f, alpha));;
		addColor("Purple",	new Color(0.50f, 0.00f, 0.50f, alpha));;
		addColor("Red",	new Color(1.00f, 0.00f, 0.00f, alpha));;
		addColor("RosyBrown",	new Color(0.74f, 0.56f, 0.56f, alpha));;
		addColor("RoyalBlue",	new Color(0.25f, 0.02f, 0.88f, alpha));;
		addColor("SaddleBrown",	new Color(0.55f, 0.27f, 0.00f, alpha));;
		addColor("Salmon",	new Color(0.98f, 0.50f, 0.45f, alpha));;
		addColor("SandyBrown",	new Color(0.96f, 0.64f, 0.00f, alpha));;
		addColor("SeaGreen",	new Color(0.18f, 0.15f, 0.00f, alpha));;
		addColor("SeaShell",	new Color(1.00f, 0.96f, 0.93f, alpha));;
		addColor("Sienna",	new Color(0.63f, 0.32f, 0.00f, alpha));;
		addColor("Silver",	new Color(0.75f, 0.75f, 0.75f, alpha));;
		addColor("SkyBlue",	new Color(0.53f, 0.81f, 0.92f, alpha));;
		addColor("SlateBlue",	new Color(0.42f, 0.35f, 0.80f, alpha));;
		addColor("SlateGray",	new Color(0.44f, 0.50f, 0.56f, alpha));;
		addColor("Snow",	new Color(1.00f, 0.98f, 0.98f, alpha));;
		addColor("SpringGreen",	new Color(0.00f, 0.02f, 0.50f, alpha));;
		addColor("SteelBlue",	new Color(0.27f, 0.12f, 0.71f, alpha));;
		addColor("Tan",	new Color(0.82f, 0.71f, 0.55f, alpha));;
		addColor("Teal",	new Color(0.00f, 0.03f, 0.50f, alpha));;
		addColor("Thistle",	new Color(0.85f, 0.75f, 0.85f, alpha));;
		addColor("Tomato",	new Color(1.00f, 0.39f, 0.00f, alpha));;
		addColor("Turquoise",	new Color(0.25f, 0.09f, 0.82f, alpha));;
		addColor("Violet",	new Color(0.93f, 0.51f, 0.93f, alpha));;
		addColor("Wheat",	new Color(0.96f, 0.87f, 0.70f, alpha));;
		addColor("White",	new Color(1.00f, 1.00f, 1.00f, alpha));;
		addColor("WhiteSmoke",	new Color(0.96f, 0.96f, 0.96f, alpha));;
		addColor("Yellow",	new Color(1.00f, 1.00f, 0.02f, alpha));;
		addColor("YellowGreen",	new Color(0.60f, 0.80f, 0.00f, alpha));
		
		names= new HashMap<Character, String>();
        addName('A', "Aqua");
        addName('B', "Blue");
        addName('C', "Chocolate");
        addName('D', "DeepPink");
        addName('E', "Black");
        addName('F', "Fuchsia");
        addName('G', "Green");
        addName('H', "HotPink");
        addName('I', "Indigo");
        addName('J', "Black");
        addName('K', "Khaki");
        addName('L', "Lime");
        addName('M', "Magenta");
        addName('N', "Navy");
        addName('O', "Olive");
        addName('P', "Pink");
        addName('Q', "Black");
        addName('R', "Red");
        addName('S', "Salmon");
        addName('T', "Turquoise");
        addName('U', "Black");
        addName('V', "Violet");
        addName('W', "White");
        addName('X', "Black");
        addName('Y', "Yellow");
        addName('Z', "Black");
        addName('!', "Black");
        addName('?', "Black");
        addName('.', "Black");
        addName(',', "Black");
        addName(' ', "Black");
        addName('<', "Black");

	}
	static void addName(char c, String name){
		names.put(c, name.toUpperCase());
	}
	static void addColor(String name, Color color){
		colorNames.put(name.toUpperCase(), color);
	}
	static void add(char c, String name, float r, float g, float b, float a){
		names.put(c, name.toUpperCase());
		colorNames.put(name.toUpperCase(),new Color(r,g,b,a)); 		
	}
	static Color getColor(String name){
		return colorNames.get(name.toUpperCase());
	}
	static Color getColor(char c){
		return colorNames.get(getName(c));
	}
	static String getName(char c){
		return names.get(c);
	}
}

public class TextKeyboard extends BoxContainer implements ButtonListener {
	TextKeyboardListener textKeyboardListener;
	int selected;
	int firstX, firstY;
	boolean allClean;
	KeyboardKey[][] keys;

	public TextKeyboard() {
		super(8, 4);
		KeyboardColors.load();
		keys = new KeyboardKey[8][4];
		populateKeys();
		addKeys();
		setBoxEventListener(new BoxEventListener() {
			@Override
			public boolean onZoomOut(int x, int y) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean onTouchCancel(int x, int y) {
				return false;
			}

			@Override
			public boolean onTouch(int x, int y) {
				return false;
			}

			@Override
			public boolean onRelease(int x, int y) {
				return false;
			}

			@Override
			public boolean onDrag(int x, int y, int newX, int newY) {
				return false;
			}

		});
	}

	private void addKeys() {
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 4; y++) {
				add(keys[x][y]);
				keys[x][y].setButtonListener(this);
			}
		}
	}

	private void populateKeys() {
		keys[0][0] = new KeyboardKey(0, 0, 'A');
		keys[1][0] = new KeyboardKey(1, 0, 'B');
		keys[2][0] = new KeyboardKey(2, 0, 'C');
		keys[3][0] = new KeyboardKey(3, 0, 'D');
		keys[4][0] = new KeyboardKey(4, 0, 'E');
		keys[5][0] = new KeyboardKey(5, 0, 'F');
		keys[6][0] = new KeyboardKey(6, 0, 'G');
		keys[7][0] = new KeyboardKey(7, 0, 'H');
		keys[0][1] = new KeyboardKey(0, 1, 'I');
		keys[1][1] = new KeyboardKey(1, 1, 'J');
		keys[2][1] = new KeyboardKey(2, 1, 'K');
		keys[3][1] = new KeyboardKey(3, 1, 'L');
		keys[4][1] = new KeyboardKey(4, 1, 'M');
		keys[5][1] = new KeyboardKey(5, 1, 'N');
		keys[6][1] = new KeyboardKey(6, 1, '?');
		keys[7][1] = new KeyboardKey(7, 1, '!');
		keys[0][2] = new KeyboardKey(0, 2, 'O');
		keys[1][2] = new KeyboardKey(1, 2, 'P');
		keys[2][2] = new KeyboardKey(2, 2, 'Q');
		keys[3][2] = new KeyboardKey(3, 2, 'R');
		keys[4][2] = new KeyboardKey(4, 2, 'S');
		keys[5][2] = new KeyboardKey(5, 2, 'T');
		keys[6][2] = new KeyboardKey(6, 2, '.');
		keys[7][2] = new KeyboardKey(7, 2, ',');
		keys[0][3] = new KeyboardKey(0, 3, 'U');
		keys[1][3] = new KeyboardKey(1, 3, 'V');
		keys[2][3] = new KeyboardKey(2, 3, 'W');
		keys[3][3] = new KeyboardKey(3, 3, 'X');
		keys[4][3] = new KeyboardKey(4, 3, 'Y');
		keys[5][3] = new KeyboardKey(5, 3, 'Z');
		keys[6][3] = new KeyboardKey(6, 3, '<');
		keys[7][3] = new KeyboardKey(7, 3, ' ');


	}

	public void setTextKeyboardListener(
			TextKeyboardListener textKeyboardListener) {
		this.textKeyboardListener = textKeyboardListener;
	}

	@Override
	public boolean onClick(Button b) {
		KeyboardKey k = (KeyboardKey)b;
		//textKeyboardListener.charSelected(k.c);
		textKeyboardListener.charNameShowed(k.c, KeyboardColors.getName(k.c), KeyboardColors.getColor(k.c));
		return false;
	}
}
