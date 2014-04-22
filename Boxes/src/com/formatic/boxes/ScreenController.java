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

import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.formatic.boxes.widgets.Box;

public class ScreenController implements GestureListener {

	private Box topBox;
	ScreenRenderer screenRenderer;
	boolean zooming;
	boolean canceled;
	int touchX, touchY;
	float lastDragX, lastDragY;
	float actualDragX, actualDragY;


	public ScreenController(ScreenRenderer screenRenderer, Box topBox) {
		this.screenRenderer = screenRenderer;
		this.topBox = topBox;
		zooming = false;
		canceled = false;
	}

	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		canceled = false;
		if (pointer == 0) {
			touchX = (int) (x / screenRenderer.boxWidth);
			touchY = (int) (y / screenRenderer.boxHeight);
			lastDragX = touchX;
			lastDragY = touchY;
			if(topBox.onTouch(touchX, touchY)){
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		int tapX = (int) (x / screenRenderer.boxWidth);
		int tapY = (int) (y / screenRenderer.boxHeight);

		if (!canceled && tapX == touchX && tapY == touchY) {
			if(topBox.onRelease(touchX, touchY)){
				return true;
			}
		}
		canceled = true;
		return topBox.onTouchCancel(touchX, touchY);
	}

	@Override
	public boolean longPress(float x, float y) {
		canceled = true;
		topBox.onTouchCancel(touchX, touchY);
		return topBox.onZoomOut(touchX, touchY);
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		return false;
	}

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {
		int dragX = (int) (x / screenRenderer.boxWidth);
		int dragY = (int) (y / screenRenderer.boxHeight);
		if (dragX != lastDragX || dragY != lastDragY) {
			lastDragX = dragX;
			lastDragY = dragY;
			if(topBox.onDrag(touchX, touchY, dragX, dragY)){
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean panStop(float x, float y, int pointer, int button) {
		int panX = (int) (x / screenRenderer.boxWidth);
		int panY = (int) (y / screenRenderer.boxHeight);

		if (panX == touchX && panY == touchY) {
			canceled = false;
			if(topBox.onRelease(touchX, touchY)){
				return true;
			}
		}
		canceled = true;
		topBox.onTouchCancel(touchX, touchY);
		if (zooming) {
			zooming = false;
			if(topBox.onZoomOut(touchX, touchY)){
				return true;
			}
		}
		return true;
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		zooming = true;
		return true;
	}

	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2,
							Vector2 pointer1, Vector2 pointer2) {
		zooming = true;
		return true;
	}

}
