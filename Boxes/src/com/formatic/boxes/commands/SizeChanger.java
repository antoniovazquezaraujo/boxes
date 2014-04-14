package com.formatic.boxes.commands;

public class SizeChanger extends RangeCommand {
	private boolean symmetrical;
	private ChangeType changeType;

	public enum ChangeType {
		WIDTH, HEIGHT, BOTH
	}

	public SizeChanger(int times, float rangeTime, float fromValue,
			float toValue, int step, boolean symmetrical, ChangeType changeType) {
		super(times, rangeTime, fromValue, toValue, step);
		this.symmetrical = symmetrical;
		this.changeType = changeType;
	}

	public void exec() {
		int prevWidth = box.getSize().width;
		int prevHeight = box.getSize().height;
		if (changeType != ChangeType.HEIGHT) {
			box.setWidth((int) actualValue);
			if (symmetrical) {
				if (box.getSize().width % 2 == 0) {
					if (box.getSize().width > prevWidth) {
						box.decX();
					}
					if (box.getSize().width < prevWidth) {
						box.incX();
					}
				}
			}
		}
		if (changeType != ChangeType.WIDTH) {
			box.setHeight((int) actualValue);
			if (symmetrical) {
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
	}
}