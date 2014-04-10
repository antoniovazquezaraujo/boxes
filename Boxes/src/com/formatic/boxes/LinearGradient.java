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


import com.badlogic.gdx.graphics.Color;

public class LinearGradient extends ColorGradient {
	LinearGradient(Point from, Point to, Color fromColor, Color toColor,
					boolean repeats) {
		super(from, to, fromColor, toColor, repeats);
		totalDistance = dist(from.x, from.y, to.x, to.y);
	}

	public float dist(float x1, float y1, float x2, float y2) {
		float dx = x1 - x2;
		float dy = y1 - y2;
		return (float) Math.sqrt(dx * dx + dy * dy);
	}

	Color getColor(Point location) {
		// float distance = dist(location.x, location.y, from.x, from.y);
		// Cómo calculo la distancia a la perpendicular???
		float distance = distToLine(from, to, location);
		float d = Math.abs((float) (distance / totalDistance));
		Color tmp= new Color(fromColor);
		tmp.lerp(toColor.r, toColor.g, toColor.b, toColor.a, d);
		return tmp;
	}

	float distToLine(Point p1, Point p2, Point p) {
		// (y1-y2)x + (x2-x1)y + (x1y2-x2y1)
		// d(P,L) = --------------------------------
		// sqrt( (x2-x1)pow2 + (y2-y1)pow2 )

		double ch = (p1.y - p2.y) * p.x + (p2.x - p1.x) * p.y
					+ (p1.x * p2.y - p2.x * p1.y);
		double del = Math.sqrt(Math.pow(p2.x - p1.x, 2)
								+ Math.pow(p2.y - p1.y, 2));
		double d = ch / del;
		return (float) d;

		
		
	}
}
