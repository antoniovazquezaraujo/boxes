package com.formatic.boxes.gradients;

import com.formatic.boxes.Color;
import com.formatic.boxes.Point;
import com.formatic.boxes.gradients.ColorGradient.Repeatable;
import com.formatic.boxes.gradients.ColorGradient.Target;

public class SquareGradient extends ColorGradient {

	public enum Alignment {
		ROWS_AND_COLS, COLS_AND_ROWS
	}

	public enum ColumnOrder {
		FROM_LEFT, FROM_RIGHT, FROM_CENTER
	}

	public enum RowOrder {
		FROM_TOP, FROM_BOTTOM, FROM_CENTER
	}

	private int width;
	private int height;
	private float gap;
	private ColumnOrder columnOrder;
	private RowOrder rowOrder;

	private Alignment alignment;
	private float leftValue, rightValue;
	private float leftGap, rightGap;
	private int gapDivider;
	private int minGapDivider;

	public SquareGradient(Point startPoint, Point endPoint, float minValue,
			float maxValue) {
		this(startPoint, endPoint, minValue, maxValue, Target.BRIGHTNESS,
				Repeatable.NONE);
	}

	public SquareGradient(Point startPoint, Point endPoint, float minValue,
			float maxValue, Target target, Repeatable repeatable) {
		super(startPoint, endPoint, minValue, maxValue, target, repeatable);
		this.width = (endPoint.x - startPoint.x);
		this.height = (endPoint.y - startPoint.y);
		this.minValue = minValue;
		this.maxValue = maxValue;
		this.startValue = minValue;
		this.minGapDivider = 6;
		this.gapDivider = minGapDivider;
		this.gap = 1.0f / gapDivider;
		this.leftGap = gap * -1;
		this.rightGap = gap;
		this.columnOrder = ColumnOrder.FROM_LEFT;
		this.rowOrder = RowOrder.FROM_TOP;
		this.repeatable = repeatable;
		this.alignment = Alignment.COLS_AND_ROWS;
		update();

	}

	public void setValue(float value){
		this.startValue=value;
	}
	public void setMinGapDivider(int minGapDivider) {
		this.minGapDivider = minGapDivider;
		this.gapDivider = minGapDivider;
		gap = 1.0f / gapDivider;
		this.leftGap = gap * -1.0f;
		this.rightGap = gap;
		limitValues();
		update();
	}

	public void incValue() {
		incValue(1);
	}

	public void incValue(int inc) {
		startValue += rightGap * inc;
		limitValues();
		update();
	}

	public void decValue() {
		decValue(1);
	}

	public void decValue(int dec) {
		startValue += leftGap * dec;
		limitValues();
		update();
	}

	public void incGap() {
		incGap(1);
	}

	public void incGap(int dec) {
		gapDivider -= dec;
		if (gapDivider < minGapDivider) {
			gapDivider = minGapDivider;
		}
		gap = 1.0f / gapDivider;
		// leftGap = gap * -1;
		// rightGap = gap;
		update();
	}

	public void decGap() {
		decGap(1);
	}

	public void decGap(int inc) {
		gapDivider += inc;
		if (gapDivider > 360) {
			gapDivider = 360;
		}
		gap = 1.0f / gapDivider;
		// leftGap = gap * -1;
		// rightGap = gap;
		update();
	}

	public void setGap(float gap) {
		this.gap = gap;
		update();
	}

	public Alignment getAlignment() {
		return alignment;
	}

	public void setAlignment(Alignment alignment) {
		this.alignment = alignment;
		update();
	}

	private void limitValues() {
		switch (repeatable) {
		case NONE:
			if (rightValue + rightGap >= maxValue) {
				rightValue = maxValue;
			} else if (rightValue + rightGap < minValue) {
				rightValue = minValue;
			} else{
				rightValue += rightGap;
			}

			if (leftValue + leftGap < minValue) {
				leftValue = minValue;
			} else if (leftValue + leftGap >= maxValue) {
				leftValue = maxValue;
			} else {
				leftValue += leftGap;
			}
			break;
		case REMOUNTING:
			if (rightGap >= 0) {
				if (rightValue + rightGap >= maxValue) {
					rightGap *= -1;
				}
			} else {
				if (rightValue + rightGap < minValue) {
					rightGap *= -1;
				}
			}
			if (leftGap <= 0) {
				if (leftValue + leftGap < minValue) {
					leftGap *= -1;
				}
			} else {
				if (leftValue + leftGap >= maxValue) {
					leftGap *= -1;
				}
			}
			rightValue += rightGap;
			leftValue += leftGap;
			break;
		case RESTARTING:
			if (rightValue + rightGap >= maxValue) {
				rightValue = minValue;
			} else if (rightValue + rightGap < minValue) {
				rightValue = maxValue;
				rightValue += rightGap;
			} else {
				rightValue += rightGap;
			}

			if (leftValue + leftGap < minValue) {
				leftValue = maxValue;
				leftValue += leftGap;
			} else if (leftValue + leftGap >= maxValue) {
				leftValue = minValue;
			} else {
				leftValue += leftGap;
			}
			break;
		}

	}

