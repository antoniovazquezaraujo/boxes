/*
	                          BOXES
	              A minimalist color box framework
	         Copyright (C) 2014 Antonio Vazquez Araujo
	          (antoniovazquezaraujo[at]gmail[dot]com)
	          
	This file is part of Boxes.

    Boxes is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    Boxes is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with Boxes.  If not, see <http://www.gnu.org/licenses/>.
*/
package com.formatic.boxes;

import com.badlogic.gdx.graphics.Color;
import com.formatic.boxes.commands.ChangeCommand;
import com.formatic.boxes.games.Cheso;
import com.formatic.boxes.widgets.Box;
import com.formatic.boxes.widgets.BoxContainer;
import com.formatic.boxes.widgets.BoxList;
import com.formatic.boxes.widgets.BoxList.LayoutType;
import com.formatic.boxes.widgets.Button;
import com.formatic.boxes.widgets.CharBox;
import com.formatic.boxes.widgets.NumberKeyboard;
import com.formatic.boxes.widgets.Rotator;
import com.formatic.boxes.widgets.Selector;
import com.formatic.boxes.widgets.TextBox;
import com.formatic.boxes.widgets.TextKeyboard;
import com.formatic.boxes.widgets.events.BoxEventAdapter;
import com.formatic.boxes.widgets.events.BoxEventListener;
import com.formatic.boxes.widgets.events.NumberKeyboardListener;
import com.formatic.boxes.widgets.events.RotatorListener;
import com.formatic.boxes.widgets.events.TextKeyboardListener;

public class Demo {
	Color red = new Color(0.5f, 0, 0, 1f);
	Color green = new Color(0, 0.5f, 0, 1f);
	Color blue = new Color(0, 0, 0.5f, 1f);
	Color menosBlue = new Color(0, 0, 0.6f, 0.5f);
	Color yellow = new Color(0.5f, 0.5f, 0, 1f);
	Color orange = new Color(0.3f, 0.8f, 0.2f, 0.5f);

	public Demo() {
	}

	void populate(Selector topBox) {
		topBox.add(demo1());
		topBox.add(demo2());
		topBox.add(demo3());
		topBox.add(demo4());
		topBox.add(demo5());
		topBox.add(demo6());
		topBox.add(demo7());
		topBox.add(demo8());
		topBox.add(demo9());
		topBox.add(testRadialGradient());
		topBox.add(testLinearGradient());
		topBox.add(movingBoxes());
		topBox.add(randomCommands());
		topBox.add(new Cheso());
		topBox.add(demoDragBoxes());
		topBox.add(demoRotator1());
		topBox.add(demoRotator2());
		topBox.add(demoNumberKeyboard1());
		topBox.add(demoNumberKeyboard2());
		topBox.add(demoTextKeyboard());
	}

	BoxList demo1() {
		BoxList uno = new BoxList(LayoutType.VERTICAL);
		uno.add(new Button(4, 4, yellow, green));
		uno.add(new Button(4, 4, menosBlue, red));
		return uno;
	}

	BoxList demo2() {
		BoxList dos = new BoxList(LayoutType.HORIZONTAL);
		dos.add(new Button(4, 4, green, red));
		dos.add(new Button(2, 2, menosBlue, yellow));
		dos.add(new Button(2, 2));
		return dos;
	}

	BoxList demo3() {
		BoxList tres = new BoxList();
		Button bbb = new Button(0, 0, 3, 3, green, red);
		tres.add(new Button(3, 3, 2, 2, menosBlue, yellow));
		tres.add(new Button(4, 6, 2, 2, blue, orange));
		return tres;
	}

	BoxList demo4() {
		BoxList cuatro = new BoxList();
		cuatro.add(new TextBox(Font.TYPE_57, "LETS COOKING"));
		return cuatro;
	}

	BoxList demo5() {
		BoxList cinco = new BoxList();
		cinco.add(new TextBox(
								Font.TYPE_55,
								"ESTO NO PARECE TAN COMPLICADO UNA VEZ QUE TE PASAS UNA ETERNIDAD INTENTANDO ENTENDERLO"));
		return cinco;
	}

