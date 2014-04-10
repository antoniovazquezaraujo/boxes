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
import com.formatic.boxes.widgets.Box;

//Command, comando que puede repetirse un número de veces
public class Command {
  protected int times, actualTime;
  protected Box box;
  protected Command(int times) {
    this.times = times;
    actualTime = 0;
  }
  public void setBox(Box box) {
    this.box = box;
  }
  public void update() {
    if (times > 0) {
      actualTime++;
    }
  }
  public boolean isFinished() {
    return actualTime == times;
  }
  public void reset() {
    actualTime = 0;
  }
}



