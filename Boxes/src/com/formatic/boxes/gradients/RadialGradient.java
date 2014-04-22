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
import com.formatic.boxes.Color;
import com.formatic.boxes.Point;
import com.formatic.boxes.gradients.ColorGradient.Repeatable;
import com.formatic.boxes.gradients.ColorGradient.Target;

public class RadialGradient extends ColorGradient {
	public RadialGradient(Point startPoint, Point endPoint, float minValue,
			float maxValue) {
		this(startPoint, endPoint, minValue, maxValue, Target.BRIGHTNESS,
				Repeatable.NONE);
	}

	public RadialGradient(Point startPoint, Point endPoint, float minValue,
			float maxValue, Target target, Repeatable repeatable) {
		super(startPoint, endPoint, minValue, maxValue, target, repeatable);
		update();
	}
	@Override
	protected void update(){
		float totalDistance = (float) startPoint.distance(endPoint);
		for (int col = 0; col < 8; col++) {
			for (int row = 0; row < 8; row++) {
				float distance = (float) new Point(col, row)
						.distance(startPoint);
				float d = distance / totalDistance;
				data[col][row] = Interpolation.linear.apply(minValue, maxValue,
						d);
			}
		}		
	}
}