	BoxList demo6() {
		BoxList seis = new BoxList();
		seis.add(new TextBox(Font.TYPE_45,
								"SI FUESE DIOS SERIA INCAPAZ DE CONTESTAR A MIS PROPIAS PREGUNTAS"));
		return seis;
	}

	BoxList demo7() {
		BoxList siete = new BoxList();
		siete.add(new TextBox(Font.TYPE_24, "0123456789"));
		return siete;
	}

	BoxList demo8() {
		BoxList ocho = new BoxList();
		ocho.add(new TextBox(
								Font.TYPE_33,
								"SI AHORRAS EN ESPACIO PARA AHORRAR TIEMPO PUEDES CREAR UNA PERTURBACION EN LA FUERZA"));
		return ocho;
	}

	BoxList demo9() {
		BoxList nueve = new BoxList();
		nueve.add(new TextBox(Font.TYPE_35, "ALL OF YOUR BASES BELONG TO US"));
		return nueve;
	}

	ColorGradient radialGradient;

	BoxContainer testRadialGradient() {
		BoxContainer p = new BoxContainer();
		radialGradient = new RadialGradient(new Point(3, 3), new Point(7, 7),
											new Color(1.0f, 1.0f, 1.0f, 1.0f),
											new Color(0.0f, 0.0f, 0.0f, 1.0f),
											false);
		p.setColorGradient(radialGradient);
		p.setBoxEventListener(new BoxEventAdapter() {
			@Override
			public boolean onRelease(int x, int y) {
				radialGradient.from.x = x;
				radialGradient.from.y = y;
				return false;
			}

			@Override
			public boolean onDrag(int x, int y, int newX, int newY) {
				radialGradient.from.x = newX;
				radialGradient.from.y = newY;
				return false;
			}

		});
		return p;
	}

	ColorGradient linearGradient;

	BoxContainer testLinearGradient() {
		BoxContainer p = new BoxContainer();
		linearGradient = new LinearGradient(new Point(3, 3), new Point(7, 7),
											new Color(0.0f, 1.0f,0.0f, 1.0f),
											new Color(0.0f, 0.0f, 0.0f, 1.0f),
											false);
		p.setColorGradient(linearGradient);
		p.setBoxEventListener(new BoxEventAdapter() {

			@Override
			public boolean onRelease(int x, int y) {
				linearGradient.from.x = x;
				linearGradient.to.x = x + 8;
				linearGradient.from.y = y;
				linearGradient.to.y = y + 8;
				return false;
			}

			@Override
			public boolean onDrag(int x, int y, int newX, int newY) {
				linearGradient.from.x = newX;
				linearGradient.from.y = newY;
				return false;
			}

		});
		return p;
	}

	BoxContainer movingPlane;

	BoxContainer movingBoxes() {
		movingPlane = new BoxContainer();
		movingPlane.setBoxEventListener(new BoxEventAdapter() {
			@Override
			public boolean onRelease(int x, int y) {
				// movingPlane.clear();
				BoxContainer p = new BoxContainer();
				// p.setAlfa((int)random(255));
				movingPlane.add(p);
				for (int a = 0; a < 10; a++) {
					Box box = new Box();
					box.setColor(new Color((float) Math.random(),
											(float) Math.random(),
											(float) Math.random(),
											(float) Math.random()));
					// box.setColorGradient(randomGradient());
					box.addCommand(new ChangeCommand(
														1,
														8,
														0,
														7,
														0,
														ChangeCommand.LOCATION_X));
					box.addCommand(new ChangeCommand(
														1,
														8,
														0,
														7,
														0,
														ChangeCommand.LOCATION_Y));
					box.addCommand(new ChangeCommand(
														1,
														8,
														7,
														0,
														0,
														ChangeCommand.LOCATION_X));
					box.addCommand(new ChangeCommand(
														1,
														8,
														7,
														0,
														0,
														ChangeCommand.LOCATION_Y));
					p.add(box);
				}
				return true;
			}


		});
		return movingPlane;
	}

