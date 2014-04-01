class SystemScroller extends Plane implements ButtonListener{
	Button left, right, down;
	System system;
	SystemScroller(System system){
		super(0,0,8,8);
		setColor(color(100, 100, 100));
		setAlfa(100);
		this.system = system;
		left = new Button(0,0,1,8, color(255,0,0), color(255,100,100));
		left.setAlfa(100);
		left.setButtonListener(this);
		add(left);
		right = new Button(7,0,1,8, color(255,0,0), color(255,100,100));
		right.setAlfa(100);
		right.setButtonListener(this);
		add(right);
		down = new Button(0,7,8,1, color(255,0,0), color(255,100,100));
		down.setAlfa(100);
		down.setButtonListener(this);
		add(down);
	}
	boolean onClick(Button b){
		if(b == left){
			system.left();
		}else if(b == right){
			system.right();
		}else{
			system.down();
		}
		return true;
	}
}
class System extends Plane {
	final static int SELECTING=0;
	final static int RUNNING  =1;
	SystemScroller scroller;
	int mode;
	Box selectedPlane;
	int selectedPos;
	Vector<Box> planes;
	System(){
		super(0,0,8,8);
		scroller= new SystemScroller(this);
		selectedPos = -1;
		selectedPlane= null;
		mode = SELECTING;
		planes = new Vector<Box>();
		reloadPlanes();
	}
	void reloadPlanes(){
		clear();
		super.add(scroller);
		if(selectedPlane != null){
			super.add(0,selectedPlane);
		}
	}
	void add(Box b){
		planes.add(b);
		b.setContainer(this);
	}
	void left(){
		selectedPos--;
		if(selectedPos <0){
			selectedPos = 0;
		}
		if(selectedPos != -1){
			selectedPlane = planes.get(selectedPos);
		}
		reloadPlanes();
	}
	void right(){
		selectedPos++;
		if(selectedPos >= planes.size()){
			selectedPos = planes.size()-1;
		}
		if(selectedPos != -1){
			selectedPlane = planes.get(selectedPos);
		}
		reloadPlanes();
	}
	void down(){
		if(selectedPos != -1){
			selectedPlane = planes.get(selectedPos);
			if(selectedPlane != null){
				remove(scroller);
				mode = RUNNING;
			}
		}
	}
	void onClose(Box s){
		mode = SELECTING;
		clear();		
		reloadPlanes();
	}
	void exit(){
	}
}
