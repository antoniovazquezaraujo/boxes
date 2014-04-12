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
package com.formatic.boxes;

import com.badlogic.gdx.math.Interpolation;

public class RadialGradient extends ColorGradient {
	RadialGradient(Point from, Point to, Color fromColor, Color toColor,
			boolean repeats) {
		super(from, to, fromColor, toColor, repeats);
		//totalDistance = dist(from.x, from.y, to.x, to.y);
		totalDistance = from.distance(to);
	}

	Color getColor(Point location) {
		double distance = location.distance(from);
		float d =(float)( distance / totalDistance);
		float r = Interpolation.linear.apply(fromColor.r, toColor.r, d);
		float g = Interpolation.linear.apply(fromColor.g, toColor.g, d);
		float b = Interpolation.linear.apply(fromColor.b, toColor.b, d);
		return new Color(r, g, b,1);
	}
}