	BoxContainer commandsBox;

	BoxContainer randomCommands() {
		commandsBox = new BoxContainer();
		commandsBox.setBoxEventListener(new BoxEventAdapter() {
			@Override
			public boolean onTouch(int x, int y) {
				Box box = new Box((int) (Math.random() * 8),
									(int) (Math.random() * 8),
									(int) (Math.random() * 8),
									(int) (Math.random() * 8),
									new Color((float) (Math.random()),
												(float) (Math.random()),
												(float) (Math.random()),
												(float) (Math.random())));
				Color fromColor = new Color((float) (Math.random()),
											(float) (Math.random()),
											(float) (Math.random()),
											(float) (Math.random()));
				Color toColor = new Color((float) (Math.random()),
											(float) (Math.random()),
											(float) (Math.random()),
											(float) (Math.random()));
				if (Math.random() > 0.5) {
					box.setColorGradient(new RadialGradient(Point.random(),
															Point.random(),
															fromColor, toColor,
															false));
				} else {
					box.setColorGradient(new LinearGradient(Point.random(),
															Point.random(),
															fromColor, toColor,
															false));
				}

				int max = (int) (Math.random() * 50);
				Command command;
				for (int n = 0; n < max; n++) {
					command = ChangeCommand.randomCommand();
					box.addCommand(command);
				}
				commandsBox.add(box);
				return true;
			}

		});
		return commandsBox;
	}

	public BoxContainer demoDragBoxes() {
		BoxContainer ret = new BoxContainer();
		final BoxContainer b = new BoxContainer(5, 5);
		b.setColor(new Color(1, 1, 0, 1));
		ret.add(b);
		final Box c = new Box(1, 1, 2, 2, new Color(0, 1, 1, 1));
		c.setAlpha(0.5f);
		b.add(c);
		final BoxContainer p = new BoxContainer();
		p.add(ret);
		p.setBoxEventListener(new BoxEventListener() {
			
			private Box movingBox;

			@Override
			public boolean onZoomOut(int x, int y) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean onTouchCancel(int x, int y) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean onTouch(int x, int y) {
				Box touched = p.boxAtPos(new Point(x,y));
				if(touched!= null){
					movingBox = touched;
				}
				return false;
			}
			
			@Override
			public boolean onRelease(int x, int y) {
				movingBox = null;
				return false;
			}
			
			@Override
			public boolean onDrag(int x, int y, int newX, int newY) {
				if(movingBox!= null){
					movingBox.setX(newX);
					movingBox.setY(newY);
				}
				return false;
			}
		});
		return p;
	}

	public BoxContainer demoRotator1() {
		final CharBox rotatorCharUnidades = new CharBox(new Font(Font.TYPE_24),
														'0', new Color(0.6f, 0,
																		0, 1));
		final CharBox rotatorCharDecenas = new CharBox(new Font(Font.TYPE_24),
														'0', new Color(0.9f, 0,
																		0, 1));
		Rotator rotator = new Rotator(0, 99, 0);
		rotator.setRotatorListener(new RotatorListener() {
			public void numberSelected(int number) {
				rotatorCharDecenas.setNumber(number / 10);
				rotatorCharUnidades.setNumber(number % 10);
			}
		});
		BoxList list = new BoxList(LayoutType.VERTICAL);
		BoxList chars = new BoxList(LayoutType.HORIZONTAL);
		chars.add(rotatorCharDecenas);
		chars.add(rotatorCharUnidades);
		list.add(chars);
		list.add(rotator);
		return list;
	}

