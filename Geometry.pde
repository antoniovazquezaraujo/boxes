color randomColor(){
	return color(random(255), random(255), random(255));
}
Point randomPoint(){
	return new Point(-8+(int)random(16), -8+(int)random(16));
}
Size randomSize(){
	return new Size((int)random(16), (int)random(16));
}
class Rectangle {
	int x, y, w, h;
	Rectangle(int x, int y, int w, int h) {
		set(x, y, w, h);
	}
	void set(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	void reSize(Size s){
		resize(s.w, s.h);
	}
	void resize(int w, int h){
		this.w=w;
		this.h=h;
	}
	void clip(int w, int h) {
		this.w = min(this.w, w);
		this.h = min(this.h, h);
	}
	void clip(Size size) {
		clip(size.w, size.h);
	}
	void add(Rectangle r){
		this.w += r.w;
		this.h += r.h;
	}
	void translate(int x, int y) {
		this.x+=x;
		this.y+=y;
	}
	void translate(Point p) {
		translate(p.x, p.y);
	}
	// getters de servicio, realmente 
	// guardamos x,y,w y h directamente
	Point getLocation() {
		return new Point(x, y);
	}
	void setLocation(Point p) {
		x=p.x;
		y=p.y;
	}
	Size getSize() {
		return new Size(w, h);
	}
	void setSize(Size size) {
		w=size.w;
		h=size.h;
	}
}
class Point {
	int x, y;
	Point(int x, int y) {
		this.x=x;
		this.y=y;
	}
	boolean isInside(Box b) {
		return isInside(b.rect.x, b.rect.y, b.rect.w, b.rect.h);
	}
	boolean isInside(int x, int y, int w, int h) {
		return (
				(  this.x >= x) 
				&&(this.y >= y)
				&&(this.x < x+w)
				&&(this.y < y+h)
			   );
	}
}
class Size {
	int w, h;
	Size(int w, int h) {
		this.w=w;
		this.h=h;
	}
}
