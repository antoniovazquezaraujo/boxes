package com.formatic.boxes.widgets;

import com.formatic.boxes.widgets.events.BlockSelectorListener;
import com.formatic.boxes.widgets.events.BoxEventListener;

public class BlockSelector extends BoxContainer {
	BlockSelectorListener blockSelectorListener;
	int selected;
	int firstX, firstY;
	boolean allClean;
	Switch[] switches;

	public BlockSelector() {
		super(2, 3);
		switches = new Switch[6];
		switches[0] = new Switch(0, 0, 1, 1);
		switches[1] = new Switch(1, 0, 1, 1);
		switches[2] = new Switch(0, 1, 1, 1);
		switches[3] = new Switch(1, 1, 1, 1);
		switches[4] = new Switch(0, 2, 1, 1);
		switches[5] = new Switch(1, 2, 1, 1);
		add(switches[0]);
		add(switches[1]);
		add(switches[2]);
		add(switches[3]);
		add(switches[4]);
		add(switches[5]);
		selected = 0;
		allClean = true;
		setBoxEventListener(new BoxEventListener() {
			@Override
			public boolean onZoomOut(int x, int y) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean onTouchCancel(int x, int y) {
				// Ojo, importante devolver true para que al soltar
				// no se apague el último switch automáticamente
				return true;
			}

			@Override
			public boolean onTouch(int x, int y) {
				firstX = x;
				firstY = y;
				selected = getOnePointNumber(x, y);
				activateSwitches();
				if (blockSelectorListener != null) {
					updateListener();
				}
				return true;
			}

			@Override
			public boolean onRelease(int x, int y) {
				if (x == firstX && y == firstY) {
					selected = getOnePointNumber(x, y);
					activateSwitches();
					if (blockSelectorListener != null) {
						updateListener();
					}
				}
				return true;
			}

			private void updateListener() {
				//selected = 1 - 9
				int block = (selected-1) % 3;
				int line = (selected-1) / 3;
				blockSelectorListener.blockSelected(line, block);
			}

			@Override
			public boolean onDrag(int x, int y, int newX, int newY) {
				if (firstX != newX && firstY != newY) {
					// Diagonal

				} else {
					// Horizontal
					if (firstX != newX) {
						switch (newY) {
						case 0:
							selected = 2;
							break;
						case 1:
							selected = 5;
							break;
						case 2:
							selected = 8;
							break;
						}

					} else {
						// Vertical

					}
				}
				activateSwitches();
				if (blockSelectorListener != null) {
					updateListener();
				}
				return true;
			}
		});
	}

	public void setBlockSelectorListener(BlockSelectorListener blockSelectorListener) {
		this.blockSelectorListener = blockSelectorListener;
	}

	public int getOnePointNumber(int x, int y) {
		switch (y) {
		case 0:
			switch (x) {
			case 0:
				return 1;
			case 1:
				return 3;
			}
		case 1:
			switch (x) {
			case 0:
				return 4;
			case 1:
				return 6;
			}
		case 2:
			switch (x) {
			case 0:
				return 7;
			case 1:
				return 9;
			}
		default:
			return 1;
		}
	}

	public void activateSwitches() {
		for (int n = 0; n < 6; n++) {
			switches[n].setPressed(false);
		}
		if (selected == 1 || selected == 2) {
			switches[0].setPressed(true);
		}
		if (selected == 2 || selected == 3) {
			switches[1].setPressed(true);
		}
		if (selected == 4 || selected == 5) {
			switches[2].setPressed(true);
		}
		if (selected == 5 || selected == 6) {
			switches[3].setPressed(true);
		}
		if (selected == 7 || selected == 8) {
			switches[4].setPressed(true);
		}
		if (selected == 8 || selected == 9) {
			switches[5].setPressed(true);
		}
	}
}