	public BoxContainer demoRotator2() {
		final CharBox rotatorCharUnidades = new CharBox(new Font(Font.TYPE_35),
														'0', new Color(0, 0.6f,
																		0, 1));
		final CharBox rotatorCharDecenas = new CharBox(new Font(Font.TYPE_35),
														'0', new Color(0, 0.9f,
																		0, 1));
		Rotator rotator = new Rotator(0, 99, 0);
		rotator.setRotatorListener(new RotatorListener() {
			public void numberSelected(int number) {
				rotatorCharDecenas.setNumber(number / 10);
				rotatorCharUnidades.setNumber(number % 10);
			}
		});
		BoxList list = new BoxList(LayoutType.VERTICAL);
		BoxList chars = new BoxList(LayoutType.HORIZONTAL);
		chars.add(rotatorCharDecenas);
		chars.add(rotatorCharUnidades);
		list.add(chars);
		list.add(rotator);
		return list;
	}

	public BoxContainer demoNumberKeyboard1() {
		final CharBox keyboardCharUnidades = new CharBox(
															new Font(
																		Font.TYPE_24),
															'0',
															new Color(0, 0.6f,
																		0, 1));
		final CharBox keyboardCharDecenas = new CharBox(new Font(Font.TYPE_24),
														'0', new Color(0, 0.9f,
																		0, 1));
		NumberKeyboard numberKeyboardUnidades = new NumberKeyboard();
		numberKeyboardUnidades.setNumberKeyboardListener(new NumberKeyboardListener() {
			public void numberSelected(int number) {
				keyboardCharUnidades.setNumber(number);
			}
		});
		NumberKeyboard numberKeyboardDecenas = new NumberKeyboard();
		numberKeyboardDecenas.setNumberKeyboardListener(new NumberKeyboardListener() {
			public void numberSelected(int number) {
				keyboardCharDecenas.setNumber(number);
			}
		});
		BoxList list = new BoxList(LayoutType.VERTICAL);
		BoxList chars = new BoxList(LayoutType.HORIZONTAL);
		chars.add(keyboardCharDecenas);
		chars.add(keyboardCharUnidades);
		list.add(chars);
		BoxList keyboards = new BoxList(LayoutType.HORIZONTAL);
		keyboards.add(numberKeyboardDecenas);
		keyboards.add(numberKeyboardUnidades);
		list.add(keyboards);
		return list;
	}
	public BoxContainer demoNumberKeyboard2() {
		final CharBox keyboardCharUnidades = new CharBox(
															new Font(
																		Font.TYPE_45),
															'0',
															new Color(0, 0.6f,
																		0, 1));
		final CharBox keyboardCharDecenas = new CharBox(new Font(Font.TYPE_45),
														'0', new Color(0, 0.9f,
																		0, 1));
		NumberKeyboard numberKeyboardUnidades = new NumberKeyboard();
		numberKeyboardUnidades.setNumberKeyboardListener(new NumberKeyboardListener() {
			public void numberSelected(int number) {
				keyboardCharUnidades.setNumber(number);
			}
		});
		NumberKeyboard numberKeyboardDecenas = new NumberKeyboard();
		numberKeyboardDecenas.setNumberKeyboardListener(new NumberKeyboardListener() {
			public void numberSelected(int number) {
				keyboardCharDecenas.setNumber(number);
			}
		});
		BoxList list = new BoxList(LayoutType.VERTICAL);
		BoxList chars = new BoxList(LayoutType.HORIZONTAL);
		chars.add(keyboardCharDecenas);
		chars.add(keyboardCharUnidades);
		list.add(chars);
		BoxList keyboards = new BoxList(LayoutType.HORIZONTAL);
		keyboards.add(numberKeyboardDecenas);
		keyboards.add(numberKeyboardUnidades);
		list.add(keyboards);
		return list;
	}
	public BoxContainer demoTextKeyboard() {
		final TextBox textBox = new TextBox(Font.TYPE_33," ");
		TextKeyboard textKeyboard= new TextKeyboard();
		textKeyboard.setTextKeyboardListener(new TextKeyboardListener() {
			public void charSelected(char c) {
				textBox.addText(""+c);
			}
		});
		BoxList list = new BoxList(LayoutType.VERTICAL);
		list.add(textBox);
		list.add(textKeyboard);
		return list;
	}
}
