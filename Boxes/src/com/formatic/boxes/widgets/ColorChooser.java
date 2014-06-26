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

import com.formatic.boxes.Color;
import com.formatic.boxes.Point;
import com.formatic.boxes.gradients.ColorGradient;
import com.formatic.boxes.gradients.SquareGradient;
import com.formatic.boxes.widgets.events.ButtonListener;
import com.formatic.boxes.widgets.events.ColorChooserListener;
import com.formatic.boxes.widgets.events.DialogListener;

interface ScrollButtonListener {
	public void valueChanged(float change);
}

interface HueSelectorListener {
	public void valueChanged(float change);
}
class NoDragButton extends Button{
	public NoDragButton(int x, int y, int width, int height, Color normalColor, Color selectedColor){
		super(x,y,width,height, normalColor, selectedColor);
	}
	@Override
	public boolean onDrag(int x, int y, int newX, int newY) {
		/*
		 * Esto solo es para que los botones Aceptar y Cancelar pasen hacia abajo
		 * el evento de drag
		 */
		return false;
	}
	
}
public class ColorChooser extends BoxContainer implements ButtonListener {
	int baseWidth, baseHeight;
	ScrollButton saturationScroller, brightnessScroller;
	NoDragButton acceptButton, cancelButton;
	HueSelector hueSelector;
	Color selectedColor;
	ColorChooserListener colorChooserListener;

	public enum Size{
		MINIMAL, MEDIUM, BIG, FULL
	}
	public ColorChooser() {
		this(Size.FULL);
	}

	public ColorChooser(Size size) {
		super(8, 8);
		adjustSizes(size);
		setSize(baseWidth, baseHeight);
		selectedColor = new Color(1,0,1,1);
		cancelButton = new NoDragButton(baseWidth-1,0           ,1,1, selectedColor, new Color(1,1,1,1));
		cancelButton.setButtonListener(this);
		acceptButton = new NoDragButton(baseWidth-1,baseHeight-1,1,1, selectedColor, new Color(1,1,1,1));
		acceptButton.setButtonListener(this);
		saturationScroller = new ScrollButton(0, 0,            baseWidth, 1,new Color(1,1,1,1),new Color(1,1,1,1),ColorGradient.Target.SATURATION);
		brightnessScroller = new ScrollButton(0, baseHeight-1, baseWidth, 1,new Color(1,1,1,1),new Color(1,1,1,1),ColorGradient.Target.BRIGHTNESS);
		hueSelector = new HueSelector(0, 1, baseWidth, baseHeight-2, selectedColor.getHue(),
				selectedColor.getSaturation(), selectedColor.getBrightness());
		hueSelector.setHueSelectorListener(new HueSelectorListener() {
			@Override
			public void valueChanged(float change) {
				selectedColor.setHue(change);
			}
		});
		saturationScroller.setPreviewButtonListener(new ScrollButtonListener() {
			@Override
			public void valueChanged(float value) {
//				selectedColor.setSaturation(selectedColor.getSaturation()
//						+ 0.1f / change);
				selectedColor.setSaturation(value);
				hueSelector.setSaturation(selectedColor.getSaturation());
			}
		});
		brightnessScroller.setPreviewButtonListener(new ScrollButtonListener() {
			@Override
			public void valueChanged(float value) {
//				selectedColor.setBrightness(selectedColor.getBrightness()
//						+ 0.1f / change);
				selectedColor.setBrightness(value);
				hueSelector.setBrightness(selectedColor.getBrightness());

			}
		});
		add(saturationScroller);
		add(cancelButton);
		add(hueSelector);
		add(brightnessScroller);
		add(acceptButton);
	}
	public Color getSelectedColor() {
		return selectedColor;
	}

	public void setColorChooserListener(ColorChooserListener colorChooserListener){
		this.colorChooserListener=colorChooserListener;
	}
	@Override
	public boolean onClick(Button b) {
		if(b == acceptButton){
			if(colorChooserListener != null)colorChooserListener.accepted();
		}
		if(b == cancelButton){
			if(colorChooserListener != null)colorChooserListener.canceled();
		}
		return true;
	}
	public void adjustSizes(Size size){
		switch(size){
		case BIG:
			baseWidth=4;
			baseHeight=8;
			break;
		case FULL:
			baseWidth=8;
			baseHeight=8;
			break;
		case MEDIUM:
			baseWidth=3;
			baseHeight=6;
			break;
		case MINIMAL:
			baseWidth=2;
			baseHeight=5;
			break;
		}
	}


}
