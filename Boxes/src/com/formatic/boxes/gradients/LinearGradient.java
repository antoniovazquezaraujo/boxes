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
package com.formatic.boxes.gradients;

import com.badlogic.gdx.math.Interpolation;
import com.formatic.boxes.Point;
import com.formatic.boxes.gradients.ColorGradient.Repeatable;
import com.formatic.boxes.gradients.ColorGradient.Target;




public class LinearGradient extends ColorGradient {

	public LinearGradient(Point startPoint, Point endPoint, float minValue,
			float maxValue) {
		this(startPoint, endPoint, minValue, maxValue, Target.BRIGHTNESS,
				Repeatable.NONE);
	}

	public LinearGradient(Point startPoint, Point endPoint, float minValue,
			float maxValue, Target target, Repeatable repeatable) {
		super(startPoint, endPoint, minValue, maxValue, target, repeatable);
		update();
	}
	@Override
	public void update(){
		float totalDistance = (float) startPoint.distance(endPoint);
		for (int col = 0; col < 8; col++) {
			for (int row = 0; row < 8; row++) {
				float distance = distToLine(startPoint, endPoint, new Point(col, row));
				float d = Math.abs(distance / totalDistance);
				data[col][row] = Interpolation.linear.apply(minValue, maxValue,
						d);
			}
		}		
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

	@Override
	public float getGap() {
		// TODO Auto-generated method stub
		return 0;
	}
}
