package com.formatic.boxes.commands;

public class ColorChanger extends RangeCommand {
	public enum ColorChangeType{
		HUE,SATURATION,BRIGHTNESS, RED, GREEN, BLUE
	}
	public enum ColorChangeTarget{
		BOX, GRADIENT_FROM, GRADIENT_TO
	}

	private ColorChangeType colorChangeType;
	private ColorChangeTarget colorChangeTarget;
	public ColorChanger(int times, float rangeTime, float fromValue,
			float toValue, int step , ColorChangeType colorChangeType) {
		this(times, rangeTime, fromValue, toValue, step, colorChangeType, ColorChangeTarget.BOX);
	}
	public ColorChanger(int times, float rangeTime, float fromValue,
			float toValue, int step , ColorChangeType colorChangeType, ColorChangeTarget colorChangeTarget) {
		super(times, rangeTime, fromValue, toValue, step);
		this.colorChangeType = colorChangeType;
		this.colorChangeTarget = colorChangeTarget;
	}

	public void exec() {
		switch(colorChangeType){
		case BLUE:
			box.setBlue(actualValue);
			break;
		case BRIGHTNESS:
			box.setBrightness(actualValue);
			break;
		case GREEN:
			box.setGreen(actualValue);
			break;
		case HUE:
			box.setHue(actualValue);
			break;
		case RED:
			box.setRed(actualValue);
			break;
		case SATURATION:
			box.setSaturation(actualValue);
			break;
		default:
			break;		
		}
	}
}