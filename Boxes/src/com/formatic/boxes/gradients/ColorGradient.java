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

import com.formatic.boxes.Color;
import com.formatic.boxes.Point;

abstract public class ColorGradient {
	public enum Target {
		HUE, SATURATION, BRIGHTNESS
	}

	public enum Repeatable {
		NONE, RESTARTING, REMOUNTING
	}

	public Point startPoint;
	public Point endPoint;
	public float minValue, maxValue, startValue;
	public Target target;
	public Repeatable repeatable;
	protected float[][]data;



	public ColorGradient(Point startPoint, Point endPoint, float minValue,
			float maxValue) {
		this(startPoint, endPoint, minValue, maxValue, Target.BRIGHTNESS,
				Repeatable.NONE);
	}

	public ColorGradient(Point startPoint, Point endPoint, float minValue,
			float maxValue, Target target, Repeatable repeatable) {
		this.startPoint = startPoint;
		this.endPoint = endPoint;
		this.minValue = minValue;
		this.maxValue = maxValue;
		this.target = target;
		this.repeatable = repeatable;
		this.data = new float[8][8];
	}
	
	public Point getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
		update();
	}

	public Point getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(Point endPoint) {
		this.endPoint = endPoint;
		update();
	}

	public float getMinValue() {
		return minValue;
	}

	public void setMinValue(float minValue) {
		this.minValue = minValue;
		update();
	}

	public float getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(float maxValue) {
		this.maxValue = maxValue;
		update();
	}

	public float getStartValue() {
		return startValue;
	}

	public void setStartValue(float startValue) {
		this.startValue = startValue;
		update();
	}

	public Target getTarget() {
		return target;
	}

	public void setTarget(Target target) {
		this.target = target;
	}

	public Repeatable getRepeatable() {
		return repeatable;
	}

	public void setRepeatable(Repeatable repeatable) {
		this.repeatable = repeatable;
		update();
	}
	public void update(Point location, Color color) {
		float f = data[location.x][location.y];
		switch(target){
		case BRIGHTNESS:
			color.setBrightness(f);
			break;
		case HUE:
			color.setHue(f);
			break;
		case SATURATION:
			color.setSaturation(f);
			break;
		}
	}
	abstract protected void update();
	public void setGap(float gap){
		
	}
}
