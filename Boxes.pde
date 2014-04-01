int SCREEN_SIZE;
int BOX_SIZE;
int DOUBLE_CLICK_TIME=200;
int MOUSE_REPEAT_INITIAL_TIME=500;
int MOUSE_REPEAT_TIME=30;
int mouseDelay=MOUSE_REPEAT_INITIAL_TIME;
Plane textPlane;
GraphicContext context;
int mouseTime=0;
int lastX, lastY;
int numScene=0;
System screen;

void setup() {
  //SCREEN_SIZE = min(displayHeight, displayWidth);
  SCREEN_SIZE = 600;
  size(SCREEN_SIZE, SCREEN_SIZE);
  //size(displayWidth, displayHeight);
  
  BOX_SIZE = SCREEN_SIZE/8;
  context = new GraphicContext(); 
  screen = new System();
  stroke(0); 
  strokeWeight(1);
  //frameRate(2);
  //noLoop();
  new Test();
}  
int x=1, y=1, w=3, h=3;
char theKey;
void keyPressed() {
}
void drawBackground() {
  fill(255);
  for (int col=0; col<8; col++) {
    for (int row=0; row<8; row++) {
      rect((int)(col)*BOX_SIZE, (row)*BOX_SIZE, BOX_SIZE, BOX_SIZE);
    }
  }
}
void draw() {
  screen.update();
  drawBackground();
  context.reset(0, 0, 8, 8);
  screen.draw(context);
  //Repetimos la pulsación de ratón si se estuvo quieto durante unos millis.
  //A partir de ahí, lo repetimos cada menos  millis.
  if (mousePressed) {
    if ( (millis() - mouseTime > mouseDelay) && (mouseX == lastX) && (mouseY == lastY)) {
      mouseDelay=MOUSE_REPEAT_TIME;
      mouseRepeated();
    }
  }
  else {
    mouseDelay=MOUSE_REPEAT_INITIAL_TIME;
  }
}

void mousePressed() {
  int actualMouseTime = millis();
  if (actualMouseTime - mouseTime < DOUBLE_CLICK_TIME) {
    mouseTime = actualMouseTime; 
    lastX = mouseX;
    lastY = mouseY;
    screen.onMouseDoubleClicked(null, mouseX/BOX_SIZE, mouseY/BOX_SIZE);
  }
  else {
    mouseTime = actualMouseTime; 
    lastX = mouseX;
    lastY = mouseY;
    screen.onMousePressed(null, mouseX/BOX_SIZE, mouseY/BOX_SIZE);
  }
}
void mouseRepeated() {
  screen.onMouseRepeated(null, mouseX/BOX_SIZE, mouseY/BOX_SIZE);
}

void mouseDragged() {  
  //Solo arrastramos si el movimiento es suficiente para llegar 
  //a la otra box. Si se mueve dentro de una box no se dispara.
  mouseTime= millis();
  if (lastX/BOX_SIZE != mouseX/BOX_SIZE || lastY/BOX_SIZE != mouseY/BOX_SIZE) {
    screen.onMouseDragged(null, lastX/BOX_SIZE, lastY/BOX_SIZE, mouseX/BOX_SIZE, mouseY/BOX_SIZE);
    lastX=mouseX;
    lastY=mouseY;
  }
}
void mouseReleased() {
  if (lastX/BOX_SIZE == mouseX/BOX_SIZE && lastY/BOX_SIZE == mouseY/BOX_SIZE) {
    screen.onMouseReleased(null, mouseX/BOX_SIZE, mouseY/BOX_SIZE);
  }
}

