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

import com.badlogic.gdx.graphics.Color;
import com.formatic.boxes.Dimension;
import com.formatic.boxes.Point;
import com.formatic.boxes.widgets.events.BoxEventAdapter;
import com.formatic.boxes.widgets.events.SwitchListener;

public class Switch extends Button {
	SwitchListener switchListener;
	boolean pressed;

	public Switch() {
		this(0, 0, 1, 1);
	}

	public Switch(Point location, Dimension size) {
		this(location.x, location.y, size.width, size.height);
	}

	public Switch(int x, int y, int w, int h) {
		super(x, y, w, h);
		this.normalColor = new Color(0.5f, 0.5f, 0.5f, 1);
		this.pressedColor = new Color(0.8f, 0.8f, 0.8f, 1);
		setColor(normalColor);
		setPressed(false);
		setBoxEventListener(new BoxEventAdapter() {

			@Override
			public boolean onTouch(int x, int y) {
				setPressed(!isPressed());
				if (pressed) {
					setColor(pressedColor);
				} else {
					setColor(normalColor);
				}
				return true;
			}

			@Override
			public boolean onRelease(int x, int y) {
				if (pressed) {
					setColor(pressedColor);
				} else {
					setColor(normalColor);
				}
				if (switchListener != null) {
					return switchListener.onChange(pressed);
				}
				return true;
			}

			@Override
			public boolean onDrag(int x, int y, int newX, int newY) {
				setColor(normalColor);
				return true;
			}
		});
	}

	public void setPressed(boolean pressed) {
		this.pressed = pressed;
		if (pressed) {
			setColor(pressedColor);
		} else {
			setColor(normalColor);
		}
	}

	public boolean isPressed() {
		return pressed;
	}

	public void setSwitchListener(SwitchListener switchListener) {
		this.switchListener = switchListener;
	}
}
