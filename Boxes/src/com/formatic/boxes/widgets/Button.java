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
import com.formatic.boxes.widgets.events.ButtonListener;

public class Button extends Box {
	ButtonListener buttonListener;
	Color normalColor, pressedColor;

	public Button() {
		this(1, 1);
	}
	public Button(int w, int h) {
		this(0, 0, w, h);
	}
	public Button(int x, int y, int w, int h) {
		this(x,y, w, h, new Color(0.5f, 0.5f,0.5f, 1.0f), new Color(0.2f,
				0.2f, 0.2f, 1.0f));
	}
	public Button(int w, int h, Color normalColor, Color pressedColor) {
		this(0,0,w,h,normalColor, pressedColor);
	}
	public Button(int x, int y, int w, int h, Color normalColor, Color pressedColor) {
		super(x, y, w, h, normalColor);
		this.normalColor = normalColor;
		this.pressedColor = pressedColor;
	}

	public void setNormalColor(Color normalColor) {
		this.normalColor = normalColor;
	}

	public void setPressedColor(Color pressedColor) {
		this.pressedColor = pressedColor;
	}

	public void setButtonListener(ButtonListener buttonListener) {
		this.buttonListener = buttonListener;
	}

	@Override
	public boolean onTouch(int x, int y) {
		setColor(pressedColor);
		return super.onTouch(x, y);
	}

	@Override
	public boolean onRelease(int x, int y) {
		setColor(normalColor);
		if (buttonListener != null) {
			if (buttonListener.onClick(this)) {
				return true;
			}
		}
		return super.onRelease(x, y);
	}

	@Override
	public boolean onDrag(int x, int y, int newX, int newY) {
		setColor(normalColor);
		return super.onDrag(x, y, newX, newY);
	}

	@Override
	public boolean onTouchCancel(int x, int y) {
		setColor(normalColor);
		return super.onTouchCancel(x, y);
	}

}
