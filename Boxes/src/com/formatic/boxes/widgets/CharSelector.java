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

import com.formatic.boxes.Color;
import com.formatic.boxes.Font;
import com.formatic.boxes.Point;
import com.formatic.boxes.ScreenModel;
import com.formatic.boxes.widgets.events.BlockSelectorListener;
import com.formatic.boxes.widgets.events.BoxEventAdapter;
import com.formatic.boxes.widgets.events.CharSelectorListener;

public class CharSelector extends BoxContainer implements BlockSelectorListener {
	final int GROUPS_BY_LINE = 3;
	final int NUM_LINES = 3;
	final int CHARS_BY_GROUP = 4;
	int selectedLine;
	int selectedGroup;
	char selectedChar;
	char[][][] chars;
	Font font;
	Color fontColor;
	CharSelectorListener charSelectorListener;
	boolean visible;
	public CharSelector() {
		super(6, 6);
		visible=false;
		font = new Font(Font.TYPE_33);
		setColor(new Color(1,1,1,1));
		fontColor = new Color(0,1,0,1);
		chars = new char[NUM_LINES][GROUPS_BY_LINE][CHARS_BY_GROUP];
		populateKeys();
		addCharBoxes();
		updateChars();

		setBoxEventListener(new BoxEventAdapter() {
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
				if (charSelectorListener != null) {
					CharBox b = (CharBox) boxAtPos(new Point(x, y));
					charSelectorListener.charSelected(b.getChar());
					visible=false;
				}
				return true;
			}

			@Override
			public boolean onDrag(int x, int y, int newX, int newY) {
				return false;
			}
		});
	}
	public void blockSelected(int line, int group){
		this.selectedLine=line;
		this.selectedGroup=group;
		visible=true;
		updateChars();
	}
	public void selectLine(int line) {
		this.selectedLine = line;
		updateChars();
	}

	public void selectedGroup(int group) {
		this.selectedGroup = group;
		updateChars();
	}

	public void update(ScreenModel model){
		if(visible){
			super.update(model);
		}
	}
	private void addCharBoxes() {
		float brightness= 1.0f;
		for (int x = 0; x < 2; x++) {
			for (int y = 0; y < 2; y++) {
				CharBox b = new CharBox(font, ' ', new Color(fontColor));
//				b.fontColor.a=0.5f;
				b.setColor(new Color(1,1,1,1));
				b.fontColor.setBrightness(brightness);
				brightness-=0.2f;
				b.setPosition(y*3,x*3);
				add(b);
			}
		}
	}

	private void updateChars() {
		int numCharBox = 0;
		for (int x = 0; x < 2; x++) {
			for (int y = 0; y < 2; y++) {
				CharBox b = (CharBox) get(numCharBox);
				b.setChar(chars[selectedLine][selectedGroup][numCharBox]);
				numCharBox++;
			}
		}
	}

	private void populateKeys() {
		chars[0][0][0] = 'Q';
		chars[0][0][1] = 'W';
		chars[0][0][2] = 'E';
		chars[0][0][3] = 'R';
		chars[0][1][0] = 'T';
		chars[0][1][1] = 'Y';
		chars[0][1][2] = 'U';
		chars[0][1][3] = 'I';
		chars[0][2][0] = 'O';
		chars[0][2][1] = 'P';
		chars[0][2][2] = ' ';
		chars[0][2][3] = ' ';
		chars[1][0][0] = 'A';
		chars[1][0][1] = 'S';
		chars[1][0][2] = 'D';
		chars[1][0][3] = 'F';
		chars[1][1][0] = 'G';
		chars[1][1][1] = 'H';
		chars[1][1][2] = 'J';
		chars[1][1][3] = 'K';
		chars[1][2][0] = 'L';
		chars[1][2][1] = 'N';
		chars[1][2][2] = ' ';
		chars[1][2][3] = ' ';
		chars[2][0][0] = 'Z';
		chars[2][0][1] = 'X';
		chars[2][0][2] = 'C';
		chars[2][0][3] = 'V';
		chars[2][1][0] = 'B';
		chars[2][1][1] = 'N';
		chars[2][1][2] = 'M';
		chars[2][1][3] = ',';
		chars[2][2][0] = '.';
		chars[2][2][1] = ' ';
		chars[2][2][2] = ' ';
		chars[2][2][3] = ' ';

	}

	public void setCharSelectorListener(
			CharSelectorListener charSelectorListener) {
		this.charSelectorListener = charSelectorListener;
	}
}
