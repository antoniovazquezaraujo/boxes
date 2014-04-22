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
import com.formatic.boxes.widgets.events.BoxEventAdapter;

interface PreviewButtonListener {
	public void valueChanged(int change);
}

interface HueSelectorListener {
	public void valueChanged(float change);
}

class PreviewButton extends Button {

	public PreviewButton(int x, int y, int width, int height, Color normalColor) {
		super(x, y, width, height, normalColor, normalColor);
	}

	private PreviewButtonListener previewButtonListener;

	public void setPreviewButtonListener(
			PreviewButtonListener previewButtonListener) {
		this.previewButtonListener = previewButtonListener;
	}

	@Override
	public boolean onDrag(int x, int y, int newX, int newY) {
		if (x != newX) {
			previewButtonListener.valueChanged(newX - x);
		}
		return true;
	}

}

class HueSelector extends BoxContainer {
	enum Direction{
		UP, DOWN
	}
	HueSelectorListener hueSelectorListener;
	float hue, delta;
	float minDelta, maxDelta;
	float minHue, maxHue;
	float saturation, brightness;

	public void setSaturation(float saturation) {
		this.saturation = saturation;
	}

	public void setBrightness(float brightness) {
		this.brightness = brightness;
	}

	public HueSelector(int x, int y, int width, int height, float hue,
			float saturation, float brightness, float delta) {
		super(x, y, width, height);
		this.hue = hue;
		this.delta = delta;
		this.saturation = saturation;
		this.brightness = brightness;
		createBoxes();
		this.minHue = 0.00000000f;
		this.maxHue = 1.00000000f;
		this.maxDelta = 6 / boxes.size();
		this.minDelta = maxHue / 360.0f;
	}

	void createBoxes() {
		for (int x = 0; x < getSize().width; x++) {
			for (int y = 0; y < getSize().height; y++) {
				add(new Box(x, y, 1, 1, new Color(hue, saturation, brightness)));
			}
		}
	}

	@Override
	public boolean onRelease(int x, int y) {
		Box next = boxAtPos(new Point(x, y));
		hue = next.getColor().getHue();
		if (hueSelectorListener != null) {
			hueSelectorListener.valueChanged(hue);
		}
		return true;
	}

	@Override
	public boolean onDrag(int x, int y, int newX, int newY) {
		if (newX != x) {
			hue -= (newX - x) * 0.01f;
		} else if (newY != y) {
			delta += (newY - y) * 0.001f;
		}
		System.out.println("Hue:" + hue + " Delta:" + delta);
		if (hueSelectorListener != null) {
			hueSelectorListener.valueChanged(hue);
		}
		return true;
	}

	public void paint(){
		paint(Direction.UP, hue, delta);
		paint(Direction.DOWN, hue, delta);		
	}
	public void setHue(float hue) {
		this.hue = hue;
		paint();
	}

	private void paint(Direction direction, float hue, float delta) {
	}

	public void setDelta(float delta) {
		this.delta = delta;
		paint();
	}

	public void setHueSelectorListener(HueSelectorListener hueSelectorListener) {
		this.hueSelectorListener = hueSelectorListener;
	}
}

public class ColorChooser extends BoxContainer {
	PreviewButton saturationButton, brightnessButton;
	HueSelector hueSelector;
	Color selectedColor;

	public ColorChooser() {
		this(new Color(1, 0, 1, 1));
	}

	public ColorChooser(Color color) {
		super(8, 8);
		selectedColor = color;
		saturationButton = new PreviewButton(0, 0, 8, 1, selectedColor);
		saturationButton.setNormalColor(selectedColor);
		brightnessButton = new PreviewButton(0, 7, 8, 1, selectedColor);
		brightnessButton.setNormalColor(selectedColor);
		hueSelector = new HueSelector(0, 1, 8, 6, selectedColor.getHue(),
				selectedColor.getSaturation(), selectedColor.getBrightness(),
				0.01f);
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

}
