Box randomBox(){
	return new Box(0,0,(int)random(8), (int)random(8));
}
class Box {
	Plane container;
	Rectangle rect;
	MouseListener mouseListener;
	color fillColor;
	ColorGradient colorGradient;
	int alfa;
	BatchCommand batch;
	Box() {
		this(0, 0, 1, 1);
	}
	Box(Point location, Size size) {
		this(location.x, location.y, size.w, size.h);
	}
	Box(int x, int y, int w, int h) {
		rect = new Rectangle(x,y,w,h);
		batch = new BatchCommand();
		batch.setBox(this);
		colorGradient = new LinearGradient(new Point(3,0), new Point(3,7), color(255,155,155), color(255,0,0), false);
		this.fillColor = color(255, 255, 255);
		this.colorGradient = null;
		this.alfa = 255;
	}
	void setAlfa(int alfa){
		this.alfa = alfa;
	}
	void setContainer(Plane container){
		this.container= container;
	}
	void setColor(color c) {
		fillColor = c;
	}
	color getColor(){
		return fillColor;
	}
	void setColorGradient(ColorGradient colorGradient){
		this.colorGradient = colorGradient;
	}
	void addCommand(Command command) {
		command.setBox(this);
		batch.addCommand(command);
	}
	void update() {
		println("update en box");
		if (!batch.isFinished()) {
			println("box batch update no finished");
			batch.update();
		}
		else {
			println("box batch reset porque finished");
			batch.reset();
		}
	}

	void setMouseListener(MouseListener mouseListener) {
		this.mouseListener = mouseListener;
	}
	boolean onMouseReleased(Box b, int x, int y) {
		if (mouseListener != null) {
			if (mouseListener.onMouseReleased(this, x, y)) {
				return true;
			}
		}
		return false;
	}
	boolean onMouseDragged(Box b, int lastX, int lastY, int x, int y) {
		if (mouseListener != null) {
			if (mouseListener.onMouseDragged(this, lastX, lastY, x, y)) {
				return true;
			}
		}
		return false;
	}
	boolean onMousePressed(Box b, int x, int y) {
		if (mouseListener != null) {
			if (mouseListener.onMousePressed(this, x, y)) {
				return true;
			}
		}
		return false;
	}
	boolean onMouseDoubleClicked(Box box, int x, int y) {
		if (mouseListener != null) {
			if (mouseListener.onMouseDoubleClicked(this, x, y)) {
				return true;
			}
		}
		if(container!= null){
			container.onClose(this);
			return true;
		}
		return false;
	}
	boolean onMouseRepeated(Box b, int x, int y) {
		if (mouseListener != null) {
			if (mouseListener.onMouseRepeated(this, x, y)) {
				return true;
			}
		}
		return false;
	}
	void draw(GraphicContext context) {
		context.setColor(fillColor);
		context.setAlfa(alfa);
		if(colorGradient != null){
			context.paintBox(rect.w, rect.h, colorGradient);
		}else{
			context.paintBox(rect.w, rect.h);
		}
	}
}