	@Override
	public void update() {
		this.width = (endPoint.x - startPoint.x);
		this.height = (endPoint.y - startPoint.y);
		int centerCol = width / 2;
		int centerLine = height / 2;
		int upLine = centerLine - 1;
		int downLine = centerLine;
		int leftCol = centerCol - 1;
		int rightCol = centerCol;
		leftGap = gap * -1;
		rightGap = gap;
		leftValue = startValue;// + leftGap;
		rightValue = startValue;// + rightGap;

		if (alignment == Alignment.ROWS_AND_COLS) {
			switch (columnOrder) {
			case FROM_CENTER:
				if (height % 2 != 0) {
					downLine++;
					// central line
					if (width % 2 != 0) {
						rightCol++;
						// first: central col of central line
						data[centerCol][centerLine] = startValue;
						limitValues();
						// second: rest of cols of central line
						for (int col = 0; col < width / 2; col++) {
							data[rightCol + col][centerLine] = rightValue;
							data[leftCol - col][centerLine] = leftValue;
							limitValues();
						}
					} else {
						// no central col: all the cols of central line
						// toghether
						limitValues();
						for (int col = 0; col < width / 2; col++) {
							data[rightCol + col][centerLine] = rightValue;
							data[leftCol - col][centerLine] = leftValue;
							limitValues();
						}
					}
				}
				// rest of lines
				for (int row = 0; row < height / 2; row++) {
					for (int col = 0; col < width; col++) {
						data[(width - 1) - col][upLine - row] = leftValue;
						data[col][downLine + row] = rightValue;
						limitValues();
					}
				}
				break;
			case FROM_LEFT:
				rightValue = startValue;
				switch (rowOrder) {
				case FROM_BOTTOM:
					for (int row = height - 1; row >= 0; row--) {
						for (int col = 0; col < width; col++) {
							data[col][row] = rightValue;
							limitValues();
						}
					}
					break;
				case FROM_CENTER:
					// ???
					break;
				case FROM_TOP:
					for (int row = 0; row < height; row++) {
						for (int col = 0; col < width; col++) {
							data[col][row] = rightValue;
							limitValues();
						}
					}
					break;
				}
				break;
			case FROM_RIGHT:
				rightValue = startValue;
				switch (rowOrder) {
				case FROM_BOTTOM:
					for (int row = height - 1; row >= 0; row--) {
						for (int col = width - 1; col >= 0; col--) {
							data[col][row] = rightValue;
							limitValues();
						}
					}
					break;
				case FROM_CENTER:
					break;
				case FROM_TOP:
					for (int row = 0; row < height; row++) {
						for (int col = width - 1; col >= 0; col--) {
							data[col][row] = rightValue;
							limitValues();
						}
					}
					break;
				}
				break;
			}
		} else {
			// VERTICAL
			switch (columnOrder) {
			case FROM_CENTER:
				if (width % 2 != 0) {
					rightCol++;
					// centralCol
					if (height % 2 != 0) {
						downLine++;
						// central line
						data[centerCol][centerLine] = startValue;
						// rest of lines
						limitValues();
						for (int row = 0; row < height / 2; row++) {
							data[centerCol][downLine + row] = rightValue;
							data[centerCol][upLine - row] = leftValue;
							limitValues();
						}
					} else {
						// no central line
						limitValues();
						for (int row = 0; row < height / 2; row++) {
							data[centerCol][downLine + row] = rightValue;
							data[centerCol][upLine - row] = leftValue;
							limitValues();
						}
					}
				}
				// rest of cols
				for (int col = 0; col < width / 2; col++) {
					for (int row = 0; row < height; row++) {
						data[leftCol - col][(height - 1) - row] = leftValue;
						data[rightCol + col][row] = rightValue;
						limitValues();
					}
				}
				break;
			case FROM_LEFT:
				rightValue = startValue;
				switch (rowOrder) {
				case FROM_BOTTOM:
					for (int col = 0; col < width; col++) {
						for (int row = height - 1; row >= 0; row--) {
							data[col][row] = rightValue;
							limitValues();
						}
					}
					break;
				case FROM_CENTER:
					break;
				case FROM_TOP:
					for (int col = 0; col < width; col++) {
						for (int row = 0; row < height; row++) {
							data[col][row] = rightValue;
							limitValues();
						}
					}
					break;
				}
				break;
			case FROM_RIGHT:
				rightValue = startValue;
				switch (rowOrder) {
				case FROM_BOTTOM:
					for (int col = width - 1; col >= 0; col--) {
						for (int row = height - 1; row >= 0; row--) {
							data[col][row] = rightValue;
							limitValues();
						}
					}
					break;
				case FROM_CENTER:
					break;
				case FROM_TOP:
					for (int col = width - 1; col >= 0; col--) {
						for (int row = 0; row < height; row++) {
							data[col][row] = rightValue;
							limitValues();
						}
					}
					break;
				}

				break;
			}
		}
	}

	public ColumnOrder getColumnOrder() {
		return columnOrder;
	}

	public void setColumnOrder(ColumnOrder columnOrder) {
		this.columnOrder = columnOrder;
		update();
	}

	public RowOrder getRowOrder() {
		return rowOrder;
	}

	public void setRowOrder(RowOrder rowOrder) {
		this.rowOrder = rowOrder;
		update();
	}

	public float getGap() {
		return gap;
	}
}