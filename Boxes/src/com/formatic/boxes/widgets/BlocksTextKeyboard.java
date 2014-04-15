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

import com.formatic.boxes.Font;



public class BlocksTextKeyboard extends BoxContainer   {
	TextEditor textEditor;
	NumberKeyboard numberKeyboard;
	BlockSelector blockSelector;
	CharSelector charSelector;

	public BlocksTextKeyboard() {
		super(8, 8);
		textEditor= new TextEditor(Font.TYPE_33, "");
		textEditor.setSize(8,3);
		textEditor.setPosition(0,0);
		add(textEditor);
		numberKeyboard = new NumberKeyboard();
		numberKeyboard.setPosition(0,3);
		numberKeyboard.setNumberKeyboardListener(textEditor);
		add(numberKeyboard);
		blockSelector=new BlockSelector();
		blockSelector.setPosition(0,5);
		add(blockSelector);
		charSelector = new CharSelector();
		charSelector.setPosition(2, 2);
		charSelector.color.a=0.1f;
		charSelector.setCharSelectorListener(textEditor);
		add(charSelector);
		blockSelector.setBlockSelectorListener(charSelector);
		
	}

}
