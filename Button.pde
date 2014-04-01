class Button extends Box {
	ButtonListener buttonListener;
	color normalColor, pressedColor; 

	Button() {
		this(0, 0, 1, 1);
	}
	Button(Point location, Size size) {
		this(location.x, location.y, size.w, size.h);
	}
	Button(int x, int y, int w, int h) {
		this(x,y,w,h,color(100, 100, 100), color(200, 200, 200));
	}
	Button(int x, int y, int w, int h, color normalColor, color pressedColor) {
		super(x, y, w, h);
		this.normalColor = normalColor; 
		this.pressedColor = pressedColor; 
		setColor(normalColor);
	}
	void setNormalColor(color normalColor){
		this.normalColor = normalColor; 
	}
	void setPressedColor(color pressedColor){
		this.pressedColor = pressedColor; 
	}
	void setButtonListener(ButtonListener buttonListener) {
		this.buttonListener= buttonListener;
	}
	boolean onMouseReleased(Box b, int x, int y) {
		setColor(normalColor);
		if (buttonListener!= null) {
			if (buttonListener.onClick(this)) {
				return true;
			}
		}
		return super.onMouseReleased(this, x, y);
	}
	boolean onMouseDragged(Box b, int lastX, int lastY, int x, int y) {
		setColor(normalColor);
		return super.onMouseDragged(this, lastX, lastY, x, y);
	}
	boolean onMousePressed(Box b, int x, int y) {
		setColor(pressedColor);
		return super.onMousePressed(this, x, y);
	}
}
