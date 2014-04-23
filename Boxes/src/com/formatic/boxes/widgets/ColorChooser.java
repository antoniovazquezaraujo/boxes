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

interface PreviewButtonListener {
	public void valueChanged(int change);
}

interface HueSelectorListener {
	public void valueChanged(float change);
}

public class ColorChooser extends BoxContainer {
	int baseWidth, baseHeight;
	ScrollButton saturationButton, brightnessButton;
	HueSelector hueSelector;
	Color selectedColor;
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
		saturationButton = new ScrollButton(0, 0, baseWidth, 1, selectedColor,new Color(1,0,0,1),ColorGradient.Target.SATURATION);
		saturationButton.setNormalColor(selectedColor);
		brightnessButton = new ScrollButton(0, baseHeight-1, baseWidth, 1, selectedColor,new Color(0,1,0,1),ColorGradient.Target.BRIGHTNESS);
		brightnessButton.setNormalColor(selectedColor);
		hueSelector = new HueSelector(0, 1, baseWidth, baseHeight-2, selectedColor.getHue(),
				selectedColor.getSaturation(), selectedColor.getBrightness());
		hueSelector.setHueSelectorListener(new HueSelectorListener() {
			@Override
			public void valueChanged(float change) {
				selectedColor.setHue(change);
			}
		});
		saturationButton.setPreviewButtonListener(new PreviewButtonListener() {
			@Override
			public void valueChanged(int change) {
				selectedColor.setSaturation(selectedColor.getSaturation()
						+ 0.1f / change);
				hueSelector.setSaturation(selectedColor.getSaturation());
			}
		});
		brightnessButton.setPreviewButtonListener(new PreviewButtonListener() {
			@Override
			public void valueChanged(int change) {
				selectedColor.setBrightness(selectedColor.getBrightness()
						+ 0.1f / change);
				hueSelector.setBrightness(selectedColor.getBrightness());

			}
		});
		add(saturationButton);
		add(brightnessButton);
		add(hueSelector);
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
