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
	HueSelectorListener hueSelectorListener;
	float hue, delta;
	float minDelta, maxDelta;
	float minHue, maxHue;
	float saturation, brightness;
	float topHue;
	float tmpHue;

	public void setSaturation(float saturation) {
		this.saturation = saturation;
		reloadBoxes();
	}

	public void setBrightness(float brightness) {
		this.brightness = brightness;
		reloadBoxes();
	}

	public HueSelector(int x, int y, int width, int height, float hue,
			float saturation, float brightness, float delta) {
		super(x, y, width, height);
		this.hue = hue;
		this.delta = delta;
		this.saturation = saturation;
		this.brightness = brightness;
		createBoxes();
		this.minHue = 0.05f;
		this.maxHue = 1.00000000f;
		this.topHue = hue + (delta * boxes.size() / 2);
		this.maxDelta = maxHue / boxes.size();
		this.minDelta = maxHue / 360.0f;

	}

	private void createBoxes() {
		float centralHue = hue;
		int width = getSize().width;
		int height = getSize().height;
		float tmpHue= centralHue;
		float overflow = 0.0f;
		for (int x = width/2; x >= 0; x--) {
			for (int y = height-1; y >= 0; y--) {
				add(new Box(x, y, 1, 1, new Color(tmpHue+overflow, saturation,brightness, Color.Mode.HSB)));
				tmpHue -= delta;
				if(tmpHue<0.0f){
					overflow=topHue;
				}
			}
		}
		tmpHue= centralHue+delta;
		overflow=0.0f;
		for (int x = (width/2)+1; x < width; x++) {
			for (int y = 0; y < height; y++) {
				add(new Box(x, y, 1, 1, new Color(tmpHue-overflow, saturation,brightness, Color.Mode.HSB)));
				tmpHue += delta;
				if(tmpHue>1.0f){
					overflow=topHue;
				}
			}
		}
	}

	private void reloadBoxes() {
		limit();
		float centralHue = hue;
		int width = getSize().width;
		int height = getSize().height;
		float tmpHue= centralHue-delta;
		float overflow = 0.0f;
		for (int x = width/2; x >= 0; x--) {
			for (int y = height-1; y >= 0; y--) {
				Box next = boxAtPos(new Point(x,y));
				next.setHue(tmpHue+overflow);
				next.setSaturation(saturation);
				next.setBrightness(brightness);
				tmpHue -= delta;
				if(tmpHue<0.0000f){
					overflow=topHue;
				}else{
					overflow=0.0f;
				}
			}
		}
		tmpHue= centralHue+delta;
		overflow=0.0f;
		for (int x = (width/2)+1; x < width; x++) {
			for (int y = 0; y < height; y++) {
				Box next = boxAtPos(new Point(x,y));
				next.setHue(tmpHue-overflow);
				next.setSaturation(saturation);
				next.setBrightness(brightness);
				tmpHue += delta;
				if(tmpHue>1.0000f){
					overflow=topHue;
				}else{
					overflow=0.0f;
				}
			}
		}
	}
	private void oldreloadBoxes() {
		int numBoxes = boxes.size();
		this.topHue = hue + (delta * boxes.size() / 2);
		tmpHue = topHue / 2;
		limit();
		for (int n = 0; n < getSize().width; n++) {
			for (int m = 0; m < getSize().height; m++) {
				Box next = boxAtPos(new Point(n, m));
				next.setHue(tmpHue);
				next.setSaturation(saturation);
				next.setBrightness(brightness);
				tmpHue += delta;
				limit();
			}
		}
	}

	private void limit() {
		if(hue>1.0000f){
			hue-=topHue;
		}
		if(hue<0.0000f){
			hue+=topHue;
		}
//		this.topHue = hue + (delta * boxes.size() / 2);
//		if (hue < minHue) {
//			hue = minHue + topHue;
//		}
//		if (hue > maxHue) {
//			hue = maxHue - topHue;
//		}
//		if (tmpHue < minHue) {
//			tmpHue = minHue + topHue;
//		}
//		if (tmpHue > maxHue) {
//			tmpHue = maxHue - topHue;
//		}
//
		if (delta > maxDelta) {
			delta = maxDelta;
		}
		if (delta < minDelta) {
			delta = minDelta;
		}
	}

	@Override
	public boolean onRelease(int x, int y) {
		Box next = boxAtPos(new Point(x, y));
		hue = next.getColor().getHue();
		reloadBoxes();
		if (hueSelectorListener != null) {
			hueSelectorListener.valueChanged(hue);
		}
		return true;
	}

	@Override
	public boolean onDrag(int x, int y, int newX, int newY) {
		if (newX != x) {
			hue -= (newX - x) * 0.01f;
			limit();
			reloadBoxes();
		} else if (newY != y) {
			delta += (newY - y) * 0.001f;
			limit();
			reloadBoxes();
		}
		System.out.println("Hue:" + hue + " Delta:" + delta);
		if (hueSelectorListener != null) {
			hueSelectorListener.valueChanged(hue);
		}
		return true;
	}

	public void setHue(float hue) {
		this.hue = hue;
		limit();
		reloadBoxes();
	}

	public void setDelta(float delta) {
		this.delta = delta;
		limit();
		reloadBoxes();
	}

	public void setHueSelectorListener(HueSelectorListener hueSelectorListener) {
		this.hueSelectorListener = hueSelectorListener;
	}
}

public class OldColorChooser extends BoxContainer {
	PreviewButton saturationButton, brightnessButton;
	HueSelector hueSelector;
	Color selectedColor;

	public OldColorChooser() {
		this(new Color(1, 0, 1, 1));
	}

	public OldColorChooser(Color color) {
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
