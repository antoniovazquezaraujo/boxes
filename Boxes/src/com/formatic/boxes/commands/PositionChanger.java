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

public class PositionChanger extends RangeCommand {
	public enum MoveType{
		HORIZONTAL, VERTICAL, BOTH
	}
	public enum MoveTarget{
		BOX, GRADIENT_FROM, GRADIENT_TO
	}

	private MoveType moveType;
	private MoveTarget moveTarget;
	public PositionChanger(int times, float rangeTime, float fromValue,
			float toValue, int step, MoveType moveType) {
		this(times, rangeTime, fromValue, toValue, step, moveType, MoveTarget.BOX);
		
	}
	public PositionChanger(int times, float rangeTime, float fromValue,
			float toValue, int step, MoveType moveType, MoveTarget moveTarget) {
		super(times, rangeTime, fromValue, toValue, step);
		this.moveType = moveType;
		this.moveTarget = moveTarget;
	}

	public void exec() {
		if(moveType != MoveType.HORIZONTAL){
			switch(moveTarget){
			case BOX:
				box.setY((int) actualValue);
				break;
			case GRADIENT_FROM:
				box.colorGradient.from.setY((int) actualValue);
				break;
			case GRADIENT_TO:
				box.colorGradient.to.setY((int) actualValue);
				break;
			default:
				break;
			}
			
		}
		if(moveType != MoveType.VERTICAL){
			switch(moveTarget){
			case BOX:
				box.setX((int) actualValue);
				break;
			case GRADIENT_FROM:
				box.colorGradient.from.setX((int) actualValue);
				break;
			case GRADIENT_TO:
				box.colorGradient.to.setX((int) actualValue);
				break;
			default:
				break;
			}
		}
		
	}
}
