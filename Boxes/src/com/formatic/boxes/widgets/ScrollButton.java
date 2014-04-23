package com.formatic.boxes.widgets;

import com.formatic.boxes.Color;
import com.formatic.boxes.Point;
import com.formatic.boxes.gradients.ColorGradient;
import com.formatic.boxes.gradients.SquareGradient;

public class ScrollButton extends Button {

	SquareGradient squareGradient;

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

		super(x, y, width, height, normalColor, pressedColor);
//		squareGradient = new SquareGradient(new Point(x, y), new Point(x
//				+ width, y + height), 0.0f, 1.0f,
//				target, ColorGradient.Repeatable.NONE);
//		squareGradient.setAlignment(SquareGradient.Alignment.ROWS_AND_COLS);
//		squareGradient.setColumnOrder(SquareGradient.ColumnOrder.FROM_LEFT);
//		squareGradient.setRowOrder(SquareGradient.RowOrder.FROM_TOP);
//		squareGradient.setStartValue(0.0f);
//		setColorGradient(squareGradient);
	}

	private PreviewButtonListener previewButtonListener;

	public void setPreviewButtonListener(
			PreviewButtonListener previewButtonListener) {
		this.previewButtonListener = previewButtonListener;
	}

	@Override
	public boolean onDrag(int x, int y, int newX, int newY) {
		if (x != newX) {
			int change = newX > x?1:-1;
			previewButtonListener.valueChanged(change);
		}
		return true;
	}
}
