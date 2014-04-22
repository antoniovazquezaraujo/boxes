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
import com.formatic.boxes.Point;
import com.formatic.boxes.widgets.events.BoxEventAdapter;

interface ScrollerListener {
	public void valueChanged(int change);
}
public class Scroller extends BoxContainer {
	public Scroller() {
		this(new Color(1, 0, 1, 1));
	}

	public Scroller(Color color) {
	
	}

}
