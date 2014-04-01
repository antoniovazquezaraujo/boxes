class GraphicContext {
	//Rectangle base;
	Rectangle clip;
	color fillColor;
	int alfa;
	GraphicContext() {
		this(0, 0, 8, 8);
	}
	String toString() {
		return new String("["+clip.x+","+clip.y+"]("+clip.w+","+clip.h+")");
	}
	GraphicContext(int x, int y, int w, int h) {
		//this.base= new Rectangle(x, y, w, h);
		this.clip = new Rectangle(x, y, w, h);
		fillColor = color(0, 0, 0);
		alfa = 255;
	}
	GraphicContext create(int x, int y, int w, int h) {
		return new GraphicContext(x, y, w, h);
	}
	void reset(int x, int y, int w, int h) {
	//	base.set(x, y, w, h);
		clip.set(x, y, w, h);
	}
	void translate(int x, int y) {
		clip.translate(x, y);
	}
	void setClip(int x, int y, int w, int h) {
		clip.translate(x, y);
		clip.clip(w, h);
	}
	void clip(Rectangle rect){
		clip(rect.x, rect.y, rect.w, rect.h);
	}
	void clip(int x, int y, int w, int h){
		//if(x < clip.x){
		//	w -= clip.x-x;
		//	x =  clip.x;
		//}
		if(x+w > clip.x + clip.w){
			w -= (x+w) -(clip.x+clip.w);
		}
		clip.x = x;
		clip.w = w;
		//if(y < clip.y){
		//	h -= clip.y-y;
		//	y =  clip.y;
		//}
		if(y+h > clip.y + clip.h){
			h -= (y+h) -(clip.y+clip.h);
		}
		clip.y = y;
		clip.h = h;
	}

	void paintPixel(int x, int y) {
		fill(fillColor, alfa);
		if( (x>=0) && (x < clip.w) && (y>=0) && (y < clip.h)){
			rect((clip.x+x)*BOX_SIZE, (clip.y+y)*BOX_SIZE, BOX_SIZE, BOX_SIZE);
		}
	}
	void paintBox(int w, int h, ColorGradient colorGradient) {
		fill(fillColor, alfa);
		if(w > clip.w){
			w= clip.w;
		}
		if(h > clip.h){
			h= clip.h;
		}
		for (int col=0; col<w; col++) {
			for (int row=0; row<h; row++) {
				color theColor = colorGradient.getColor(new Point(clip.x+col, clip.y+row));
				fill(red(theColor), green(theColor), blue(theColor), alfa);
				rect((clip.x+col)*BOX_SIZE, (clip.y+row)*BOX_SIZE, BOX_SIZE, BOX_SIZE);
			}
		}
	}
	void paintBox(int w, int h) {
		fill(fillColor, alfa);
		if(w > clip.w){
			w= clip.w;
		}
		if(h > clip.h){
			h= clip.h;
		}
		for (int col=0; col<w; col++) {
			for (int row=0; row<h; row++) {
				rect((clip.x+col)*BOX_SIZE, (clip.y+row)*BOX_SIZE, BOX_SIZE, BOX_SIZE);
			}
		}
	}

	color getColor() {
		return fillColor;
	}

	void setColor(color fillColor) {
		this.fillColor = fillColor;
	}
	void setAlfa(int alfa) {
		this.alfa = alfa;
	}
	int getAlfa() {
		return alfa;
	}
}
