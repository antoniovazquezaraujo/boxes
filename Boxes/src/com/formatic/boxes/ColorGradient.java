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

import com.badlogic.gdx.graphics.Color;

abstract public class ColorGradient {
  public Point from;
  public Point to;
  public Color fromColor, toColor;
  public boolean repeats;
  public double totalDistance;
  abstract Color getColor(Point location);
  ColorGradient(Point from, Point to, Color fromColor, Color toColor, boolean repeats) {
    this.from = from;
    this.to = to;
    this.fromColor=fromColor;
    this.toColor = toColor;
    this.repeats = repeats;
  }

  public void setFromRed(float r){
	  fromColor.r = r;
  }
  public void setFromGreen(float g){
	  fromColor.g=g;
  }
  public void setFromBlue(float b){
	  fromColor.b=b;
  }
  public void setToRed(float r){
	  toColor.r=r;
  }
  public void setToGreen(float g){
	  toColor.g=g;
  }
  public void setToBlue(float b){
	  toColor.b=b;
  }
  
}

