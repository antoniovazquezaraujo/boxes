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

import com.formatic.boxes.widgets.events.BoxEventAdapter;
import com.formatic.boxes.widgets.events.RotatorListener;



public class Rotator extends BoxContainer{
	public final static int NONE  =0;
	public final static int UP    =1;
	public final static int DOWN  =2;
	public final static int TO_MIN=3;
	public final static int TO_MAX=4;
	RotatorListener rotatorListener; 
	int action;
	int value;
	int minValue, maxValue;
	Switch[] switches;
	int lastX, lastY;
	public Rotator(final int minValue, final int maxValue, final int value) {
		super(2, 2);
		switches = new Switch[4];
		switches[0] =new Switch(0, 0, 1, 1);
		switches[1] =new Switch(1, 0, 1, 1);
		switches[2] =new Switch(1, 1, 1, 1);
		switches[3] =new Switch(0, 1, 1, 1);
		add(switches[0]);
		add(switches[1]);
		add(switches[2]);
		add(switches[3]);
		this.minValue = minValue;
		this.maxValue = maxValue;
		this.value = value;
		rotateColors();
		lastX =0;
		lastY=0;
		setBoxEventListener(new BoxEventAdapter() {
			
			@Override
			public boolean onZoomOut(int x, int y) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean onTouchCancel(int x, int y) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean onTouch(int x, int y) {
				// TODO Auto-generated method stub
				return true;
			}
			
			@Override
			public boolean onRelease(int x, int y) {
				// TODO Auto-generated method stub
				return true;
			}
			
			@Override
			public boolean onDrag(int x, int y, int newX, int newY) {
				//System.out.println("Drag:"+lastX+","+lastY+":"+newX+","+newY);
				//System.out.println(getPosition().x+"//"+getPosition().y);
				action = NONE;
				if (lastX != newX && lastY != newY) {
					//Diagonal
					if (lastX < newX && lastY > newY) {
						action = TO_MAX;
					}
					else {
						action = TO_MIN;
					}
				}
				else if (lastY == newY) {
					//Horizontal
					if (lastX < newX) {
						if (newY == 0) {
							action=UP;
						}
						else {
							action=DOWN;
						}
					}
					else if (lastX > newX) {
						if (newY == 0) {
							action=DOWN;
						}
						else {
							action=UP;
						}
					}
				}
				else {
					if (lastY < newY) {
						if (newX == 0) {
							action = DOWN;
						}
						else {
							action = UP;
						}
					}
					else if (lastY > newY) {
						if (newX == 0) {
							action = UP;
						}
						else {
							action = DOWN;
						}
					}
				}
				switch(action) {
					case UP: 
						if (Rotator.this.value<Rotator.this.maxValue)Rotator.this.value++; 
						break;
					case DOWN: 
						if (Rotator.this.value > Rotator.this.minValue)Rotator.this.value--; 
						break;
					case TO_MIN: 
						Rotator.this.value = Rotator.this.minValue; 
						break;
					case TO_MAX: 
						Rotator.this.value = Rotator.this.maxValue; 
						break;
				}
				rotateColors();

				if (rotatorListener != null) {
					rotatorListener.numberSelected(Rotator.this.value);
				}
				lastX=newX;
				lastY=newY;
				return true;
			}

		});
	}
	public void setRotatorListener(RotatorListener rotatorListener) {
		this.rotatorListener = rotatorListener;
	}

	public void rotateColors() {
		int rotation = Rotator.this.value % 4;
		for (int n=0; n<4; n++) {
			switches[3-n].setColor(1.0f/(1.0f+rotation));
			rotation++;
			rotation %=4;
		}
	}

}

