class TextEditor extends Plane implements TextKeyboardListener, NumberKeyboardListener{
	TextKeyboard textKeyboard;
	NumberKeyboard numberKeyboard;
	String text;
	Char[]chars;
	TextEditor() {
		super(new Point(0,0), new Size(8,8));
		chars = new Char[2];
		chars[0]=new Char(0,0);
		chars[1]=new Char(5,0);
		add(chars[0]);
		add(chars[1]);

		textKeyboard = new TextKeyboard();
		add(textKeyboard);
		textKeyboard.setTextKeyboardListener(this);

		numberKeyboard = new NumberKeyboard();
		add(numberKeyboard);
		numberKeyboard.setNumberKeyboardListener(this);
	}
	public void charShowed(char c) {
		chars[0].setChar(c);	
	}
	public void charSelected(char c) {
		chars[0].setChar(c);	
	}
	public void canceled() {
		chars[0].setChar('X');	
	}
	void numberSelected(int n){
		chars[0].setNumber(n);	
	}
}

