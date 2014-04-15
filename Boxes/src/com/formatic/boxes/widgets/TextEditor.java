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
package com.formatic.boxes.widgets;


import com.formatic.boxes.Color;
import com.formatic.boxes.Font;
import com.formatic.boxes.ScreenModel;
import com.formatic.boxes.widgets.events.CharSelectorListener;
import com.formatic.boxes.widgets.events.NumberKeyboardListener;

public class TextEditor extends BoxList implements CharSelectorListener, NumberKeyboardListener {
	final static int MAX_TURNS = 10;
	String text;
	Font font;
	private int speed = 1;
	private int numTurns = 0;
	private Color fontColor;

	TextEditor(Font font) {
		this(font, "");
	}

	public TextEditor(int fontType, String text) {
		this(new Font(fontType), text);
	}

	public void setFontColor(Color fontColor) {
		this.fontColor = fontColor;
		if(fontColor.getBrightness() > 0.5f){
			color.setBrightness(0.0f);
		}		
	}

	TextEditor(Font font, String text) {
		super(LayoutType.HORIZONTAL);
		this.font = font;
		this.fontColor = new Color(1, 0, 0, 1);
		if(fontColor.getBrightness() > 0.5f){
			color.setBrightness(0.0f);
		}
		setText(text);

	}

	public void addText(String text) {
		this.text += text;
		clear();
		updateBoxes();
	}

	public void setText(String text) {
		this.text = text;
		clear();
		updateBoxes();
	}

	private void updateBoxes() {
		Color tmpColor = new Color(fontColor);
		float brightness = tmpColor.getBrightness();
		if (brightness < 0.2f){
			brightness= 0.2f;
		}
		if (brightness > 0.8f){
			brightness= 0.8f;
		}

		boolean even=false;
		for (int n = 0; n < text.length(); n++) {
			tmpColor = new Color(tmpColor);
			if(even){
				tmpColor.setBrightness(brightness-0.2f);
			}else{
				tmpColor.setBrightness(brightness);				
			}
			even = !even;
			add(new CharBox(font, text.charAt(n), tmpColor));
		}
	}

	@Override
	public boolean onDrag(int x, int y, int newX, int newY) {
		return super.onDrag(x, y, newX, newY);
	}

	@Override
	public void update(ScreenModel screenModel) {
		super.update(screenModel);
		int len = font.getWidth()*text.length();
		int boxWidth = container.getSize().width;
		setX((len *-1) +(boxWidth));
	}

	@Override
	public void charSelected(char c) {
		addText(""+c);
	}

	@Override
	public void numberSelected(int n) {
		addText(""+(char)('0'+n));
	}
}
