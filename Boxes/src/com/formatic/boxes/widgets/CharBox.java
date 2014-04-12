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
import com.formatic.boxes.Dimension;
import com.formatic.boxes.Font;
import com.formatic.boxes.Point;
import com.formatic.boxes.ScreenModel;

public class CharBox extends Box {
	char c;
	Color fontColor;
	Font font;
	int[][] charMap;

	public CharBox(Font font, char c, Color fontColor) {
		setFont(font);
		setChar(c);
		this.fontColor = fontColor;
	}

	public CharBox(Font font, char c) {
		this(font, c, new Color(0, 0, 0, 1));
	}

	public CharBox(Font font, int n) {
		this(font, (char) ('0' + n));
	}

	public void setFont(Font font) {
		this.font = font;
		setSize(font.getWidth(), font.getHeight());
	}

	public void setChar(char c) {
		this.c = c;
		this.charMap = font.getCharMap(c);
	}

	public void setNumber(int n) {
		setChar((char) ('0' + n));
	}

	public char getChar() {
		return c;
	}

	public void update(ScreenModel screenModel) {
		super.update(screenModel);
		Point p = new Point(screenModel.getPosition());
		Dimension d = new Dimension(screenModel.getSize());

		for (int x =0; x < getSize().width; x++) {
			for (int y = 0; y < getSize().height; y++) {
				if (charMap[y][x] == 1) {
					screenModel.applyClip(x, y, 1, 1);
					screenModel.setColor(fontColor);
					screenModel.fill(0, 0, 1, 1);
					screenModel.setPointAndSize(p, d);
				}
			}
		}
	}
}
