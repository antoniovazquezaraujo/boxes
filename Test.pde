class Test {
  RepeaterButton b2;
  Test() {
    testMenu();
  }
  void testMenu() {
	screen.add(movingBoxes());
	screen.add(floatingPlanes());
	screen.add(randomPlane());
	screen.add(testLinearGradient());
	screen.add(testRadialGradient());
	screen.add(testCanvas());
	screen.add(testGeneralBox());
	screen.add(testDragChar());
	screen.add(testNumberKeyboard());
	screen.add(testKeyboard());
	screen.add(testRotator());
	screen.add(testRepeaterButton());
  }
  Plane testCanvas(){
	  return new Canvas();
  }
  ColorGradient radialGradient;
  Plane testRadialGradient() {
    Plane p= new Plane(0, 0, 8, 8);
	radialGradient = new RadialGradient(new Point(3,3),new Point(7,7), color(255,255,255), color(0,0,0), false);
	p.setColorGradient(radialGradient);
    p.setMouseListener(new MouseListener() {
      public boolean onMouseDoubleClicked(Box b, int x, int y) {
        return false;
      }
      public boolean onMousePressed(Box b, int x, int y) {
        return false;
      }
      public boolean onMouseReleased(Box b, int x, int y) {
        radialGradient.from.x = x;
		radialGradient.from.y = y;
		return false ;
      }
      public boolean onMouseDragged(Box b, int lastX, int lastY, int x, int y) {
        radialGradient.from.x = x;
		radialGradient.from.y = y;
        return false;
      }
      public boolean onMouseRepeated(Box b, int x, int y) {
        return true;
      }
    });
	return p;
  }
  ColorGradient linearGradient;
  Plane testLinearGradient() {
    Plane p= new Plane(0, 0, 8, 8);
	linearGradient = new LinearGradient(new Point(3,3),new Point(7,7), color(255,255,255), color(0,0,0), false);
	p.setColorGradient(linearGradient);
    p.setMouseListener(new MouseListener() {
      public boolean onMouseDoubleClicked(Box b, int x, int y) {
	  	println("DoubleClicked ");
        return false;
      }
      public boolean onMousePressed(Box b, int x, int y) {
        return false;
      }
      public boolean onMouseReleased(Box b, int x, int y) {
        linearGradient.from.x = x;
        linearGradient.to.x = x+8;
		linearGradient.from.y = y;
		linearGradient.to.y = y;
		return false ;
      }
      public boolean onMouseDragged(Box b, int lastX, int lastY, int x, int y) {
        linearGradient.from.x = x;
		linearGradient.from.y = y;
        return false;
      }
      public boolean onMouseRepeated(Box b, int x, int y) {
        return true;
      }
    });
	return p;
  }
  Plane testMouse() {
    Plane p= new Plane(0, 0, 8, 8);
    p.setMouseListener(new MouseListener() {
      public boolean onMouseDoubleClicked(Box b, int x, int y) {
	  	println("DoubleClicked ");
        return false;
      }
      public boolean onMousePressed(Box b, int x, int y) {
	  	println("Pressed");
        return false;
      }
      public boolean onMouseReleased(Box b, int x, int y) {
	  	println("Released");
        return false ;
      }
      public boolean onMouseDragged(Box b, int lastX, int lastY, int x, int y) {
	  	println("Dragged");
        return false;
      }
      public boolean onMouseRepeated(Box b, int x, int y) {
	  	println("Repeated");
        return true;
      }
    });
	return p;
  }
  Plane p;
  Plane testDragChar() {
    p = new Plane(0, 0, 2000, 8, Plane.HORIZONTAL);
    p.setMouseListener(new MouseListener() {
      boolean incrementing = true;
      public boolean onMousePressed(Box b, int x, int y) {
        return false;
      }
      public boolean onMouseReleased(Box b, int x, int y) {
        incrementing = !incrementing;
        return false;
      }
      public boolean onMouseDragged(Box b, int lastX, int lastY, int x, int y) {
        if (x > lastX) {
          p.rect.x++;
        }
        else if (x < lastX) {
          p.rect.x--;
        }
        return true;
      }
      public boolean onMouseRepeated(Box b, int x, int y) {
        if (incrementing) {
          p.rect.x++;
        }
        else {
          p.rect.x--;
        }
        return false;
      }
    });
    String pirata = "" +
      "CON CIEN CAÑONES POR BANDA, VIENTO EN POPA A TODA VELA, NO CORTA EL"+
      "MAR, SINO VUELA, UN VELERO BERGANTIN, BAJEL PIRATA QUE LLAMAN POR  "+
      "SU BRAVURA EL TEMIDO, EN TODO MAR CONOCIDO DEL UNO AL OTRO CONFIN. ";
    for (int n=0; n<pirata.length();n++) {
      Char c = new Char();
      c.setChar(pirata.charAt(n));
      p.add(c);
    }
	return p;
  }
  Plane testGeneralBox() {
    Plane p = new Plane(2, 2, 4, 4);
    p.setColor(color(34, 42, 155));
    Button b = new Button(1, 1, 2, 2, color(23,233,12), color(3,100,24));
    p.add(b);
	return p;
  }
  Char numberChar;
  Plane testNumberKeyboard() {
    numberChar = new Char();
    NumberKeyboard numberKeyboard = new NumberKeyboard();
    numberKeyboard.setNumberKeyboardListener(new NumberKeyboardListener() {
      public void numberSelected(int number) {
        numberChar.setNumber(number);
      }
    });
    Plane p = new Plane(0, 0, 8, 8, Plane.VERTICAL);
    p.add(numberChar);
    Box separator = new Box(0, 0, 1, 1);
    separator.setColor(color(255, 255, 255));
    p.add(separator);
    p.add(numberKeyboard);
   	return p; 
  }
  Char textChar;
  Plane testKeyboard() {
    textChar = new Char();
    TextKeyboard textKeyboard = new TextKeyboard();
    textKeyboard.setTextKeyboardListener(new TextKeyboardListener() {
      public void canceled() {
      }
      public void charSelected(char c) {
        textChar.setChar(c);
      }
      public void charShowed(char c) {
        textChar.setChar(c);
      }
    });
    Plane p = new Plane(0, 0, 8, 8, Plane.VERTICAL);
    p.add(textChar);
    Box separator = new Box(0, 0, 1, 1);
    separator.setColor(color(255, 255, 255));
    p.add(separator);
    p.add(textKeyboard);
   	return p; 
  }
  Char rotatorCharUnidades;
  Char rotatorCharDecenas;
  Plane testRotator() {
    rotatorCharUnidades= new Char();
    rotatorCharDecenas= new Char();
    Rotator rotator  = new Rotator(0, 99, 0);
    rotator.setRotatorListener(new RotatorListener() {
      public void numberSelected(int number) {
        rotatorCharDecenas.setNumber(number/10);
        rotatorCharUnidades.setNumber(number%10);
      }
    }
    );
    Plane p = new Plane(0, 0, 8, 8, Plane.VERTICAL);
    Plane chars = new Plane(0, 0, 8, 5, Plane.HORIZONTAL);
    chars.add(rotatorCharDecenas);
    chars.add(rotatorCharUnidades);
	p.add(chars);
    Box separator = new Box(0, 0, 1, 1);
    separator.setColor(color(255, 255, 255));
    p.add(separator);
    p.add(rotator);
    return p;
  }
  void test0() {
    screen.add(new Canvas());
  }
  void test1() {

    Plane p0= new Plane(0, 0, 8, 8);
    p0.setColor(color(255, 0, 0));

    Plane p1= new Plane(1, 1, 6, 8);
    p1.setColor(color(0, 255, 0));
    p0.add(p1);

    Plane p2= new Plane(1, 1, 5, 5);
    p2.setColor(color(0, 0, 255));
    p1.add(p2);

    //		Switch b0 = new Switch(0,0,1,1);
    //		b0.setColor(color(100, 100, 100));
    //		p2.add(b0);

    Button b1 = new Button(3, 3, 1, 1);
    b1.setColor(color(100, 0, 100));
    p2.add(b1);
    //
    //		b2 = new RepeaterButton(0, 255, 4,0,1,1);
    //		b2.setColor(color(200, 100, 40));
    //		b2.setRepeaterButtonListener(new RepeaterButtonListener(){
    //			public void valueChanged(RepeaterButton b, int value){
    //				Test.this.b2.setColor(value);
    //			}
    //		});
    //		p2.add(b2);
    screen.add(p0);
  }
  Plane testRepeaterButton() {
    Plane p0= new Plane(0, 0, 8, 8);
    p0.setColor(color(255, 0, 0));

    Plane p1= new Plane(1, 1, 6, 8);
    p1.setColor(color(0, 255, 0));
    p0.add(p1);

    Plane p11= new Plane(1, 1, 4, 4, Plane.VERTICAL);
    p11.setColor(color(100, 200, 55));
    p1.add(p11);

    Box b = new Box();
    b.setColor(color(12, 23, 44));
    p11.add(b);

    Plane p2= new Plane(1, 1, 4, 1, Plane.HORIZONTAL);
    p2.setColor(color(0, 0, 255));
    p11.add(p2);

    Switch b0 = new Switch();
    b0.setColor(color(100, 100, 100));
    p2.add(b0);

    Button b1 = new Button();
    b1.setColor(color(100, 0, 100));
    p2.add(b1);

    b2 = new RepeaterButton(0, 255);
    b2.setColor(color(200, 100, 40));
    b2.setRepeaterButtonListener(new RepeaterButtonListener() {
      public void valueChanged(RepeaterButton b, int value) {
        Test.this.b2.setColor(value);
      }
    });
    p2.add(b2);
    return p0;
  }
  Plane theRandomPlane; 
  Plane randomPlane() {
	  theRandomPlane = new Plane(0,0,8,8);
	  theRandomPlane.setMouseListener(new MouseListener(){
		  public boolean onMouseReleased(Box theBox, int x, int y) {
			  theRandomPlane.clear();
			  for (int n=0; n<random(15); n++) {
				  Plane p= new Plane();
				  theRandomPlane.add(p); 
				  for (int a=0; a<random(15);a++) {
					  Box box = new Box(randomPoint(), randomSize()); 
					  box.setColor(randomColor());
					  box.setColorGradient(randomGradient());
					  for (int b=0; b<random(15); b++) {
						  ProccessCommand proccess= new ProccessCommand(-1);
						  proccess.addCommand(commandFactory());
						  box.addCommand(proccess);
					  }
					  p.add(box);
				  }
			  }
			  return true;
		  }
	  });
	return theRandomPlane;
  }
  Plane floatingPlane; 
  Plane floatingPlanes() {
	  floatingPlane = new Plane(0,0,8,8);
	  floatingPlane.setMouseListener(new MouseListener(){
		  public boolean onMouseReleased(Box theBox, int x, int y) {
			  floatingPlane.clear();
			  for (int n=0; n<15; n++) {
				  Plane p= new Plane();
				  p.setAlfa((int)random(255));
				  floatingPlane.add(p); 
				  for (int a=0; a<5;a++) {
					  Box box = new Box(randomPoint(), randomSize()); 
					  box.setColor(randomColor());
					  box.setColorGradient(randomGradient());
					  for (int b=0; b<random(15); b++) {
						  ProccessCommand proccess= new ProccessCommand(-1);
						  int from = -8+(int)random(24);
						  proccess.addCommand(
							  new ChangeCommand(
								  (int)random(50), 
								  20,
								  from, 
								  -from, 
								  0,
								 ChangeCommand.LOCATION_X 
							));
						  from = -8+(int)random(24);
						  proccess.addCommand(
							  new ChangeCommand(
								  (int)random(50), 
								  20,
								  from, 
								  -from, 
								  0,
								 ChangeCommand.LOCATION_Y 
							));
						  box.addCommand(proccess);
					  }
					  p.add(box);
				  }
			  }
			  return true;
		  }
	  });
	return floatingPlane;
  }
  Plane movingPlane; 
  Plane movingBoxes() {
	  movingPlane = new Plane(0,0,8,8);
	  movingPlane.setMouseListener(new MouseListener(){
			  public boolean onMouseReleased(Box theBox, int x, int y) {
				  movingPlane.clear();
				  Plane p= new Plane();
				  p.setAlfa((int)random(255));
				  movingPlane.add(p); 
				  for (int a=0; a<1;a++) {
					  Box box = new Box(new Point(0,0), new Size(1,1)); 
					  box.setColor(randomColor());
					  box.setColorGradient(randomGradient());
					  box.addCommand( new ChangeCommand(1,8,0,7,0,ChangeCommand.LOCATION_X));
					  box.addCommand( new ChangeCommand(1,8,0,7,0,ChangeCommand.LOCATION_Y));
					  box.addCommand( new ChangeCommand(1,8,7,0,0,ChangeCommand.LOCATION_X));
					  box.addCommand( new ChangeCommand(1,8,7,0,0,ChangeCommand.LOCATION_Y));
					  p.add(box);
				  }
			  return true;
		  }
	  });
	return movingPlane;
  }
}

