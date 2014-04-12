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

import java.util.Vector;

import com.formatic.boxes.Color;
import com.formatic.boxes.ScreenModel;
import com.formatic.boxes.widgets.events.ButtonListener;

public class Selector extends BoxContainer implements ButtonListener {
	private Color solidBackground, nextColor, prevColor, selectColor, transparentBackground;
	private Color nextPressedColor, prevPressedColor, selectPressedColor;
	private Button next, prev, select;
	Vector<Box> selectingBoxes;
	private Box selectedBox;
	private int selectedIndex;
	boolean running;

	public Selector() {
		setSize(8, 8);
		solidBackground = new Color(0,0,0,1);
		transparentBackground = new Color(1, 1, 1, 0.4f);
		prevColor = new Color(0.5f, 0, 0, 0.5f);
		nextColor = new Color(0, 0.5f, 0, 0.5f);
		selectColor = new Color(0, 0, 0.5f, 0.5f);
		prevPressedColor = new Color(1, 0, 0, 0.5f);
		nextPressedColor = new Color(0, 1, 0, 0.5f);
		selectPressedColor = new Color(0, 0, 1, 0.5f);
		setColor(solidBackground); // totalmente transparente!
		prev = new Button(0, 0, 1, 7, prevColor, nextPressedColor);
		prev.setButtonListener(this);
		next = new Button(7, 0, 1, 7, nextColor, prevPressedColor);
		next.setButtonListener(this);
		select = new Button(0, 7, 8, 1, selectColor, selectPressedColor);
		select.setButtonListener(this);
		BoxContainer cristal = new BoxContainer();
		cristal.setColor(transparentBackground);
		cristal.add(next);
		cristal.add(prev);
		cristal.add(select);
		super.add(cristal);
		selectingBoxes = new Vector<Box>();
		selectedIndex = -1;
		running = false;
	}

	public void layout() {
		for (Box box : selectingBoxes) {
			box.layout();
		}
		if (selectedBox != null) {
			selectedBox.layout();
		}
	}

	@Override
	public void add(Box box) {
		selectingBoxes.add(box);
		box.setContainer(this);
		box.setPosition(0,0);
		box.setSize(8,8);
		selectedIndex = selectingBoxes.size() - 1;
		selectedBox = selectingBoxes.get(selectedIndex);
		layout();
		select();
	}

	@Override
	public	void onClose(Box box) {
		running = false;
		select();
		super.onClose(box);
	}
 

	@Override
	public boolean onClick(Button b) {
		if (b == prev) {
			if (selectedIndex > 0)
				selectedIndex--;
		} else if (b == next) {
			if (selectedIndex < selectingBoxes.size() - 1)
				selectedIndex++;
		} else if (b == select) {
			running = true;
		}
		select();
		return true;
	}

	private void select() {
		if (selectedIndex >= 0 && selectedIndex < selectingBoxes.size()) {
			selectedBox = selectingBoxes.get(selectedIndex);
			setBoxDisplayListener(selectedBox);
			if (running == true) {
				setBoxEventListener(selectedBox);
			} else {
				setBoxEventListener(null);
			}
		}
	}

	public void update(ScreenModel screenModel) {
		if (boxDisplayListener != null) {
			boxDisplayListener.update(screenModel);
		}
		if (!running) {
			super.update(screenModel);
		}
	}
	@Override
	public boolean onTouch(int x, int y) {
		if(selectedBox!= null && running){
			return selectedBox.onTouch(x, y);
		}
		return super.onTouch(x, y);
	}
	@Override
	public boolean onRelease(int x, int y) {
		if(selectedBox!= null && running){
			return selectedBox.onRelease(x, y);
		}
		return super.onRelease(x, y);
	}
	@Override
	public boolean onDrag(int x, int y, int newX, int newY) {
		if(selectedBox!= null && running){
			return selectedBox.onDrag(x, y, newX, newY);
		}
		return super.onDrag(x, y, newX, newY);
	}

}
