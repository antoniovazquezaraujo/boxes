package com.formatic.boxes.widgets;

import com.formatic.boxes.Color;
import com.formatic.boxes.Point;
import com.formatic.boxes.gradients.ColorGradient;
import com.formatic.boxes.gradients.SquareGradient;

public class HueSelector extends Box  {
	enum Direction{
		UP, DOWN
	}
	HueSelectorListener hueSelectorListener;
	SquareGradient squareGradient;
	float delta;
	float minDelta, maxDelta;
	float minHue, maxHue;
	int hueInc;

	public void setSaturation(float saturation) {
		this.color.setSaturation(saturation);
	}

	public void setBrightness(float brightness) {
		this.color.setBrightness(brightness);
	}

	public HueSelector(int x, int y, int width, int height, float hue,
			float saturation, float brightness) {
		super(x, y, width, height);
		hueInc = height;
		setColor(new Color(hue, saturation, brightness, Color.Mode.HSB));
		squareGradient = new SquareGradient(new Point(0,0), new Point(width, height), 0.0f, 1.0f, ColorGradient.Target.HUE, ColorGradient.Repeatable.NONE);
		squareGradient.setColumnOrder(SquareGradient.ColumnOrder.FROM_CENTER);
		squareGradient.setRowOrder(SquareGradient.RowOrder.FROM_TOP);
		squareGradient.setStartValue(0.5f);
		setColorGradient(squareGradient);
	}

	@Override
	public boolean onRelease(int x, int y) {
		float hue = squareGradient.getValueAt(x,y);
		squareGradient.setStartValue(hue);
		if (hueSelectorListener != null) {
			hueSelectorListener.valueChanged(hue);
		}
		return true;
	}

	@Override
	public boolean onDrag(int x, int y, int newX, int newY) {
		if (newX != x) {
			if(newX < x){
				squareGradient.incValue(hueInc);
			}else{
				squareGradient.decValue(hueInc);
			}
		} else if (newY != y) {
			if(newY > y){
				squareGradient.incGap(hueInc*2);
			}else{
				squareGradient.decGap(hueInc*2);
			}
		}
		if (hueSelectorListener != null) {
			hueSelectorListener.valueChanged(squareGradient.getStartValue());
		}
		return true;
	}

	public void setHueSelectorListener(HueSelectorListener hueSelectorListener) {
		this.hueSelectorListener = hueSelectorListener;
	}
}
