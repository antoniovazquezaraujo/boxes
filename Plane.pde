import java.util.Vector;
class Plane extends Box {
	final static int NONE=0;
	final static int VERTICAL=1;
	final static int HORIZONTAL=2;
	int layoutType;
	Vector<Box> boxes;
	Plane() {
		this(0, 0, 8, 8);
	}
	Plane(Point location, Size size) {
		this(location.x, location.y, size.w, size.h);
	}
	Plane(int x, int y, int w, int h) {
		this(x,y,w,h,NONE);
	}
	Plane(int x, int y, int w, int h, int layoutType) {
		super(x, y, w, h);
		colorGradient = new LinearGradient(new Point(3,0), new Point(3,7), color(155,155,255), color(0,0,255), false);
		boxes = new Vector<Box>();
		this.layoutType= layoutType;
	}
	void layout(Box box){
		if(layoutType != NONE){
			if(layoutType == VERTICAL){
				Rectangle r = getSize(VERTICAL);
				box.rect.x = 0;
				box.rect.y = r.h;
				//box.rect.h = box.preferredHeight();
				rect.h += box.rect.h;
				rect.w = r.w;
			}else{
				Rectangle r = getSize(HORIZONTAL);
				box.rect.x = r.w;
				box.rect.y = 0;
				//box.rect.w = box.preferredWidth();
				rect.w += box.rect.w;
				rect.h = r.h;
			}
		}
	}
	void add(Box box ) {
		layout(box);
		boxes.add(box);
	}
	void add(int pos, Box box ) {
		layout(box);
		boxes.add(pos, box);
	}
	void setLayoutType(int layoutType){
		this.layoutType = layoutType;
	}
	Rectangle getSize(int axis){
		Rectangle r = new Rectangle(0,0,0,0);
		if(axis == VERTICAL){
			int maxW = 0;
			for(Box b: boxes){
				r.h+=b.rect.h;
				if(b.rect.w > maxW){
					maxW = b.rect.w;
				}
				r.w = maxW;
			}
		}else{
			int maxH = 0;
			for(Box b: boxes){
				r.w+=b.rect.w;
				if(b.rect.h > maxH){
					maxH = b.rect.h;
				}
				r.h = maxH;
			}
		}
		return r;
	}
	void clear() {
		boxes.clear();
	}
	void remove(Box box) {
		boxes.remove(box);
	}
	void update() {
		//Creo que no hace falta esto. Comprobar bien!
		//super.update();
		for (Box box: boxes) {
			box.update();
		}
	}
	Box boxAtPos(int x, int y){
		return boxAtPos(new Point(x,y)); 
	}
	Box boxAtPos(Point p) {
		//for (Box box: boxes) {
		//Mejor al revés
		for (int n= boxes.size()-1; n>=0; n--) {
			Box box = boxes.get(n);
			if (p.isInside(box)) {
				return box;
			}
		}  
		return null;
	}
	boolean onMouseReleased(Box box, int x, int y) {
		Box b = boxAtPos(new Point(x, y));
		if (b!= null) {
			if (b.onMouseReleased(this, x-b.rect.x, y-b.rect.y)) {
				return true;
			}
		}
		return super.onMouseReleased(this, x, y);
	}
	boolean onMouseDragged(Box box, int lastX, int lastY, int x, int y) {
		Box b = boxAtPos(new Point(x, y));
		if (b!= null) {
			if (b.onMouseDragged(this, lastX-b.rect.x, lastY-b.rect.y, x-b.rect.x, y-b.rect.y)) {
				return true;
			}
		}
		return super.onMouseDragged(this, lastX, lastY, x, y);
	}
	boolean onMousePressed(Box box, int x, int y) {
		Box b = boxAtPos(new Point(x, y));
		if (b!= null) {
			if (b.onMousePressed(this, x-b.rect.x, y-b.rect.y)) {
				return true;
			}
		}
		return super.onMousePressed(this, x, y);
	}
	boolean onMouseDoubleClicked(Box box, int x, int y) {
		Box b = boxAtPos(new Point(x, y));
		if (b!= null) {
			if (b.onMouseDoubleClicked(this, x-b.rect.x, y-b.rect.y)) {
				return true;
			}
		}
		return super.onMouseDoubleClicked(this, x, y);
	}
	boolean onMouseRepeated(Box box, int x, int y) {
		Box b = boxAtPos(new Point(x, y));
		if (b!= null) {
			if (b.onMouseRepeated(box, x-b.rect.x, y-b.rect.y)) {
				return true;
			}
		}
		return super.onMouseRepeated(this, x, y);
	}  
	void draw(GraphicContext context) {
		super.draw(context);
		for (Box box: boxes) {
			GraphicContext deep = context.create(context.clip.x, context.clip.y, context.clip.w, context.clip.h);
			deep.clip(context.clip.x+box.rect.x, context.clip.y+box.rect.y, box.rect.w, box.rect.h);
			deep.setColor(box.fillColor);
			deep.setAlfa(box.alfa);
			box.draw(deep);
		}
	}
	void onClose(Box b){
		//una box avisa de que desea salir.
	}
}
