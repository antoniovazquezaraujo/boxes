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
import com.formatic.boxes.widgets.events.NumberKeyboardListener;

public class NumberKeyboard extends BoxContainer {
	NumberKeyboardListener numberKeyboardListener; 
	int selected;
	int firstX, firstY;
	boolean allClean;
	Switch[] switches;
	public NumberKeyboard(){
		super(2,2);
		switches = new Switch[4];
		switches[0] =new Switch(0,0,1,1);
		switches[1] =new Switch(1,0,1,1);
		switches[2] =new Switch(1,1,1,1);
		switches[3] =new Switch(0,1,1,1);
		add(switches[0]);
		add(switches[1]);
		add(switches[2]);
		add(switches[3]);
		selected = 0;
		allClean=true;
		setBoxEventListener(new BoxEventAdapter() {
			@Override
			public boolean onZoomOut(int x, int y) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean onTouchCancel(int x, int y) {
				//Ojo, importante devolver true para que al soltar 
				//no se apague el último switch automáticamente
				return true;
			}
			
			@Override
			public boolean onTouch(int x, int y) {
				firstX=x;
				firstY=y;
				selected = getOnePointNumber(x,y);
				activateSwitches();
//				if(numberKeyboardListener != null){
//					numberKeyboardListener.numberSelected(selected);
//				}
				return true; 
			}
			
			@Override
			public boolean onRelease(int x, int y) {
				if(x == firstX && y == firstY){
					selected = getOnePointNumber(x,y);
					activateSwitches();
					if(numberKeyboardListener != null){
						numberKeyboardListener.numberSelected(selected);
					}
				}
				return true;
			}
			
			@Override
			public boolean onDrag(int x, int y, int newX, int newY) {
				if(firstX != newX && firstY != newY){
					//Diagonal
					if(allClean){
						allClean=false;
						selected= 9;
					}else{
						allClean=true;
						selected = 0;
					}
				}else{
					//Horizontal
					if(firstX != newX){
						if(newY == 0){
							selected = 2;
						}else{
							selected = 6;
						}
						//Vertical
					}else{
						if(newX == 0){
							selected = 8;
						}else{
							selected = 4;
						}
					}
				}
				activateSwitches();
				if(numberKeyboardListener != null){
					numberKeyboardListener.numberSelected(selected);
				}
				return true;

			}

		});
	}
	public void setNumberKeyboardListener(NumberKeyboardListener numberKeyboardListener){
		this.numberKeyboardListener = numberKeyboardListener;
	}
	public int getOnePointNumber(int x, int y){
		if(x == 0){
			if(y == 0){
				return 1;
			}else{
				return 7;
			}
		}else{
			if(y == 0){
				return 3;
			}else{
				return 5;
			}
		}
	}

	public void activateSwitches(){
		if(selected == 9){
			for(int n=0;n<4; n++){
				switches[n].setPressed(true);
			}
		}else{
			for(int n=0;n<4; n++){
				switches[n].setPressed(false);
			}

			if(selected == 8 || selected == 1 || selected == 2){
				switches[0].setPressed(true);
			}
			if(selected == 2 || selected == 3 || selected == 4){
				switches[1].setPressed(true);
			}
			if(selected == 4 || selected == 5 || selected == 6){
				switches[2].setPressed(true);
			}
			if(selected == 6 || selected == 7 || selected == 8){
				switches[3].setPressed(true);
			}
		}
	}
}
