class Canvas extends Plane implements ColorChooserListener {
	boolean colorChooserVisible;
	color paintColor;
	ColorChooser colorChooser;
	Canvas() {
		paintColor = color(0, 0, 0);
		colorChooserVisible = false;
		colorChooser = new ColorChooser();
		colorChooser.setColorChooserListener(this);
	}
	boolean onMouseDragged(Box box, int lastDraggedX, int lastDraggedY, int x, int y) {
		paintBox(x, y);
		return true;
	}

	boolean onMousePressed(Box box, int  x, int y) {
		if (!colorChooserVisible) {
			paintBox(x, y);
			return true;
		}else{
			return super.onMousePressed(box, x,y);
		}
	}
	boolean onMouseRepeated(Box box, int x, int y) {
		if (!colorChooserVisible) {
			colorChooserVisible = true;
			colorChooser.setPosition(x,y);
			Box b = boxAtPos(x, y);
			if(b!= null){
				colorChooser.setOriginalColor(b.getColor());
			}else{
				colorChooser.setOriginalColor(paintColor);
			}
			add(colorChooser);
			return true;
		}else{
			return super.onMouseRepeated(box, x,y);
		}
	}
	boolean onMouseReleased(Box box, int x, int y) {
		if (!colorChooserVisible) {
			paintBox(x, y);
			return true;
		}else{
			return super.onMouseReleased(box, x,y);
		}
	}
	void paintBox(int x, int y) {
		Box b = boxAtPos(x, y);
		if (b != null) {
			if(b != colorChooser){
				b.setColor(paintColor);
			}
		}
		else {
			Box nueva = new Box(x, y, 1, 1);
			nueva.setColor(paintColor);
			add(nueva);
		}
	}
	void accepted(ColorChooser c) {
		paintColor = c.getSelectedColor();
		remove(colorChooser);
		colorChooserVisible = false;
	}
	void canceled(ColorChooser c) {
		remove(colorChooser);
		colorChooserVisible = false;
	}
}
