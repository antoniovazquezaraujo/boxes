package com.formatic.boxes.widgets;

import com.formatic.boxes.Color;
import com.formatic.boxes.Point;
import com.formatic.boxes.gradients.ColorGradient;
import com.formatic.boxes.gradients.SquareGradient;

interface ScrollBarListener {
	public void valueChanged(float change);
}

public class ScrollBar extends Button {
	public enum GapType {
		PAGES, LINES
	}

	float value;
	float lineGap, pageGap;
	final float MAX_VALUE=1.0f;
	final float MIN_VALUE=0.0f;
	SquareGradient squareGradient;

	private ScrollButtonListener scrollButtonListener;

	public void limit(){
		if(value< MIN_VALUE) value=MIN_VALUE;
		if(value> MAX_VALUE) value=MAX_VALUE;
	}
	public void moveByLine(GapType gapType, float change){
		switch(gapType){
		case LINES:
			value+=(change*lineGap);
			break;
		case PAGES:
			value+=(change*pageGap);
			break;
		default:
			break;
		}
		limit();
	}
	public void moveToStart(){
		value= MIN_VALUE;
	}
	public void moveToEnd(){
		value=MAX_VALUE;
	}
	public void scroll(int speed, float chang){
		
	}
	public ScrollBar(int x, int y, int width, int height, Color normalColor) {
		this(x, y, width, height, normalColor, normalColor);
	}

	public ScrollBar(int x, int y, int width, int height, Color normalColor,
			Color pressedColor) {
		this(x, y, width, height, normalColor, pressedColor, 
				ColorGradient.Target.BRIGHTNESS);
	}
	public ScrollBar(int x, int y, int width, int height, Color normalColor,
			Color pressedColor, ColorGradient.Target target) {
		this(x, y, width, height, normalColor, pressedColor, 
				target, 0.0f);
	}
	public ScrollBar(int x, int y, int width, int height, Color normalColor,
			Color pressedColor, ColorGradient.Target target, float value) {

		super(x, y, width, height, normalColor, pressedColor);
		this.value = value;
		squareGradient = new SquareGradient(new Point(x, y), new Point(x
				+ width, y + height), 0.0f, 1.0f,
				target, ColorGradient.Repeatable.NONE);
		squareGradient.setAlignment(SquareGradient.Alignment.ROWS_AND_COLS);
		squareGradient.setColumnOrder(SquareGradient.ColumnOrder.FROM_LEFT);
		squareGradient.setRowOrder(SquareGradient.RowOrder.FROM_TOP);
		squareGradient.setStartValue(value);
		// cambiar esto para verticales
//		squareGradient.setMinGapDivider(width);
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
			squareGradient.incValue(scrollSize);
			scrollButtonListener.valueChanged(1.0f/scrollSize);
		}
		return true;
	}
	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}

	public float getLineGap() {
		return lineGap;
	}

	public void setLineGap(float lineGap) {
		this.lineGap = lineGap;
	}

	public float getPageGap() {
		return pageGap;
	}

	public void setPageGap(float pageGap) {
		this.pageGap = pageGap;
	}
}
