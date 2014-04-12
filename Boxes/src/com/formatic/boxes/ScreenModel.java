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



import com.badlogic.gdx.math.Rectangle;

public class ScreenModel {
	final int SIZE = 8;
	Color colors[][];
	private Color color;
	private Dimension size;
	private Point position;
	final Color black = new Color(0,0,0,1);

	public String toString() {
		String ret = "";
		ret+= position.x+","+position.y+"\n";
		ret+= size.width+","+size.height+"\n";
		for (int y = 0; y < SIZE; y++) {
			for (int x = 0; x < SIZE; x++) {
				int c = (int)(100 * colors[x][y].r +
				10 * colors[x][y].g +
				colors[x][y].b);
				if (x == position.x && y == position.y) {
					ret += "/";
				} else if (x == (position.x + size.width)-1 && y == position.y) {
					ret += "\\";
				} else if (x == position.x && y == (position.y + size.height)-1) {
					ret += "\\";
				} else if (x == (position.x + size.width)-1
							&& y == (position.y + size.height)-1) {
					ret += "/";
				}else{
					if(c!=0){
						ret += (char)('A'+c);
					}else{
						ret += '.';
					}
				}
			}
			ret += "\n";
		}
		return ret;
	}

	public ScreenModel() {
		color = new Color(0,0,0,0);
		size = new Dimension();
		position = new Point();
		resetPositionAndSize();
		colors = new Color[SIZE][SIZE];
		for (int x = 0; x < SIZE; x++) {
			for (int y = 0; y < SIZE; y++) {
				colors[x][y] = new Color(0, 0, 0, 1);
			}
		}
	}

	void resetPositionAndSize() {
		this.size.setSize(8, 8);
		this.position.setLocation(0, 0);
	}
	void setSize(Dimension size){
		this.size.setSize(size);
	}
	void setPosition(Point position){
		this.position.setLocation(position);
	}
	public void setPointAndSize(Point position, Dimension dimension) {
		this.size.setSize(dimension);
		this.position.setLocation(position);
	}
	void setRectangle(Rectangle clip) {
		this.size.setSize((int)clip.width, (int)clip.height);
		this.position.setLocation((int)clip.x, (int)clip.y);
	}

	public Dimension getSize() {
		return size;
	}
	public Point getPosition(){
		return position;
	}

	public Color getColor() {
		return color;
	}

	public Color getColor(int x, int y) {
		return colors[x][y];
	}

	public void setColor(Color color) {
		this.color.set(color);
	}
	public void substColor(Color color) {
		this.setColor(color);
	}

	public void clear() {
		for (int forX = 0; forX < size.width; forX++) {
			for (int forY = 0; forY < size.height; forY++) {
				setColor(position.x+forX,position.y+forY,black);
			}
		}
	}
	public void fill(int x, int y, int width, int height) {
		fill(x,y,width, height, this.getColor());
	}
	void translate(Point p){
		p.x+=position.x;
		p.y+=position.y;
	}
	private void setColor(int i, int j, Color color) {
		colors[i][j].set(color);
	}

	private void addColor(int i, int j, Color color) {
		colors[i][j].r= colors[i][j].r*(1-color.a) +color.r*color.a;
		colors[i][j].g= colors[i][j].g*(1-color.a) +color.g*color.a;
		colors[i][j].b= colors[i][j].b*(1-color.a) +color.b*color.a;
 
	}
	public void applyClip(Point p, Dimension d) {
		applyClip(p.x,p.y, d.width, d.height);
	}
	public void applyClip(Rectangle r) {
		applyClip((int)r.x, (int)r.y, (int)r.width, (int)r.height);
	}
	public void applyClip(int x, int y, int width, int height) {
		position.x+=x;
		position.y+=y;
		size.setSize(width, height);
	}
	void clip(Point from, Point to){
		position.x+=from.x;
		position.y+=from.y;
		size.setSize(to.x-from.x, to.y-from.y);
	}
	void clip(Point from, Dimension d){
		position.x+=from.x;
		position.y+=from.y;
		size.setSize(d);
	}
	public void fill(int boxX, int boxY, int boxWidth, int boxHeight, Color color) {
		for(int x = position.x+boxX; x<position.x+boxWidth; x++){
			for(int y=position.y+boxY; y<position.y+boxHeight; y++){
				if(
						   x>=0 
						&& x<8 
						&& y>=0 
						&& y<8
						&& x<position.x+size.width
						&& y<position.y+size.height
						){
					addColor(x,y, color);
				}
			}
		}
	}
	public void fill(int boxX, int boxY, int boxWidth, int boxHeight, ColorGradient colorGradient) {
		for(int x = position.x+boxX; x<position.x+boxWidth; x++){
			for(int y=position.y+boxY; y<position.y+boxHeight; y++){
				if(
						   x>=0 
						&& x<8 
						&& y>=0 
						&& y<8
						&& x<position.x+size.width
						&& y<position.y+size.height
						){
					/*
					 * Hacer que el gradiente se muestre relativo a la posicion del box!
					 */
			        Color theColor = colorGradient.getColor(new Point(x,y));
					addColor(x,y, theColor);
				}
			}
		}
	}
}
