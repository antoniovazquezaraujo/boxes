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

import com.badlogic.gdx.graphics.Color;
import com.formatic.boxes.Font;
import com.formatic.boxes.ScreenModel;

public class TextBox extends BoxList {
	final static int MAX_TURNS=10;
	String text;
	Font font;
	private int speed=1;
	private int numTurns=0;

	TextBox(Font font) {
		this(font, "");
	}
	public TextBox(int fontType,String text) {
		this(new Font(fontType), text);
	}

	TextBox(Font font, String text) {
		super(LayoutType.HORIZONTAL);
		this.font = font;
		setText(text);
	}
	public void addText(String text){
		this.text += text;
		clear();
		updateBoxes();
	}

	public void setText(String text){
		this.text = text;
		clear();
		updateBoxes();
	}
	private void updateBoxes() {
		float c=0.2f;
		Color fontColor= new Color(c,0,0,1);
		for(int n=0; n<text.length(); n++){
			add(new CharBox(font, text.charAt(n), fontColor));			
			c+=0.2f;
			if(c>=1.0f)c=0.2f;
			fontColor=new Color(c,0,0,1);
		}
	}
	@Override
	public boolean onDrag(int x, int y, int newX, int newY) {
		return super.onDrag(x, y, newX, newY);
	}
	@Override
	public void update(ScreenModel screenModel) {
		super.update(screenModel);
		numTurns++;
		if(numTurns>=MAX_TURNS){
			numTurns=0;
			decX();
			if(getPosition().x < -1* (font.getWidth()*text.length())){
				//setX(getSize().width);
				setX(8);
			}
		}
		
	}
}

