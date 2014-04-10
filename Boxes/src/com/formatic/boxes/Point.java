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

public class Point {
	public int x,y;
	public Point(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	public Point(){
		this.x=this.y=0;
	}
	public Point(Point d){
		this.x=d.x;
		this.y=d.y;
	}
	static Point random(){
		return new Point((int)(Math.random()*8), (int)(Math.random()*8));
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public void setLocation(int x, int y){
		this.x=x;
		this.y=y;
	}
	public void setLocation(Point p){
		this.x=p.x;
		this.y=p.y;
	}
	public boolean equals(Point p){
		return x==p.x && y == p.y;
	}
	public double distance(Point p){
		return Math.sqrt ( Math.pow ( p.x - x, 2 ) + Math.pow ( p.y - y, 2 ) );
	}
}
