interface NumberKeyboardListener {
	void numberSelected(int n);
}
class NumberKeyboard extends Plane {
	NumberKeyboardListener numberKeyboardListener; 
	int selected;
	int firstX, firstY;
	boolean allClean;
	Switch[] switches;
	NumberKeyboard(){
		super(0,0,2,2);
		switches = new Switch[4];
		switches[0] =new Switch(0,0,1,1);
		switches[1] =new Switch(1,0,1,1);
		switches[2] =new Switch(1,1,1,1);
		switches[3] =new Switch(0,1,1,1);
		add(switches[0]);
		add(switches[1]);
		add(switches[2]);
		add(switches[3]);
		selected = 0;
		allClean=true;
	}
	void setNumberKeyboardListener(NumberKeyboardListener numberKeyboardListener){
		this.numberKeyboardListener = numberKeyboardListener;
	}
	int getOnePointNumber(int x, int y){
		if(x == 0){
			if(y == 0){
				return 1;
			}else{
				return 7;
			}
		}else{
			if(y == 0){
				return 3;
			}else{
				return 5;
			}
		}
	}

	void activateSwitches(){
		if(selected == 9){
			for(int n=0;n<4; n++){
				switches[n].setPressed(true);
			}
		}else{
			for(int n=0;n<4; n++){
				switches[n].setPressed(false);
			}

			if(selected == 8 || selected == 1 || selected == 2){
				switches[0].setPressed(true);
			}
			if(selected == 2 || selected == 3 || selected == 4){
				switches[1].setPressed(true);
			}
			if(selected == 4 || selected == 5 || selected == 6){
				switches[2].setPressed(true);
			}
			if(selected == 6 || selected == 7 || selected == 8){
				switches[3].setPressed(true);
			}
		}
	}
	boolean onMousePressed(Box b, int x, int y) {
		firstX=x;
		firstY=y;
		selected = getOnePointNumber(x,y);
		activateSwitches();
		if(numberKeyboardListener != null){
			numberKeyboardListener.numberSelected(selected);
		}
		return true; 
	}
	boolean onMouseReleased(Box b, int x, int y) {
		if(x == firstX && y == firstY){
			selected = getOnePointNumber(x,y);
			activateSwitches();
			if(numberKeyboardListener != null){
				numberKeyboardListener.numberSelected(selected);
			}
		}
		return true;
	}    
	boolean onMouseDragged(Box b, int oldX, int oldY, int x, int y) {
		if(firstX != x && firstY != y){
			//Diagonal
			if(allClean){
				allClean=false;
				selected= 9;
			}else{
				allClean=true;
				selected = 0;
			}
		}else{
			//Horizontal
			if(firstX != x){
				if(y == 0){
					selected = 2;
				}else{
					selected = 6;
				}
				//Vertical
			}else{
				if(x == 0){
					selected = 8;
				}else{
					selected = 4;
				}
			}
		}
		activateSwitches();
		if(numberKeyboardListener != null){
			numberKeyboardListener.numberSelected(selected);
		}
		return true;
	}
}
