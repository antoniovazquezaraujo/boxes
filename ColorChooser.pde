final int RED = 0;
final int GREEN = 1;
final int BLUE = 2;

interface ColorChooserListener {
	void accepted(ColorChooser c);
	void canceled(ColorChooser c);
}

color toner(color a, int tone, int newValue) {
	switch(tone) {
		case RED:  
			return color(newValue, green(a), blue(a));
		case GREEN:
			return color(red(a), newValue, blue(a));							  
		case BLUE: 
			return color(red(a), green(a), newValue);
	}
	return color(0, 0, 0);
}
color getColorTone(int colorTone, int value) {
	switch(colorTone) {
		case RED: 
			return color(value, 0, 0); 
		case GREEN: 
			return color(0, value, 0); 
		case BLUE: 
			return color(0, 0, value);
	}
	return color(0, 0, 0);
}
class ColorChooser extends Plane implements ColorToneListener{
	boolean firstClick;
	Box colorBox;
	color originalColor;
	int x, y;
	ColorChooserListener colorChooserListener;
	ColorToneChooser r, g, b;
	ColorChooser() {
		super(0, 0, 8, 8 );
		setColor(color(255,255,255));
		setAlfa(100);
		firstClick = true;

		r = new ColorToneChooser(RED);
		r.setColorToneListener(this);
		add(r);
		g = new ColorToneChooser(GREEN);
		g.setColorToneListener(this);
		add(g);
		b = new ColorToneChooser(BLUE);
		b.setColorToneListener(this);
		add(b);


		colorBox = new Box(0, 0, 1, 1);
		add(colorBox);
	}
	void setPosition(int x, int y){
		this.x = x;
		this.y = y;
		colorBox.rect.x = x;
		colorBox.rect.y = y;
		r.rect.x = g.rect.x = b.rect.x = x;
		if(y<=3){ //middle of screen
			r.rect.y = y+1;
			g.rect.y = y+2;
			b.rect.y = y+3;
		}else{
			r.rect.y = y-1;
			g.rect.y = y-2;
			b.rect.y = y-3;
		}
	}
	void setOriginalColor(color originalColor){
		this.originalColor = originalColor;
		colorBox.setColor(originalColor);
		r.setColor(originalColor);
		g.setColor(originalColor);
		b.setColor(originalColor);
	}
	void setColorChooserListener(ColorChooserListener colorChooserListener) {
		this.colorChooserListener = colorChooserListener;
	}
	void setSelectedColor(color c) {
		colorBox.setColor(c);
	}
	color getSelectedColor() {
		return colorBox.getColor();
	}
	void colorChanged(ColorToneChooser chooser, int colorTone) {
		if      (chooser == r) {
			colorBox.setColor(toner(colorBox.getColor(), RED, colorTone));
		}
		else if (chooser == g) {
			colorBox.setColor(toner(colorBox.getColor(), GREEN, colorTone));
		}
		else if (chooser == b) {
			colorBox.setColor(toner(colorBox.getColor(), BLUE, colorTone));
		}
	}
	boolean onMouseReleased(Box box, int x, int y){
		if(firstClick){
			firstClick=false;
			return true;
		}
		Box b = boxAtPos(x,y);
		if(b == null){
			if(colorChooserListener != null){
				colorChooserListener.canceled(this);
			}
			firstClick = true;
			return true;
		}else if(b == colorBox){
			if(colorChooserListener != null){
				colorChooserListener.accepted(this);
			}
			firstClick = true;
			return true;
		}
		firstClick = false;
		return super.onMouseReleased(box, x,y);
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
}
