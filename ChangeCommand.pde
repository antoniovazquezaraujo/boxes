class ChangeType {
	final static int FIXED=1;
	final static int GRADIENT=2;
}
Command commandFactory() {
	BatchCommand ret = new BatchCommand((int)random(10));
	int max = (int)random(50);
	Command command;
	for (int n = 0; n< max; n++) {
		command = randomChangeCommand();
		ret.addCommand(command);
	}
	return ret;
}
Command randomChangeCommand(){
	if(random(10) > 5){ //pos and size
		return new ChangeCommand((int)random(50), random(10), random(8), random(8), 0, (int)random(8));
	}else{
		return new ChangeCommand((int)random(50), random(10), random(255), random(255), 0, 9+(int)random(8));     
	}
}
class ChangeCommand extends Command {
	final static int LOCATION_X =0;
	final static int LOCATION_Y =1;
	final static int SIZE_W = 2;
	final static int SIZE_H = 3;
	final static int SIZE_W_AND_H =4;
	final static int GRADIENT_LOCATION_FROM_X=5;
	final static int GRADIENT_LOCATION_FROM_Y=6;
	final static int GRADIENT_LOCATION_TO_X=7;
	final static int GRADIENT_LOCATION_TO_Y=8;
	//Colors (0-255)
	final static int COLOR_R = 9;
	final static int COLOR_G = 10;
	final static int COLOR_B = 11;
	final static int GRADIENT_COLOR_FROM_R=12;
	final static int GRADIENT_COLOR_FROM_G=13;
	final static int GRADIENT_COLOR_FROM_B=14;
	final static int GRADIENT_COLOR_TO_R=15;
	final static int GRADIENT_COLOR_TO_G=16;
	final static int GRADIENT_COLOR_TO_B=17;
	float fromValue; 
	float actualValue;
	float toValue;
	float delta;
	float rangeTime;
	int step;
	int changeTarget;
	ChangeCommand(int times, float rangeTime, float fromValue, float toValue, int step, int changeTarget) {
		super(times);
		this.rangeTime = rangeTime;
		this.fromValue = fromValue;
		this.toValue = toValue;
		this.actualValue = fromValue;
		this.step = step;
		this.changeTarget = changeTarget;
		this.delta = (toValue-fromValue)/rangeTime;
	}
	void update() {
		if ((delta> 0 && actualValue < toValue) || (delta <0 && actualValue >= toValue)) {
			actualValue+=delta; 
			if ((step == 0) || (((int)(actualValue % step) == 0))) {
				switch(changeTarget) {
					case LOCATION_X:
						box.rect.x = (int)actualValue;
						break;
					case LOCATION_Y:
						box.rect.y = (int)actualValue;
						break;
					case SIZE_W_AND_H :
						box.rect.w = (int)actualValue; 
						box.rect.h = (int)actualValue; 
						break;
					case SIZE_W :
						box.rect.w = (int)actualValue; 
						break;
					case SIZE_H : 
						box.rect.h = (int)actualValue;
						break;
					case COLOR_R : 
						box.fillColor = toner(box.fillColor, RED, (int)actualValue);
						break;
					case COLOR_G : 
						box.fillColor = toner(box.fillColor, GREEN, (int)actualValue);
						break;
					case COLOR_B : 
						box.fillColor = toner(box.fillColor, BLUE, (int)actualValue);
						break;
					case GRADIENT_LOCATION_FROM_X: 
						box.colorGradient.from.x = (int)actualValue;
						break;
					case GRADIENT_LOCATION_FROM_Y: 
						box.colorGradient.from.y = (int)actualValue;
						break;
					case GRADIENT_LOCATION_TO_X:
						box.colorGradient.to.x = (int)actualValue; 
						break;
					case GRADIENT_LOCATION_TO_Y:
						box.colorGradient.to.y = (int)actualValue; 
						break;
					case GRADIENT_COLOR_FROM_R:
						box.colorGradient.fromColor = toner(box.colorGradient.fromColor, RED, (int)actualValue);
						break;
					case GRADIENT_COLOR_FROM_G: 
						box.colorGradient.fromColor = toner(box.colorGradient.fromColor, GREEN, (int)actualValue);
						break;
					case GRADIENT_COLOR_FROM_B:
						box.colorGradient.fromColor = toner(box.colorGradient.fromColor, BLUE, (int)actualValue);
						break;
					case GRADIENT_COLOR_TO_R: 
						box.colorGradient.toColor = toner(box.colorGradient.toColor, RED, (int)actualValue);
						break;
					case GRADIENT_COLOR_TO_G: 
						box.colorGradient.toColor = toner(box.colorGradient.toColor, GREEN, (int)actualValue);
						break;
					case GRADIENT_COLOR_TO_B: 
						box.colorGradient.toColor = toner(box.colorGradient.toColor, BLUE, (int)actualValue);
						break;
				}
			}
		}
		else {
			super.update();
			actualValue = fromValue;
		}
	}
	void reset() {
		actualValue = fromValue;
		super.reset();
	}

}

