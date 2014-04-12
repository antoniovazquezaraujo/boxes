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
package com.formatic.boxes.commands;


public abstract class RangeCommand extends Command {
	float fromValue;
	float actualValue;
	float toValue;
	float delta;
	float rangeTime;
	int step;
	public RangeCommand(int times, float rangeTime, float fromValue, float toValue,
					int step) {
		super(times);
		this.rangeTime = rangeTime;
		this.fromValue = fromValue;
		this.toValue = toValue;
		this.actualValue = fromValue;
		this.step = step;
		this.delta = (toValue - fromValue) / rangeTime;		
	}

	abstract public void exec();
	
	public void update() {
		if ((delta > 0 && actualValue < toValue)
			|| (delta <= 0 && actualValue >= toValue)) {
			actualValue += delta;
			if ((step == 0) || (((int) (actualValue % step) == 0))) {
				exec();
			}
		} else {
			super.update();
			actualValue = fromValue;
		}
	}

	public void reset() {
		actualValue = fromValue;
		super.reset();
	}

}
