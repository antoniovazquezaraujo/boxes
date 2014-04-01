interface RotatorListener {
	void numberSelected(int n);
}
class Rotator extends Plane {
	final static int NONE  =0;
	final static int UP    =1;
	final static int DOWN  =2;
	final static int TO_MIN=3;
	final static int TO_MAX=4;
	RotatorListener rotatorListener; 
	int action;
	int value;
	int minValue, maxValue;
	Switch[] switches;
	Rotator(int minValue, int maxValue, int value) {
		super(0, 0, 2, 2);
		switches = new Switch[4];
		switches[0] =new Switch(0, 0, 1, 1);
		switches[1] =new Switch(1, 0, 1, 1);
		switches[2] =new Switch(1, 1, 1, 1);
		switches[3] =new Switch(0, 1, 1, 1);
		add(switches[0]);
		add(switches[1]);
		add(switches[2]);
		add(switches[3]);
		this.minValue = minValue;
		this.maxValue = maxValue;
		this.value = value;
		rotateColors();
	}
	void setRotatorListener(RotatorListener rotatorListener) {
		this.rotatorListener = rotatorListener;
	}

	void rotateColors() {
		int rotation = value % 4;
		for (int n=0; n<4; n++) {
			switches[3-n].setColor(128/(1+rotation));
			rotation++;
			rotation %=4;
		}
	}
	boolean onMousePressed(Box b, int x, int y) {
		return true;
	}
	boolean onMouseReleased(Box b, int x, int y) {
		return true;
	}    
	boolean onMouseDragged(Box b, int oldX, int oldY, int x, int y) {
		action = NONE;
		if (oldX != x && oldY != y) {
			//Diagonal
			if (oldX < x && oldY > y) {
				action = TO_MAX;
			}
			else {
				action = TO_MIN;
			}
		}
		else if (oldY == y) {
			//Horizontal
			if (oldX < x) {
				if (y == 0) {
					action=UP;
				}
				else {
					action=DOWN;
				}
			}
			else if (oldX > x) {
				if (y == 0) {
					action=DOWN;
				}
				else {
					action=UP;
				}
			}
		}
		else {
			if (oldY < y) {
				if (x == 0) {
					action = DOWN;
				}
				else {
					action = UP;
				}
			}
			else if (oldY > y) {
				if (x == 0) {
					action = UP;
				}
				else {
					action = DOWN;
				}
			}
		}
		switch(action) {
			case UP: 
				if (value<maxValue)value++; 
				break;
			case DOWN: 
				if (value > minValue)value--; 
				break;
			case TO_MIN: 
				value = minValue; 
				break;
			case TO_MAX: 
				value = maxValue; 
				break;
		}
		rotateColors();

		if (rotatorListener != null) {
			rotatorListener.numberSelected(value);
		}
		return true;
	}
}

