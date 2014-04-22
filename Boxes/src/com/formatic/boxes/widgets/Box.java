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
import com.formatic.boxes.Dimension;
import com.formatic.boxes.Point;
import com.formatic.boxes.ScreenModel;
import com.formatic.boxes.commands.BatchCommand;
import com.formatic.boxes.commands.Command;
import com.formatic.boxes.gradients.ColorGradient;
import com.formatic.boxes.widgets.events.BoxDisplayListener;
import com.formatic.boxes.widgets.events.BoxEventListener;

public class Box implements BoxEventListener, BoxDisplayListener {
	private Point position;
	private Dimension size;
	Color color;
	public ColorGradient colorGradient;
	protected BoxContainer container;
	protected BoxEventListener boxEventListener;
	protected BoxDisplayListener boxDisplayListener;
	BatchCommand batch;

	public Color getColor() {
		return color;
	}

	public Box() {
		this(new Color(1, 1, 1, 1));
	}

	public Box(Color color) {
		this(1, 1, color);
	}

	public Box(int width, int height) {
		this(0, 0, width, height, null);
	}

	public Box(int width, int height, Color color) {
		this(0, 0, width, height, color);
	}
	public Box(int x, int y, int width, int height) {
		this(x,y, width, height, null);
	}
	public Box(int x, int y, int width, int height, Color color) {
		this.position = new Point(x, y);
		this.size = new Dimension(width, height);
		this.colorGradient = null;
		batch = new BatchCommand();
		batch.setBox(this);
		if(color!= null){
			setColor(color);
		}else{
			setColor(new Color(1,1,1,1));
		}
	}

	public void layout() {

	}

	public void setBoxEventListener(BoxEventListener boxEventListener) {
		this.boxEventListener = boxEventListener;
	}

	protected void setBoxDisplayListener(BoxDisplayListener boxDisplayListener) {
		this.boxDisplayListener = boxDisplayListener;
	}

	public void setContainer(BoxContainer container) {
		this.container = container;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	public void setColor(float r, float g, float b){
		this.color.set(r,g,b, this.color.a);
	}
	public void setColor(float r, float g, float b, float a){
		this.color.set(r,g,b,a);
	}
	public void setColor(float greyLevel){
		this.color.r = greyLevel;
		this.color.g = greyLevel;
		this.color.b = greyLevel;
	}

	public void setColorGradient(ColorGradient colorGradient) {
		this.colorGradient = colorGradient;
	}

	@Override
	public void update(ScreenModel screenModel) {
		if (!batch.isFinished()) {
			batch.update();
		} else {
			batch.reset();
		}
		if (colorGradient != null) {
			screenModel.fill(	0, 0, getSize().width, getSize().height,
								colorGradient);
		} else {
			screenModel.setColor(color);
			screenModel.fill(0, 0, getSize().width, getSize().height);
		}
		if (boxDisplayListener != null) {
			boxDisplayListener.update(screenModel);
		}
	}

	@Override
	public boolean onTouch(int x, int y) {
		if (boxEventListener != null) {
			if (boxEventListener.onTouch(x, y)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean onRelease(int x, int y) {
		if (boxEventListener != null) {
			if (boxEventListener.onRelease(x, y)) {
				return true;
			}
		}
		return false;
	}
	@Override
	public boolean onDrag(int x, int y, int newX, int newY) {
		if (boxEventListener != null) {
			if (boxEventListener.onDrag(x, y, newX, newY)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean onZoomOut(int x, int y) {
		if (container != null) {
			container.onClose(this);
			return true;
		}
		if (boxEventListener != null) {
			if (boxEventListener.onZoomOut(x, y)) {
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean onTouchCancel(int x, int y) {
		if (boxEventListener != null) {
			if (boxEventListener.onTouchCancel(x, y)) {
				return true;
			}
		}
		return false;
	}

	public boolean contains(Point p) {
		return p.x >= getPosition().x
				&& p.x < (getPosition().x + getSize().width)
				&& p.y >= getPosition().y
				&& p.y < (getPosition().y + getSize().height);
	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position.setLocation(position);
	}

	public void setPosition(int x, int y) {
		this.position.setLocation(x, y);
	}

	public void setX(int x) {
		this.position.x = x;
	}

	public void incX() {
		this.position.x++;
	}

	public void decX() {
		this.position.x--;
	}

	public void setY(int y) {
		this.position.y = y;
	}

	public void incY() {
		this.position.y++;
	}

	public void decY() {
		this.position.y--;
	}

	public Dimension getSize() {
		return size;
	}

	public void setSize(Dimension size) {
		this.size.setSize(size);
	}

	public void setSize(int width, int height) {
		this.size.setSize(width, height);
	}

	public void setWidth(int width) {
		this.size.width = width;
	}

	public void setHeight(int height) {
		this.size.height = height;
	}

	public Box boxAtPos(Point p) {
		if (contains(p)) {
			return this;
		}
		return null;
	}

	public void setHue(float hue){
		this.color.setHue(hue);
	}
	public void setSaturation(float saturation){
		this.color.setSaturation(saturation);
	}
	public void setBrightness(float brightness){
		this.color.setBrightness(brightness);
	}
	public void setRed(float r) {
		this.color.r = r;
	}

	public void setGreen(float g) {
		this.color.g = g;
	}

	public void setBlue(float b) {
		this.color.b = b;
	}
	public void setAlpha(float alpha){
		this.color.a=alpha;
	}

	public void addCommand(Command command) {
		command.setBox(this);
		batch.addCommand(command);
	}
}
