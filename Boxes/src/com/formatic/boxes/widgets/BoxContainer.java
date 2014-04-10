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

import com.formatic.boxes.Dimension;
import com.formatic.boxes.Point;
import com.formatic.boxes.ScreenModel;

public class BoxContainer extends Box {
	public Vector<Box> boxes;
	Point at;

	public BoxContainer() {
		this(8, 8);
	}

	public BoxContainer(int width, int height) {
		super(width, height);
		boxes = new Vector<Box>();
		at = new Point(0, 0);

	}

	public void add(Box box) {
		boxes.add(box);
		box.setContainer(this);
	}

	public void add(int pos, Box box) {
		boxes.add(pos, box);
		box.setContainer(this);
	}

	public Box firstElement() {
		return boxes.firstElement();
	}

	public Box get(int pos) {
		return boxes.get(pos);
	}

	public Box lastElement() {
		return boxes.lastElement();
	}

	public Box remove(int pos) {
		Box ret = boxes.remove(pos);
		ret.setContainer(null);
		return ret;
	}

	public void remove(Box box) {
		boxes.remove(box);
	}

	public void clear(){
		boxes.clear();
	}
	public void remove(int x, int y) {
		Point p = new Point(x, y);
		for (Box b : boxes) {
			if (b.getPosition().equals(p)) {
				boxes.remove(b);
				return;
			}
		}
	}

	public void update(ScreenModel screenModel) {
		Point p = new Point(0, 0);
		Dimension d = new Dimension(0, 0);
		p.setLocation(screenModel.getPosition());
		d.setSize(screenModel.getSize());
		super.update(screenModel);
		for (Box box : boxes) {
			screenModel.applyClip(box.getPosition(), box.getSize());
			box.update(screenModel);
			screenModel.setPointAndSize(p, d);
		}
	}

	public void layout() {
		for (Box box : boxes) {
			box.layout();
		}
	}

	public Box boxAtPos(Point p) {
		for (int n = boxes.size() - 1; n >= 0; n--) {
			Box box = boxes.get(n);
			if (box.contains(p)) {
				return box;
			}
		}
		return null;
	}

	public void onClose(Box box) {

	}

	@Override
	public boolean onTouch(int x, int y) {
		if (super.onTouch(x, y)) {
			return true;
		}
		at.setLocation(x, y);
		Box box = boxAtPos(at);
		if (box != null) {
			if (box != this) {
				if (box.onTouch(x - box.getPosition().x,
								y - box.getPosition().y)) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean onRelease(int x, int y) {
		if (super.onRelease(x, y)) {
			return true;
		}
		at.setLocation(x, y);
		Box box = boxAtPos(at);
		if (box != null) {
			if (box.onRelease(x - box.getPosition().x, y - box.getPosition().y)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean onDrag(int x, int y, int newX, int newY) {
		if (super.onDrag(x, y, newX, newY)) {
			return true;
		}
		at.setLocation(x, y);
		Box box = boxAtPos(at);
		if (box != null) {
			if (box.onDrag(x - box.getPosition().x, y - box.getPosition().y,newX - box.getPosition().x, newY - box.getPosition().y)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean onZoomOut(int x, int y) {
		if (super.onZoomOut(x, y)) {
			return true;
		}
		at.setLocation(x, y);
		Box box = boxAtPos(at);
		if (box != null) {
			if (box.onZoomOut(x - box.getPosition().x, y - box.getPosition().y)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean onTouchCancel(int x, int y) {
		if (super.onTouchCancel(x, y)) {
			return true;
		}
		at.setLocation(x, y);
		Box box = boxAtPos(at);
		if (box != null) {
			if (box.onTouchCancel(	x - box.getPosition().x,
									y - box.getPosition().y)) {
				return true;
			}
		}
		return false;
	}
}
