package com.formatic.boxes.commands;

public class ColorToneChanger extends RangeCommand {
	public ColorToneChanger(int times, float rangeTime, float fromValue,
			float toValue, int step) {
		super(times, rangeTime, fromValue, toValue, step);
	}

	public void exec() {
		box.setHue(actualValue);
	}
}