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

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class ScreenRenderer extends ShapeRenderer {
	private ScreenModel model;
	private int width;
	private int height;
	int boxWidth;
	int boxHeight;
	Color linesColor;

	ScreenRenderer(ScreenModel model) {
		this.model = model;
		linesColor = new Color(0,0,0,1);
		this.width = Gdx.graphics.getWidth();
		this.height = Gdx.graphics.getHeight();
		this.boxWidth = (int) this.width / 8;
		this.boxHeight = (int) this.height / 8;
	}

 

	public void render() {
		begin(ShapeType.Filled);
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				setColor(model.getColor(x, y));
				rect(x * boxWidth, (7- (y)) * boxHeight, boxWidth, boxHeight);
			}
		}
		end();
		begin(ShapeType.Line);
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				setColor(linesColor);
				rect(x * boxWidth, (7- (y)) * boxHeight, boxWidth, boxHeight);
			}
		}
		end();
	}
}
