package com.formatic.boxes.widgets;

import com.formatic.boxes.Color;
import com.formatic.boxes.widgets.events.ButtonListener;

public class KeyboardKey extends Button {
	char c;
	static Color generalPressedColor = new Color(0, 0, 0, 1);
	String name;
	public KeyboardKey(int x, int y, char c) {
		super(x, y, 1, 1, new Color(KeyboardColors.getColor(c)), generalPressedColor);
		name = KeyboardColors.getName(c);
		this.c=c;
		setButtonListener(new ButtonListener() {
			@Override
			public boolean onClick(Button b) {
				//System.out.println(KeyboardKey.this.c);
				return false;
			}
		});
	}
}