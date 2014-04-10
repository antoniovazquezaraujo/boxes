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

public class Dimension {
	public int width,height;
	public Dimension(int width, int height) {
		super();
		this.width = width;
		this.height = height;
	}
	public Dimension(){
		this.width=this.height=0;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}

	public Dimension(Dimension d){
		this.width=d.width;
		this.height=d.height;
	}

	public void setSize(Dimension size){
		this.width= size.width;
		this.height=size.height;
	}
	public void setSize(int width, int height){
		this.width=width;
		this.height=height;
	}
	public boolean equals(Dimension d){
		return d.width==width && d.height==height;
	}
}
