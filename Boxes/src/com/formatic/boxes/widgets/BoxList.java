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

import com.formatic.boxes.Dimension;




public class BoxList extends BoxContainer {
	public enum LayoutType {
		VERTICAL, HORIZONTAL, FREE
	}
	LayoutType layoutType;


	public BoxList() {
		this(LayoutType.FREE);
	}

	public BoxList(LayoutType boxLayout) {
		this.layoutType = boxLayout;
	}

	public void layout() {
		Dimension boxesSize = new Dimension(0, 0);
		int maxSize=0;
		for (Box box : boxes) {
			if (layoutType == LayoutType.VERTICAL) {
				//box.setPosition(getPosition().x,  boxesSize.height);
				box.setPosition(0,  boxesSize.height);
				boxesSize.height += box.getSize().height;
				if(box.getSize().width >  maxSize){
					maxSize=box.getSize().width;
				}
			} else if (layoutType == LayoutType.HORIZONTAL) {
				//box.setPosition(boxesSize.width, getPosition().y);
				box.setPosition(boxesSize.width, 0);
				boxesSize.width += box.getSize().width;
				if(box.getSize().height >  maxSize){
					maxSize=box.getSize().height;
				}
			}
			box.layout();
		}
		if (layoutType == LayoutType.VERTICAL) {
			setSize(maxSize, boxesSize.height);
		}else if (layoutType == LayoutType.HORIZONTAL){
			setSize(boxesSize.width, maxSize);
		}else{
			if(container != null){
				setSize(container.getSize());
			}else{
				setSize(8,8);
			}
		}
	}
	public void clear(){
		super.clear();
		layout();
	}
	@Override
	public void add(Box box) {
		super.add(box);
		layout();
	}

	@Override
	public void add(int pos, Box box) {
		super.add(pos, box);
		layout();
	}

	@Override
	public Box remove(int pos) {
		Box ret = super.remove(pos);
		layout();
		return ret;
	}
}
