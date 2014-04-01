class RepeaterButtonListener {
	void valueChanged(RepeaterButton button, int value) {
	}
}
class RepeaterButton extends Box {
	int value, minValue, maxValue;
	int increment;
	boolean incrementing;
	RepeaterButtonListener repeaterButtonListener;
	RepeaterButton(int minValue, int maxValue) {
		this(minValue, maxValue, 0, 0, 1, 1);
	} 
	RepeaterButton() {
		this(0, 255, 0, 0, 1, 1);
	}
	RepeaterButton(int minValue, int maxValue, int x, int y, int w, int h) {
		super(x, y, w, h);
		this.minValue = minValue;
		this.maxValue = maxValue;
		value=minValue;
		increment = 1;
		incrementing = true;
	}
	int getValue(){
		return value;
	}
	void setValue(int value){
		this.value = value;
	}
	void setRepeaterButtonListener(RepeaterButtonListener listener){
		this.repeaterButtonListener = listener;
	}
	boolean onMousePressed(Box box, int x, int y) {
		if (incrementing) {
			value+=increment;
		}
		else {
			value-=increment;
		}
		if (value > maxValue) value = maxValue;
		if (value < minValue) value = minValue;
		if (repeaterButtonListener != null) {
			repeaterButtonListener.valueChanged(this, value);
		}
		return true;//super.onMousePressed(x,y);
	}
	boolean onMouseRepeated(Box box, int x, int y) {
		increment ++;
		return onMousePressed(box, x, y);
	}
	boolean onMouseReleased(Box box, int x, int y) {
		incrementing= !incrementing;
		increment = 1;
		return true;
	}
}
