package com.formatic.boxes.commands;

public class SymmetricalSizeChanger extends RangeCommand {
	public SymmetricalSizeChanger(int times, float rangeTime, float fromValue,
			float toValue, int step) {
		super(times, rangeTime, fromValue, toValue, step);
	}

	public void exec() {
		int prevWidth = box.getSize().width;
		int prevHeight = box.getSize().height;
		box.setWidth((int) actualValue);
		if (box.getSize().width % 2 == 0) {
			if (box.getSize().width > prevWidth) {
				box.decX();
			}
			if (box.getSize().width < prevWidth) {
				box.incX();
			}
		}
		box.setHeight((int) actualValue);
		if (box.getSize().height % 2 == 0) {
			if (box.getSize().height > prevHeight) {
				box.decY();
			}
			if (box.getSize().height < prevHeight) {
				box.incY();
			}
		}

	}
}