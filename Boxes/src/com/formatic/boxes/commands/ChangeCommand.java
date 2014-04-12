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


class ChangeType {
	final static int FIXED = 1;
	final static int GRADIENT = 2;
}
/*
 * THIS IS NOT OBJECT ORIENTED AT ALL!!!!!
 * I KNOW I MUST REFACTOR THIS!!!
 */
public class ChangeCommand extends Command {
	public final static int LOCATION_X = 0;
	public final static int LOCATION_Y = 1;
	public final static int SIZE_W = 2;
	public final static int SIZE_H = 3;
	public final static int SIZE_W_AND_H = 4;
	public final static int GRADIENT_LOCATION_FROM_X = 5;
	public final static int GRADIENT_LOCATION_FROM_Y = 6;
	public final static int GRADIENT_LOCATION_TO_X = 7;
	public final static int GRADIENT_LOCATION_TO_Y = 8;
	// Colors (0-255)
	public final static int COLOR_R = 9;
	public final static int COLOR_G = 10;
	public final static int COLOR_B = 11;
	public final static int COLOR_HUE = 12;
	public final static int COLOR_SATURATION = 13;
	public final static int COLOR_BRIGHTNESS = 14;
	
	public final static int GRADIENT_COLOR_FROM_R = 15;
	public final static int GRADIENT_COLOR_FROM_G = 16;
	public final static int GRADIENT_COLOR_FROM_B = 17;
	public final static int GRADIENT_COLOR_FROM_HUE = 18;
	public final static int GRADIENT_COLOR_FROM_SATURATION = 19;
	public final static int GRADIENT_COLOR_FROM_BRIGHTNESS = 20;

	public final static int GRADIENT_COLOR_TO_R = 21;
	public final static int GRADIENT_COLOR_TO_G = 22;
	public final static int GRADIENT_COLOR_TO_B = 23;
	public final static int GRADIENT_COLOR_TO_HUE = 24;
	public final static int GRADIENT_COLOR_TO_SATURATION = 25;
	public final static int GRADIENT_COLOR_TO_BRIGHTNESS = 26;

	float fromValue;
	float actualValue;
	float toValue;
	float delta;
	float rangeTime;
	int step;
	int changeTarget;

	static float random(int i){
		return (float)(Math.random()*i);
	}
	public static ChangeCommand randomCommand(){
			if(random(10) > 5){ //pos and size
				return new ChangeCommand((int)random(50), random(10), random(8), random(8), (int)(random(100)), (int)random(8));
			}else{
				return new ChangeCommand((int)random(50), random(10), random(255), random(255), 0, 9+(int)random(18));     
			}
	}
	public ChangeCommand(int times, float rangeTime, float fromValue, float toValue,
					int step, int changeTarget) {
		super(times);
		this.rangeTime = rangeTime;
		this.fromValue = fromValue;
		this.toValue = toValue;
		this.actualValue = fromValue;
		this.step = step;
		this.changeTarget = changeTarget;
		this.delta = (toValue - fromValue) / rangeTime;
	}

	public void update() {
		if ((delta > 0 && actualValue < toValue)
			|| (delta <= 0 && actualValue >= toValue)) {
			actualValue += delta;
			if ((step == 0) || (((int) (actualValue % step) == 0))) {
				switch (changeTarget) {
				case LOCATION_X:
					box.setX((int) actualValue);
					break;
				case LOCATION_Y:
					box.setY((int) actualValue);
					break;
				case SIZE_W_AND_H:
					box.setWidth((int) actualValue);
					box.setHeight((int) actualValue);
					break;
				case SIZE_W:
					box.setWidth((int) actualValue);
					break;
				case SIZE_H:
					box.setHeight((int) actualValue);
					break;
				case COLOR_R:
					box.setRed(actualValue);
					break;
				case COLOR_G:
					box.setGreen(actualValue);
					break;
				case COLOR_B:
					box.setBlue(actualValue);
					break;
				case COLOR_HUE:
					box.setHue(actualValue);
					break;
				case COLOR_SATURATION:
					box.setSaturation(actualValue);
					break;
				case COLOR_BRIGHTNESS:
					box.setBrightness(actualValue);
					break;
				case GRADIENT_LOCATION_FROM_X:
					box.colorGradient.from.x = (int) actualValue;
					break;
				case GRADIENT_LOCATION_FROM_Y:
					box.colorGradient.from.y = (int) actualValue;
					break;
				case GRADIENT_LOCATION_TO_X:
					box.colorGradient.to.x = (int) actualValue;
					break;
				case GRADIENT_LOCATION_TO_Y:
					box.colorGradient.to.y = (int) actualValue;
					break;
				case GRADIENT_COLOR_FROM_R:
					box.colorGradient.setFromRed(actualValue);
					break;
				case GRADIENT_COLOR_FROM_G:
					box.colorGradient.setFromGreen(actualValue);
					break;
				case GRADIENT_COLOR_FROM_B:
					box.colorGradient.setFromBlue(actualValue);
					break;
				case GRADIENT_COLOR_FROM_HUE:
					box.colorGradient.setFromHue(actualValue);
					break;
				case GRADIENT_COLOR_FROM_SATURATION:
					box.colorGradient.setFromSaturation(actualValue);
					break;
				case GRADIENT_COLOR_FROM_BRIGHTNESS:
					box.colorGradient.setFromBrightness(actualValue);
					break;
				case GRADIENT_COLOR_TO_R:
					box.colorGradient.setToRed(actualValue);
					break;
				case GRADIENT_COLOR_TO_G:
					box.colorGradient.setToGreen(actualValue);
					break;
				case GRADIENT_COLOR_TO_B:
					box.colorGradient.setToBlue(actualValue);
					break;
				case GRADIENT_COLOR_TO_HUE:
					box.colorGradient.setToHue(actualValue);
					break;
				case GRADIENT_COLOR_TO_SATURATION:
					box.colorGradient.setToSaturation(actualValue);
					break;
				case GRADIENT_COLOR_TO_BRIGHTNESS:
					box.colorGradient.setToBrightness(actualValue);
					break;
				}
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
