class Switch extends Button {
	SwitchListener switchListener;
	boolean pressed;
	Switch() {
		this(0, 0, 1, 1);
	}
	Switch(Point location, Size size) {
		this(location.x, location.y, size.w, size.h);
	}
	Switch(int x, int y, int w, int h) {
		super(x, y, w, h);
		this.normalColor = color(100, 100, 100);
		this.pressedColor = color(200, 200, 200);
		setColor(normalColor);
		setPressed(false);
	}
	void setPressed(boolean pressed) {
		this.pressed = pressed;
		if (pressed) {
			setColor(pressedColor);
		}
		else {
			setColor(normalColor);
		}
	}
	boolean isPressed() {
		return pressed;
	}
	void setSwitchListener(SwitchListener switchListener) {
		this.switchListener = switchListener;
	}
	boolean onMouseReleased(Box box, int x, int y) {
		if (pressed) {
			setColor(pressedColor);
		}
		else {
			setColor(normalColor);
		}
		if (switchListener!= null) {
			return switchListener.onChange(pressed);
		}
		return true;
	}
	boolean onMouseDragged(Box box, int lastX, int lastY, int x, int y) {
		setColor(normalColor);
		return super.onMouseDragged(this, lastX, lastY, x, y);
	}
	boolean onMousePressed(Box box, int x, int y) {
		setPressed(!isPressed());
		if (pressed) {
			setColor(pressedColor);
		}
		else {
			setColor(normalColor);
		}
		return true;
	}
}
