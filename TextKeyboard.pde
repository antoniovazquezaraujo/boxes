interface CharButtonListener {
	void charShowed(char c);
	void charSelected(char c);
	void canceled();
}
class CharButton extends Button {
	char[]chars; 
	int selected;
	CharButtonListener charButtonListener;
	CharButton(char left, char right) {
		chars = new char[2];
		chars[0] =left;
		chars[1] = right;
		selected = 0;
	}
	void setCharButtonListener(CharButtonListener charButtonListener) {
		this.charButtonListener = charButtonListener;
	}
	boolean onMouseReleased(Box b, int x, int y) {
		if (charButtonListener != null) {
			charButtonListener.charSelected(chars[selected]);
		}
		return true;
	}
	boolean onMouseDragged(Box b, int oldX, int oldY, int x, int y) {
		if(x > oldX){
			selected = 1;
		}
		if(x < oldX){
			selected = 0;
		}
		if (charButtonListener != null) {
			if(y != oldY){
				charButtonListener.canceled();
			}else{
				charButtonListener.charShowed(chars[selected]);
			}
		}
		return true;
	}
	boolean onMousePressed(Box b, int x, int y) {
		selected = 0;
		if (charButtonListener != null) {
			charButtonListener.charShowed(chars[selected]);
		}
		return true;
	}
}
interface TextKeyboardListener {
	void charShowed(char c);
	void charSelected(char c);
	void canceled();
}
class TextKeyboard extends Plane implements CharButtonListener{
	final int NUM_KEYS = 39; // 27+ space + nums + delete
	CharButton[] keys;
	CharButton lastCharButtonPressed;
	TextKeyboardListener keyListener;
	TextKeyboard() {
		super(new Point(0,0), new Size(8, 8));
		keys = new CharButton[NUM_KEYS];
		keys[0] = new CharButton('Q', 'W');
		keys[1] = new CharButton('E', 'R');
		keys[2] = new CharButton('T', 'Y');
		keys[3] = new CharButton('U', 'I');
		keys[4] = new CharButton('O', 'P');
		for (int n=0; n<5; n++) {
			add(keys[n], n, 0);
			keys[n].setCharButtonListener(this);
		}
	}
	void setTextKeyboardListener(TextKeyboardListener keyListener){
		this.keyListener = keyListener;
	}
	void add(CharButton keyButton, int x, int y){
		add(keyButton);
		keyButton.rect.x = x;
		keyButton.rect.y = y;
	}
	void charShowed(char c) {
		if(keyListener != null){
			keyListener.charShowed(c);
		}
	}
	void charSelected(char c) {
		if(keyListener != null){
			keyListener.charSelected(c);
		}
	}
	void canceled() {
		if(keyListener != null){
			keyListener.canceled();
		}
	}
	boolean onMousePressed(Box box, int  x, int y) {
		Box b = boxAtPos(x, y);
		if (b != null) {
			lastCharButtonPressed = (CharButton)b;
			return b.onMousePressed(this, x, y);
		}
		return true; 
	}
	boolean onMouseReleased(Box b, int x, int y) {
		if(lastCharButtonPressed != null){
			CharButton k = lastCharButtonPressed;
			lastCharButtonPressed = null;
			return k.onMouseReleased(this, x, y);
		}
		return true; 
	}    
}
