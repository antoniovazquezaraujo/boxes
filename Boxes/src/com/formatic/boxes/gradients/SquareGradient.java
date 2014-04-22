package com.formatic.boxes.gradients;

import com.formatic.boxes.Color;
import com.formatic.boxes.Point;
import com.formatic.boxes.gradients.ColorGradient.Repeatable;
import com.formatic.boxes.gradients.ColorGradient.Target;

public class SquareGradient extends ColorGradient {

	enum Alignment {
		ROWS_AND_COLS, COLS_AND_ROWS
	}

	enum ColumnOrder {
		FROM_LEFT, FROM_RIGHT, FROM_CENTER
	}

	enum RowOrder {
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

	SquareGradient(Point startPoint, Point endPoint, float minValue,
			float maxValue) {
		this(startPoint, endPoint, minValue, maxValue, Target.BRIGHTNESS,
				Repeatable.NONE, 0.1f);
	}

	SquareGradient(Point startPoint, Point endPoint, float minValue,
			float maxValue, Target target, Repeatable repeatable, float gap) {
		super(startPoint, endPoint, minValue, maxValue, target, repeatable);
		this.width = (endPoint.x - startPoint.x);
		this.height = (endPoint.y - startPoint.y);
		this.minValue = minValue;
		this.maxValue = maxValue;
		this.startValue = minValue;
		this.gap = gap;
		this.columnOrder = ColumnOrder.FROM_LEFT;
		this.rowOrder = RowOrder.FROM_TOP;
		this.repeatable = repeatable;
		this.alignment = Alignment.ROWS_AND_COLS;
		update();

	}

	public void setGap(float gap) {
		this.gap = gap;
		update();
	}

	private void limitValues() {
		switch (repeatable) {
		case NONE:
			if (rightValue + rightGap >= maxValue) {
				rightGap = 0;
			}
			if (leftValue + leftGap < minValue) {
				leftGap = 0;
			}
			rightValue += rightGap;
			leftValue += leftGap;
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
			}else{
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
	protected void update() {
		int centerCol = width / 2;
		int centerLine = height / 2;
		int upLine = (height / 2) - 1;
		int downLine = (height / 2);
		int leftCol = (width / 2) - 1;
		int rightCol = (width / 2);
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
						data[centerLine][centerCol] = startValue;
						limitValues();
						// second: rest of cols of central line
						for (int col = 0; col < width / 2; col++) {
							data[centerLine][rightCol + col] = rightValue;
							data[centerLine][leftCol - col] = leftValue;
							limitValues();
						}
					} else {
						// no central col: all the cols of central line
						// toghether
						limitValues();
						for (int col = 0; col < width / 2; col++) {
							data[centerLine][rightCol + col] = rightValue;
							data[centerLine][leftCol - col] = leftValue;
							limitValues();
						}
					}
				}
				// rest of lines
				for (int row = 0; row < height / 2; row++) {
					for (int col = 0; col < width; col++) {
						data[upLine - row][(width - 1) - col] = leftValue;
						data[downLine + row][col] = rightValue;
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
							data[row][col] = rightValue;
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
							data[row][col] = rightValue;
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
							data[row][col] = rightValue;
							limitValues();
						}
					}
					break;
				case FROM_CENTER:
					break;
				case FROM_TOP:
					for (int row = 0; row < height; row++) {
						for (int col = width - 1; col >= 0; col--) {
							data[row][col] = rightValue;
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
						data[centerLine][centerCol] = startValue;
						// rest of lines
						limitValues();
						for (int row = 0; row < height / 2; row++) {
							data[downLine + row][centerCol] = rightValue;
							data[upLine - row][centerCol] = leftValue;
							limitValues();
						}
					} else {
						// no central line
						limitValues();
						for (int row = 0; row < height / 2; row++) {
							data[downLine + row][centerCol] = rightValue;
							data[upLine - row][centerCol] = leftValue;
							limitValues();
						}
					}
				}
				// rest of cols
				for (int col = 0; col < width / 2; col++) {
					for (int row = 0; row < height; row++) {
						data[(height - 1) - row][leftCol - col] = leftValue;
						data[row][rightCol + col] = rightValue;
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
							data[row][col] = rightValue;
							limitValues();
						}
					}
					break;
				case FROM_CENTER:
					break;
				case FROM_TOP:
					for (int col = 0; col < width; col++) {
						for (int row = 0; row < height; row++) {
							data[row][col] = rightValue;
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
							data[row][col] = rightValue;
							limitValues();
						}
					}
					break;
				case FROM_CENTER:
					break;
				case FROM_TOP:
					for (int col = width - 1; col >= 0; col--) {
						for (int row = 0; row < height; row++) {
							data[row][col] = rightValue;
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