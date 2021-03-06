package com.formatic.boxes.widgets;

import com.formatic.boxes.Color;
import com.formatic.boxes.Point;
import com.formatic.boxes.gradients.ColorGradient;
import com.formatic.boxes.gradients.SquareGradient;

public class ScrollButton extends Button {

	float value;
	SquareGradient squareGradient;
	private ScrollButtonListener scrollButtonListener;
	
	public ScrollButton(int x, int y, int width, int height, Color normalColor) {
		this(x, y, width, height, normalColor, normalColor);
	}

	public ScrollButton(int x, int y, int width, int height, Color normalColor,
			Color pressedColor) {
		this(x, y, width, height, normalColor, pressedColor, 
				ColorGradient.Target.BRIGHTNESS);
	}
	public ScrollButton(int x, int y, int width, int height, Color normalColor,
			Color pressedColor, ColorGradient.Target target) {
		this(x, y, width, height, normalColor, pressedColor, 
				target, 0.0f);
	}
	public ScrollButton(int x, int y, int width, int height, Color normalColor,
			Color pressedColor, ColorGradient.Target target, float value) {

		super(x, y, width, height, normalColor, pressedColor);
		this.value = value;
		///////////////////////
		// pongo esto a 1
		this.value = 1.0f;
		squareGradient = new SquareGradient(new Point(x, y), new Point(x
				+ width, y + height), 0.0f, 1.0f,
				target, ColorGradient.Repeatable.NONE);
		squareGradient.setAlignment(SquareGradient.Alignment.ROWS_AND_COLS);
		squareGradient.setColumnOrder(SquareGradient.ColumnOrder.FROM_LEFT);
		squareGradient.setRowOrder(SquareGradient.RowOrder.FROM_TOP);
		squareGradient.setStartValue(value);
		// cambiar esto para verticales
		squareGradient.setMinGapDivider(width);
		setColorGradient(squareGradient);
	}



	public void setPreviewButtonListener(
			ScrollButtonListener scrollButtonListener) {
		this.scrollButtonListener = scrollButtonListener;
	}

	@Override
	public boolean onDrag(int x, int y, int newX, int newY) {
		if (x != newX) {
			int scrollSize = newX -x;
			this.value+=(0.1f/scrollSize);
			if(value>1.0)value=1.0f;
			if(value<0)value = 0.0f;
			squareGradient.setValue(value);
			scrollButtonListener.valueChanged(value);
		}
		return true;
	}
}
