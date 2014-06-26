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
package com.formatic.boxes.widgets.events;

public class BoxEventAdapter implements BoxEventListener {

	public BoxEventAdapter() {
	}

	@Override
	public boolean onTouch(int x, int y) {
		return false;
	}

	@Override
	public boolean onTouchCancel(int x, int y) {
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

	@Override
	public boolean onZoomOut(int x, int y) {
		return false;
	}

	@Override
	public boolean onZoomIn(int x, int y) {
		return false;
	}

}
