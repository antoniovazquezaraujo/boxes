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

import com.badlogic.gdx.graphics.Color;
import com.formatic.boxes.widgets.events.BoxEventListener;
import com.formatic.boxes.widgets.events.ButtonListener;
import com.formatic.boxes.widgets.events.TextKeyboardListener;

class KeyboardKey extends Button {
	char c;
	static Color generalPressedColor = new Color(0, 0, 0, 1);
	String name;
	public KeyboardKey(int x, int y, char c) {
		super(x, y, 1, 1, new Color(KeyboardColors.getColor(c)), generalPressedColor);
		name = KeyboardColors.getName(c);
		this.c=c;
		setButtonListener(new ButtonListener() {
			@Override
			public boolean onClick(Button b) {
				System.out.println(KeyboardKey.this.c);
				return false;
			}
		});
	}
}
class KeyboardColors{
	static Map<Character, String>names;
	static Map<String, Color>colors;
	static void load(){
		colors= new HashMap<String, Color>();
		names= new HashMap<Character, String>();
        add('A', "A", 0.0f, 0.9f,0.0f,1.0f);
        add('B', "B", 0.0f, 0.0f,0.0f,1.0f);
        add('C', "C", 0.7f, 0.0f,0.9f,1.0f);
        add('D', "D", 0.8f, 0.0f,0.0f,1.0f);
        add('E', "E", 0.0f, 0.0f,0.0f,1.0f);
        add('F', "F", 0.7f, 0.0f,0.0f,1.0f);
        add('G', "G", 0.0f, 0.0f,0.9f,1.0f);
        add('H', "H", 0.6f, 0.8f,0.0f,1.0f);
        add('I', "I", 0.0f, 0.0f,0.0f,1.0f);
        add('J', "J", 0.0f, 0.7f,0.7f,1.0f);
        add('K', "K", 0.5f, 0.0f,0.0f,1.0f);
        add('L', "L", 0.0f, 0.6f,0.5f,1.0f);
        add('M', "M", 0.0f, 0.0f,0.0f,1.0f);
        add('N', "N", 0.0f, 0.0f,0.5f,1.0f);
        add('O', "O", 0.3f, 0.4f,0.3f,1.0f);
        add('P', "P", 0.0f, 0.0f,0.0f,1.0f);
        add('Q', "Q", 0.0f, 0.0f,0.0f,1.0f);
        add('R', "R", 0.0f, 0.0f,0.0f,1.0f);
        add('S', "S", 0.0f, 0.0f,0.0f,1.0f);
        add('T', "T", 0.0f, 0.0f,0.0f,1.0f);
        add('U', "U", 0.0f, 0.0f,0.0f,1.0f);
        add('V', "V", 0.0f, 0.0f,0.0f,1.0f);
        add('W', "W", 0.0f, 0.0f,0.0f,1.0f);
        add('X', "X", 0.0f, 0.0f,0.0f,1.0f);
        add('Y', "Y", 0.0f, 0.0f,0.0f,1.0f);
        add('Z', "Z", 0.0f, 0.0f,0.0f,1.0f);
        add('!', "!", 0.0f, 0.0f,0.0f,1.0f);
        add('?', "?", 0.0f, 0.0f,0.5f,1.0f);
        add('.', ".", 0.0f, 0.7f,0.0f,1.0f);
        add(',', ",", 0.6f, 0.0f,0.0f,1.0f);
        add(' ', " ", 0.0f, 0.5f,0.4f,1.0f);
        add('<', "<", 0.0f, 0.0f,0.5f,1.0f);

	}
	static void add(char c, String name, float r, float g, float b, float a){
		names.put(c, name);
		colors.put(name,new Color(r,g,b,a)); 		
	}
	static Color getColor(String name){
		return colors.get(name);
	}
	static Color getColor(char c){
		return colors.get(getName(c));
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
		textKeyboardListener.charSelected(k.c);
		return false;
	}
}
