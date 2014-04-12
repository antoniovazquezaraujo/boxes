package com.formatic.boxes.commands;

public class ColorBrightnessChanger extends RangeCommand {
	public ColorBrightnessChanger(int times, float rangeTime, float fromValue,
			float toValue, int step) {
		super(times, rangeTime, fromValue, toValue, step);
	}

	public void exec() {
		box.setBrightness(actualValue);
	}
}